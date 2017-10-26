package org.cityu.cs.ian.model.bean;

import java.util.List;

public class BlockBackBean {

    private long blockTotalCount;
    private List<BlockBean> blockBeans;

    public long getBlockTotalCount() {
        return blockTotalCount;
    }

    public void setBlockTotalCount(long blockTotalCount) {
        this.blockTotalCount = blockTotalCount;
    }

    public List<BlockBean> getBlockBeans() {
        return blockBeans;
    }

    public void setBlockBeans(List<BlockBean> blockBeans) {
        this.blockBeans = blockBeans;
    }
}
