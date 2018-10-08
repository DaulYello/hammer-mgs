package com.bm.fmkj.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;

/**
 * Created by wx.team on 2017/6/6.
 */
public class WxApiCall {
    private static Log log = LogFactoryImpl.getLog(WxApiCall.class);
    private static final int STATUS_OK = 200;
    private RequestModel model;

    public void setModel(RequestModel model) {
        this.model = model;
    }

    static {
        //默认连接超时10s,读取超时60s
        Unirest.setTimeouts(10*1000,60*1000);
    }


	/**
     * 发送请求
     * @return
     * @throws Exception
     */
    public String request(){
        HttpResponse<String> response;
        try{
            if ("get".equalsIgnoreCase(model.getMethod())) {
                response = Unirest.get(model.getGwUrl()).headers(model.getHeaderParams()).queryString(model.getQueryParams()).asString();
            } else {
                HttpRequestWithBody httpRequestWithBody = Unirest.post(model.getGwUrl()).headers(model.getHeaderParams()).queryString(model.getQueryParams());
                if (!isLegalBody()) {
                    log.error("the bodyStr and the filePath can't both have value");
                    return "";
                }
                if (RequestTools.isNotBlank(model.getBodyStr())) {
                    httpRequestWithBody.body(model.getBodyStr());
                }
                if (RequestTools.isNotBlank(model.getFilePath())) {
                    httpRequestWithBody.body(file2BetyArray(model.getFilePath()));
                }
                response = httpRequestWithBody.asString();
            }
            if (null != response && response.getStatus() == STATUS_OK) {
                return response.getBody();
            }
            log.error("error when request, the status is :" + (response == null ? "": response.getStatus()));
            return "";
        }catch (Exception e){
            log.error("error occur, please check you code",e);
        }
        return "";
    }

	/**
     * 判断body内容是否符合要求
     * @return
     */
    private boolean isLegalBody(){
        return RequestTools.isNotBlank(model.getBodyStr()) && RequestTools.isNotBlank(model.getFilePath()) ? false : true;
    }

    /**
     * 根据路径转换为字节数组
     * @param path 传递的路径
     * @return
     */
    public byte[] file2BetyArray(String path)
    {
        File file = new File(path);
        if (!file.exists()) {
            log.error("the file path is wrong!");
            return null;
        }
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            log.error("convert file to byte array",e);
        }
        return null;
    }
}
