package org.cityu.cs.ian.service.Threads.impl;

import org.cityu.cs.ian.model.IBlockModel;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.service.Threads.ISyncBlockService;
import org.cityu.cs.ian.util.HttpUtils;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.cityu.cs.ian.util.unuse.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.function.Consumer;

@Service
public class SyncBlockServiceImpl implements ISyncBlockService {
    @Autowired
    private IBlockModel blockModel;

    @Override
    public void downloadBlock() {
        String containAllBlcokServerUrl = PropertiesUtil.readValue("config.properties", "containAllBlcokServerUrl");
        String s = HttpUtils.getInstance().requestByGetSync(containAllBlcokServerUrl+"/block/syncBlock/Highest");
        if (StringUtil.issNullorEmpty(s)) {
            return;
        }
        long blockHeight = Long.valueOf(s);
        long currentServerTopBlockHeight = blockModel.getTopBlockHeight();
        for (long i = blockHeight; i <= currentServerTopBlockHeight; i++) {
            HttpUtils.getInstance().downLoadFileProgress(containAllBlcokServerUrl+"/block/syncBlock/"+i, String.valueOf(i), blockModel.getBlockPath());
        }
    }

    @Override
    public void getBlockFileByName(String blockName) {

    }

    @Override
    public String getBlockJsonByHeight(long blockHeight) {
        if(blockHeight>blockModel.getTopBlockHeight()){
            throw new IllegalArgumentException("该区块高度不存，已超出当前链的范围");
        }
        List<BlockBean> allBlockBeans = blockModel.getAllBlockBeans();
        final String[] blockJson = {""};
        allBlockBeans.forEach(blockBean -> {
            if(blockBean.getBlockHeight()==blockHeight){
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
    public String getTransactionById(String TransactionId) {
        return null;
    }

    @Override
    public String getBlockJsonListByPage(int page) {
        return null;
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
