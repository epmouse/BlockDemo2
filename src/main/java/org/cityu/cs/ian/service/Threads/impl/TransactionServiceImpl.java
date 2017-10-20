package org.cityu.cs.ian.service.Threads.impl;

import org.apache.commons.codec.binary.Base64;
import org.cityu.cs.ian.model.bean.SignTransaction;
import org.cityu.cs.ian.model.bean.Transaction;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.ECDSACoder;
import org.cityu.cs.ian.util.JsonUtil;
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
            boolean verifyFrom = ECDSACoder.verify(signJson(transaction).getBytes(), fromBytes, Base64.decodeBase64(fromSi));
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


    public String signJson(Transaction transaction) {
        SignTransaction signTransaction = new SignTransaction();
        signTransaction.setTransactionId(transaction.getTransactionId());
        signTransaction.setFrom(transaction.getFrom());
        signTransaction.setTo(transaction.getTo());
        signTransaction.setFromPubkey(transaction.getFromPubkey());
        signTransaction.setToPubkey(transaction.getToPubkey());
        signTransaction.setInclude(transaction.getInclude());
        signTransaction.setTotal(transaction.getTotal());
        signTransaction.setLink(transaction.getLink());
        return JsonUtil.toJson(signTransaction);
    }

}

