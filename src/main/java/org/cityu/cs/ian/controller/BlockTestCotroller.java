package org.cityu.cs.ian.controller;

import org.cityu.cs.ian.model.bean.BlockBean;
import org.cityu.cs.ian.model.bean.Transaction1;
import org.cityu.cs.ian.service.Threads.IBlockAcceptService;
import org.cityu.cs.ian.service.Threads.ISyncBlockService;
import org.cityu.cs.ian.service.Threads.ITaskService;
import org.cityu.cs.ian.service.Threads.ITransactionService;
import org.cityu.cs.ian.util.Constant;
import org.cityu.cs.ian.util.JsonUtil;
import org.cityu.cs.ian.util.PropertiesUtil;
import org.cityu.cs.ian.util.ResponseMsgUtils;
import org.cityu.cs.ian.util.unuse.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("block")
public class BlockTestCotroller {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBlockAcceptService blockAcceptService;
    @Autowired
    private ISyncBlockService syncBlockService;

    /**
     * 初始化接口
     */
    @RequestMapping("init")

    public void init(){
        taskService.powCalculate();//初始化触发计算
        syncBlockService.downloadBlock();
    }


    @RequestMapping(value = "add/transaction", method = RequestMethod.POST)
    @ResponseBody
    public String transactionAccept(@RequestBody Transaction1 transaction1) {
        if (transactionService.verifySign(transaction1)) {
            transactionService.saveTransactionToList(transaction1);
            return Constant.SUCCESS_RESPONSE;
        }
        return Constant.ERR_RESPONSE;
    }

    @RequestMapping(value = "add/block", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> acceptBlock(@RequestBody BlockBean block) {
        if (blockAcceptService.verifyBlock(block)) {
            blockAcceptService.interruptPow();
            boolean b = blockAcceptService.saveBlock(block);
            if (b) {
                return ResponseMsgUtils.getResponseMap(true, null);
            } else {
                System.out.println("BlockAcceptServicee中saveBlock（）方法文件写入失败，请查看异常");

                return ResponseMsgUtils.getResponseMap(false,"文件写入失败");
            }
        } else {
            return ResponseMsgUtils.getResponseMap(false,"block验证失败");
        }
    }

    @RequestMapping(value = "syncBlock/{blockName}", method = RequestMethod.GET)
    @ResponseBody
    public String syncBlocks(@PathVariable("blockName") String blockName,HttpServletResponse mResponse) {
        if(StringUtil.issNullorEmpty(blockName)){
            return  Constant.ERR_RESPONSE;
        }
        if (blockName != null) {
            String blockFolderPath= PropertiesUtil.readValue("config.properties","block.localPath");
            File file = new File(blockFolderPath+"/"+blockName);
            if (file.exists()) {
                syncBlockService.toDownload(mResponse, blockName, file);
                return Constant.SUCCESS_RESPONSE;
            }
        }
        return Constant.ERR_RESPONSE;
    }

    /**
     * 返回目前最高区块的高度，方便其他server根据目前的缺失情况，循环调用下载接口，下载区块
     * @return
     */
    @RequestMapping(value = "syncBlock/Highest", method = RequestMethod.GET)
    @ResponseBody
    public long getTopBlockHeight() {
       return syncBlockService.getTopBlockHeight();
    }
    @RequestMapping(value = "syncBlock/blockDetail", method = RequestMethod.GET)
    @ResponseBody
    public String getBlockByHeight(String blockName) {
        //todo-根据blockheight 查询出对应block  返回json

        return "";
    }
    @RequestMapping(value = "syncBlock/getTransaction", method = RequestMethod.GET)
    @ResponseBody
    public String getTransaction(String transactionid) {
        //todo-根据transactionid查询这条transactiong的信息 返回 json

        return "";
    }
    @RequestMapping(value = "syncBlock/all", method = RequestMethod.GET)
    @ResponseBody
    public String getAllBlockForClient(int page) {  //1为最新的10条,依次往下
        //todo-给客户端提供所有block的json串。（考虑分页）从后往前，先发最新的
        return "";
    }

//todo-除以上contrller操作外 还需要做调用下载和获取最高区块的两个接口的操作



//    1、同步block  下载没有的blcok文件。（1提供下载接口， 2下载并保存）
//    2、给客户端提供所有block的json串。（考虑分页）从后往前，先发最新的
//    3、根据transactionid查询这条transactiong的信息 返回 json
//    4、根据blockheight 查询出对应block  返回json


}

