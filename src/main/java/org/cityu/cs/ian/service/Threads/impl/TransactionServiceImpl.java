package org.cityu.cs.ian.service.Threads.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.cityu.cs.ian.model.bean.Transaction;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.ECDSACoder;
import org.cityu.cs.ian.util.TransactonListOperatorUtils;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements ITransactionService {

    @Override
    public boolean verifySign(Transaction transaction) {
       String fromPubkey = transaction.getFromPubkey().split(",")[0];
        String toPubkey = transaction.getToPubkey().split(",")[0];

        byte[] fromBytes = Base64.decodeBase64(fromPubkey);
        byte[] toBytes = Base64.decodeBase64(toPubkey);

        String fromSi = transaction.getSignatures().split(",")[0];
        String toSi = transaction.getSignatures().split(",")[1];
        try {
            String json = signJson(transaction);
            boolean verifyFrom = ECDSACoder.verify(json.getBytes(), fromBytes, Base64.decodeBase64(fromSi));
            boolean verifyTo = ECDSACoder.verify(signJson(transaction).getBytes(), toBytes, Base64.decodeBase64(toSi));
            return verifyFrom && verifyTo;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //保存transaction到一个list集合中，最终等区块计算完（计算完才有当前区块的完整json串）吧list转成json传追加到当前计算的区块中（添加到区块的json中）

    @Override
    public void saveTransactionToList(Transaction json) {
        TransactonListOperatorUtils.addTransaction(json);
    }


//    public String signJson(Transaction transaction) {
//        SignTransaction signTransaction = new SignTransaction();
//        signTransaction.setTransactionId(transaction.getTransactionId());
//        signTransaction.setFrom(transaction.getFrom());
//        signTransaction.setTo(transaction.getTo());
//        signTransaction.setFromPubkey(transaction.getFromPubkey());
//        signTransaction.setToPubkey(transaction.getToPubkey());
//        signTransaction.setInclude(transaction.getInclude());
//        signTransaction.setTotal(transaction.getTotal());
//        signTransaction.setLink(transaction.getLink());
//        return JsonUtil.toJson(signTransaction);
//    }
    //考虑到json解析不同框架的排序问题，此处使用跟客户服务器一致框架
    public String signJson(Transaction p) {
        JSONObject json = new JSONObject();
        json.put("transactionId", p.getTransactionId());
        json.put("from", p.getFrom());
        json.put("to", p.getTo());
        json.put("fromPubkey", p.getFromPubkey());
        json.put("toPubkey", p.getToPubkey());
//        json.put("data", p.getData());
        json.put("include", p.getInclude());
        json.put("total", p.getTotal());
        json.put("link", p.getLink());
        return json.toJSONString();
    }

}

