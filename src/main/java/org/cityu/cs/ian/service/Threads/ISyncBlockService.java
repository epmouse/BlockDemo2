package org.cityu.cs.ian.service.Threads;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface ISyncBlockService {
    /**
     * 初始化的时候调用，发现有未同步的区块则下载同步
     */
    void downloadBlock();
    void getBlockFileByName(String blockName);
    String getBlockJsonByHeight(long blockHeight);
    long getTopBlockHeight();
    String getTransactionById(String TransactionId);
    String getBlockJsonListByPage(int page);
    void toDownload(HttpServletResponse mResponse, String fileName, File file);
}
