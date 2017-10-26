package org.cityu.cs.ian.service.Threads;

import org.cityu.cs.ian.model.bean.Transaction;

public interface ITransactionService {

    boolean verifySign(Transaction json);

    boolean saveTransactionToList(Transaction json);

    /**
     * 是否是重复添加，验证整个链上的重复性
     * @param transactionIn
     * @return
     */
    boolean isRepetition(Transaction transactionIn);

}
