package org.cityu.cs.ian.test;


import org.cityu.cs.ian.model.BlockModelImpl;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction;
import org.cityu.cs.ian.service.Threads.impl.TaskService;
import org.cityu.cs.ian.util.JsonUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-core-test.xml"})
public class MyTest {
    @Autowired
    TaskService taskService;

    @Before
    public void init() {
        //在运行测试之前的业务代码
    }

    @After
    public void after() {
        //在测试完成之后的业务代码
    }

    String hash = "aaaaaaaaaaaaaaa";

    @Test
    public void test1() {
        taskService.powCalculate();
    }

    @Test
    public void test2() {



    }

    @Test
    public void test3() {
        final BlockBean bean = new BlockBean();
        List<Transaction> transactions = new ArrayList<>();
        List<Transaction.Data> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {


            Transaction transaction = new Transaction();
            for (int j = 0; j < 3; j++) {
                Transaction.Data data = new Transaction.Data();
                data.setItem(j + "我是dataitem"+i);
                data.setPrice(j + "1我是dataprice"+i);

            }
            transaction.setData(datas);
            transaction.setFrom(i+"from");
            transaction.setFromPubkey(i+"from公钥");
            transaction.setInclude(i+"include");
            transaction.setIsSign(i+"true");
            transaction.setLink(i+"link");
            transaction.setSignatures(i+"signatures");
            transaction.setTo(i+"to");
            transaction.setToPubkey(i+"to公钥");
            transaction.setTransactionId(i+"transactionId");
            transaction.setTotal(i+"total");
            transactions.add(transaction);
        }

        bean.setBlockHeight(10);
        bean.setTransactions(transactions);
        bean.setTransactionCount(transactions.size());
        bean.setTransactionCount(3);
        final BlockBean.BlockHeaderBean blockHeaderBean = new BlockBean.BlockHeaderBean();
        blockHeaderBean.setBlockHash("blockhash");
        blockHeaderBean.setMerkleRoot("merkleroot");
        blockHeaderBean.setNonce("nonce");
        blockHeaderBean.setPreviousHash("previoushash");
        blockHeaderBean.setRandomTime(12313);
        blockHeaderBean.setTimeStamp(33333);
        blockHeaderBean.setNextHash("");
        bean.setBlockHeader(blockHeaderBean);

        String json = JsonUtil.toJson(bean);

        BlockModelImpl blockModel=new BlockModelImpl();
        blockModel.saveBlockToLocal(json);
    }
}