package org.cityu.cs.ian.test;


import org.cityu.cs.ian.model.BlockModelImpl;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.SignTransaction;
import org.cityu.cs.ian.model.bean.Transaction;
import org.cityu.cs.ian.util.JsonUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-core-test.xml"})
public class MyTest {

    @Before
    public void init() {
        //在运行测试之前的业务代码
    }

    @After
    public void after() {
        //在测试完成之后的业务代码
    }



//    @Test
//    public void test2() {
//        System.out.println(signJson());
////        System.out.println(signJson1());
//        System.out.println(signJson3());
//        assert signJson().equals(signJson3());
//
//    }
//    public String signJson3() {
//        JsonObject json =new JsonObject();
//        json.addProperty("transactionId", "transactionId");
//        json.addProperty("from", "from");
//        json.addProperty("to", "to");
//        json.addProperty("fromPubkey", "fromPubkey");
//        json.addProperty("toPubkey", "toPubkey");
////        json.put("data", p.getData());
//        json.addProperty("include", "include");
//        json.addProperty("total", "total");
//        json.addProperty("link", "link");
//        return json.toString();
//    }
//public String signJson3() {
//    JSONObject json = new JSONObject();
//    json.put("transactionId","transactionId");
//    json.put("from","from");
//    json.put("to", "to");
//    json.put("fromPubkey", "fromPubkey");
//    json.put("toPubkey", "toPubkey");
////        json.put("data", p.getData());
//    json.put("include", "include");
//    json.put("total", "total");
//    json.put("link", "link");
//    return json.toJSONString();
//}
    public String signJson() {
        SignTransaction signTransaction = new SignTransaction();
        signTransaction.setTransactionId("transactionId");
        signTransaction.setFrom("from");
        signTransaction.setTo("to");
        signTransaction.setFromPubkey("fromPubkey");
        signTransaction.setToPubkey("toPubkey");
        signTransaction.setInclude("include");
        signTransaction.setTotal("total");
        signTransaction.setLink("link");
        return JsonUtil.toJson(signTransaction);
    }
    public String signJson1() {
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
        stringStringLinkedHashMap.put("transactionId","transactionId");
        stringStringLinkedHashMap.put("from","from");
        stringStringLinkedHashMap.put("to","to");
        stringStringLinkedHashMap.put("fromPubkey","fromPubkey");
        stringStringLinkedHashMap.put("toPubkey","toPubkey");
        stringStringLinkedHashMap.put("include","include");
        stringStringLinkedHashMap.put("total","total");
        stringStringLinkedHashMap.put("link","link");
        return JsonUtil.toJson(stringStringLinkedHashMap);
    }
    public String signJson2() {

        Map<String, String> stringStringLinkedHashMap = new HashMap<>();
        stringStringLinkedHashMap.put("transactionId","transactionId");
        stringStringLinkedHashMap.put("from","from");
        stringStringLinkedHashMap.put("to","to");
        stringStringLinkedHashMap.put("fromPubkey","fromPubkey");
        stringStringLinkedHashMap.put("toPubkey","toPubkey");
        stringStringLinkedHashMap.put("include","include");
        stringStringLinkedHashMap.put("total","total");
        stringStringLinkedHashMap.put("link","link");
        return JsonUtil.toJson(stringStringLinkedHashMap);
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