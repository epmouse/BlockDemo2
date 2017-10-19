package org.cityu.cs.ian.test;


import org.cityu.cs.ian.model.BlockModelImpl;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.impl.TaskService;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.SHA256;
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
        assert "000d9fd67c7f357f8c49abf2da30b1d059a66e631ffa4548bee34ef873b35dd4".equals(SHA256.getSHA256StrJava(TaskService.BASENACL + "1508322382103" + 1816));
    }

    @Test
    public void test3() {
        final BlockBean bean = new BlockBean();
        List<Transaction1> transaction1s = new ArrayList<>();
        List<Transaction1.Data> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {


            Transaction1 transaction1 = new Transaction1();
            for (int j = 0; j < 3; j++) {
                Transaction1.Data data = new Transaction1.Data();
                data.setItem(j + "我是dataitem"+i);
                data.setPrice(j + "1我是dataprice"+i);

            }
            transaction1.setData(datas);
            transaction1.setFrom(i+"from");
            transaction1.setFromPubkey(i+"from公钥");
            transaction1.setInclude(i+"include");
            transaction1.setIsSign(i+"true");
            transaction1.setLink(i+"link");
            transaction1.setSignatures(i+"signatures");
            transaction1.setTo(i+"to");
            transaction1.setToPubkey(i+"to公钥");
            transaction1.setTransactionId(i+"transactionId");
            transaction1.setTotal(i+"total");
            transaction1s.add(transaction1);
        }

        bean.setBlockHeight(10);
        bean.setTransaction1s(transaction1s);
        bean.setTransactionCount(transaction1s.size());
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