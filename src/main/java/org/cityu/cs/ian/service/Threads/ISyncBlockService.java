package org.cityu.cs.ian.service.Threads;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface ISyncBlockService {
    /**
     * 初始化的时候调用，发现有未同步的区块则下载同步
     * 下载逻辑：如果下载完成总服务上又有了新的区块，则继续下载
        知道与总服务器彻底同步才开始计算，防止下载的过程中，总服务器又生成了新的区块，导致不同步
     */
    void downloadBlock();
    void getBlockFileByName(String blockName);
    String getBlockJsonByHeight(long blockHeight);
    long getTopBlockHeight();
    String getTransactionById(String TransactionId);
    String getBlockJsonListByPage(int page);
    void toDownload(HttpServletResponse mResponse, String fileName, File file);
}
