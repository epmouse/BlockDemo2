package org.cityu.cs.ian.service.Threads.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.cityu.cs.ian.model.IBlockModel;
import org.cityu.cs.ian.model.bean.Transaction;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.ECDSACoder;
import org.cityu.cs.ian.util.TransactonListOperatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private IBlockModel blockModel;

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
    public boolean saveTransactionToList(Transaction transaction) {
       return  TransactonListOperatorUtils.addTransaction(transaction);
    }

    @Override
    public boolean isRepetition(Transaction transactionIn) {
        final boolean[] flag = {false};

        blockModel.getAllBlockBeans().forEach(blockBean ->
                        blockBean.getTransactions().forEach(transaction ->
                        {
                            if (transaction.getTransactionId().equals(transactionIn.getTransactionId()) ) {
                                flag[0] = true;
                            }
                        }));

        return flag[0];
    }

    //考虑到json解析不同框架的排序问题，此处使用跟客户服务器一致框架
    public String signJson(Transaction p) {
        JSONObject json = new JSONObject();
        json.put("transactionId", p.getTransactionId());
        json.put("from", p.getFrom());
        json.put("to", p.getTo());
        json.put("fromPubkey", p.getFromPubkey());
        json.put("toPubkey", p.getToPubkey());
        json.put("include", p.getInclude());
//        json.put("total", p.getTotal());
//        json.put("link", p.getLink());
        json.put("type",p.getType());
        return json.toJSONString();
    }

}

