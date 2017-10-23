package org.cityu.cs.ian.model;

import org.apache.commons.io.FileUtils;
import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.MyPathUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlockModelImpl implements IBlockModel {
    @Override
    public boolean saveBlockToLocal(String blockJson) {
        String rootPath = getBlockPath();
        File file = new File(rootPath + "/" + getCurrentBlockName());
        try {
            FileUtils.write(file, blockJson, "utf-8");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getBlockPath() {
//        return PropertiesUtil.readValue("config.properties", "block.localPath");
        return MyPathUtils.getBlockHome();
    }

    @Override
    public String getCurrentBlockName() {
        return (getTotalBlockCount() + 1) + "";
    }

    @Override
    public long getTopBlockHeight() {
        return getTotalBlockCount();
    }

    @Override
    public String getTopBlockHash() {
        File file = null;
        List<File> files = getAllBolckFiles();
        if (files != null & files.size() > 0) {
            file = files.get(files.size() - 1);
            try {
                String json = FileUtils.readFileToString(file, "utf-8");
                BlockBean blockBean = JsonUtil.fromJson(json, BlockBean.class);
                return blockBean.getBlockHeader().getBlockHash();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public long getTotalBlockCount() {
        List<File> files = getAllBolckFiles();
        if (files != null & files.size() > 0)
            System.out.println(files.get(files.size() - 1));
        return (long) files.size();
    }

    @Override
    public List<File> getAllBolckFiles() {
        return (List<File>) FileUtils.listFiles(new File(getBlockPath()), null, true);
    }

    @Override
    public List<BlockBean> getAllBlockBeans() {
        try {
            List<File> allBolckFiles = getAllBolckFiles();
            List<BlockBean> blockBeans = new ArrayList<>();
            for (File file : allBolckFiles) {
                String json = null;
                json = FileUtils.readFileToString(file, "utf-8");
                blockBeans.add(JsonUtil.fromJson(json, BlockBean.class));
            }
            return blockBeans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
