package org.cityu.cs.ian.service.Threads;

import org.cityu.cs.ian.model.bean.Transaction;

public interface ITransactionService {

    boolean verifySign(Transaction json);

    void saveTransactionToList(Transaction json);

}
