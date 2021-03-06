package org.cityu.cs.ian.service.Threads.impl;

import javafx.util.converter.LongStringConverter;
import org.cityu.cs.ian.model.IBlockModel;
import org.cityu.cs.ian.model.bean.BlockBackBean;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction;
import org.cityu.cs.ian.model.bean.TransactionForWeb;
import org.cityu.cs.ian.service.Threads.ISyncBlockService;
import org.cityu.cs.ian.service.Threads.ITaskService;
import org.cityu.cs.ian.util.HttpUtils;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.cityu.cs.ian.util.unuse.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SyncBlockServiceImpl implements ISyncBlockService {
    /**
     * 只有该server开始下载了才可以接受区块保存到本地，否则会重复，比如在该server刚启动还未init的时候如果接收到post的block
     * 它在init的时候又会重新下载一次
     */
    public static AtomicBoolean isInit = new AtomicBoolean(false);
    @Autowired
    private IBlockModel blockModel;
    @Autowired
    private ITaskService taskService;
    /**
     * 记录所有的下载线程是否完成
     */
    public static AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void downloadBlock() {
        String containAllBlcokServerUrl = PropertiesUtil.readValue("config.properties", "containAllBlcokServerUrl");
        long blockHeight = getHeighestBlock(containAllBlcokServerUrl);
        if (blockHeight < 0) return;
        try {
            long currentServerTopBlockHeight = blockModel.getTopBlockHeight();
            int count = (int) (blockHeight - currentServerTopBlockHeight);
            isInit.getAndSet(true);//开始下载时改变该值，说明已经init。

            if (count <= 0) {
                taskService.powCalculate();
                return;
            }
            for (int i = (int) currentServerTopBlockHeight + 1; i <= (int) blockHeight; i++) {
                HttpUtils.getInstance().downLoadFileProgress(containAllBlcokServerUrl + "/block/syncBlock/" + i,
                        String.valueOf(i),
                        blockModel.getBlockPath(),
                        () -> integer.getAndIncrement()
                );
            }
            boolean stopListener = true;
            while (stopListener) {
                if (integer.get() == count) {
                    stopListener = false;
                    downloadBlock();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取总server上的最高区块高度
    private long getHeighestBlock(String containAllBlcokServerUrl) {
        if (!containAllBlcokServerUrl.contains("http://")) return -1;
        String s = HttpUtils.getInstance().requestByGetSync(containAllBlcokServerUrl + "/block/syncBlock/Highest");
        try {
            if (!StringUtil.issNullorEmpty(s)) {
                return new LongStringConverter().fromString(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void getBlockFileByName(String blockName) {

    }

    @Override
    public String getBlockJsonByHeight(long blockHeight) {
        if (blockHeight > blockModel.getTopBlockHeight()) {
            throw new IllegalArgumentException("该区块高度不存，已超出当前链的范围");
        }
        List<BlockBean> allBlockBeans = blockModel.getAllBlockBeans();
        final String[] blockJson = {""};
        allBlockBeans.forEach(blockBean -> {
            if (blockBean.getBlockHeight() == blockHeight) {
                blockJson[0] = JsonUtil.toJson(blockBean);
            }
        });
        return blockJson[0];
    }

    @Override
    public long getTopBlockHeight() {
        return blockModel.getTopBlockHeight();
    }

    @Override
    public String getTransactionById(String transactionId) {
        List<BlockBean> allBlockBeans = blockModel.getAllBlockBeans();
        final String[] transactionJson = {""};
        for (int i = 0; i < allBlockBeans.size(); i++) {
            List<Transaction> transactions = allBlockBeans.get(i).getTransactions();
            for (int j = 0; j < transactions.size(); j++) {
                Transaction transaction = transactions.get(j);
                if (transaction.getTransactionId().equals(transactionId)) {
                    transactionJson[0]= transformTransaction(transaction);
                }
            }
        }
        return transactionJson[0];
    }
    //吧transaction转换成前端需要的格式
    private String transformTransaction(Transaction p) {
        TransactionForWeb transactionForWeb = new TransactionForWeb();
        transactionForWeb.setTransactionId(p.getTransactionId());
        transactionForWeb.setData(p.getData());
        transactionForWeb.setFromPubkey(p.getFromPubkey());
        transactionForWeb.setToPubkey(p.getToPubkey());
        transactionForWeb.setInclude(p.getInclude());
        transactionForWeb.setIsSign(p.getIsSign());
        transactionForWeb.setLink(p.getLink());
        transactionForWeb.setSignatures(p.getSignatures());
        transactionForWeb.setToken(p.getToken());
        transactionForWeb.setTotal(p.getTotal());
        transactionForWeb.setType(p.getType());
        TransactionForWeb.FromBean fromBean = new TransactionForWeb.FromBean();
        fromBean.setFrom(p.getFrom());
        fromBean.setName(p.getTransactionId());
        TransactionForWeb.ToBean toBean = new TransactionForWeb.ToBean();
        toBean.setTo(p.getTo());
        toBean.setName(p.getTransactionId());
        transactionForWeb.setFrom(fromBean);
        transactionForWeb.setTo(toBean);
        return JsonUtil.toJson(transactionForWeb);
    }

    @Override
    public String getBlockJsonListByPage(int page) {
        if (page > blockModel.getTotalBlockCount() / 10) {
            return null;
        }
        List<BlockBean> allBlockBeans = blockModel.getAllBlockBeans();
        List<BlockBean> responseBeans = new ArrayList<>();
        int from = page * 10;
        int i = allBlockBeans.size() - from;
        if (i < 10) {
            while (i > 0) {
                responseBeans.add(allBlockBeans.get(i - 1));
                i--;
            }
        } else {
            for (int j = 0; j < 10; j++) {
                responseBeans.add(allBlockBeans.get(i - 1));
                i--;
            }
        }
        BlockBackBean blockBackBean = new BlockBackBean();
        blockBackBean.setBlockBeans(responseBeans);
        blockBackBean.setBlockTotalCount(blockModel.getTotalBlockCount());
        return JsonUtil.toJson(blockBackBean);
    }

    @Override
    public void toDownload(HttpServletResponse mResponse, String fileName, File file) {
        mResponse.setContentType("application/octet-stream");
        mResponse.setContentLength((int) file.length());
        byte[] buffer = new byte[8 * 1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            mResponse.setCharacterEncoding("UTF-8");
            mResponse.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ServletOutputStream os = mResponse.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
