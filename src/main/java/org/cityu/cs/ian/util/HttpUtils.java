package org.cityu.cs.ian.util;



import com.squareup.okhttp.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class HttpUtils {
    private OkHttpClient okhttp = new OkHttpClient();

    private HttpUtils() {
    }
    private volatile static HttpUtils instance;

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * get请求 同步方法
     *
     * @return
     */
    public String requestByGetSync(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okhttp.newCall(request).execute();//同步请求
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code" + response);
            }
            Response response1 = response.cacheResponse();
            Response r = response1;
            String string = response.body().string();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * post 同步请求
     *
     * @param map
     * @return
     */
    public String requestByPostSync(String mUrl, HashMap<String, String> map) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : map.keySet()) {
            formEncodingBuilder.add(key, map.get(key) + "");
        }
        RequestBody body = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url( mUrl)
                .post(body)
                .build();
        Response response = null;
        try {
            response = okhttp.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response + "请求失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 同步上传json串
     */
    public String postJson(String url, String json) {
        Request request = getUploadJsonRequest(url, json);
        try {
            Response response = okhttp.newCall(request).execute();
            if (response.isSuccessful()) {
                String s = response.body().string();
                return s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 异步上传json串
     */
    public void postJsonByAsync(String url, String json,Callback resposeCallback) {
        Request request = getUploadJsonRequest(url, json);
        okhttp.newCall(request).enqueue(resposeCallback);

    }

    private Request getUploadJsonRequest(String url, String json) {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, json);
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }


    /**
     * 请在ui线程调用该方法。
     * 带进度 下载文件    回调已返回主线程
     *
     * @param fileUrl     文件url
     * @param destFileDir 文件存储路径
     */
    public <T> void downLoadFileProgress(String fileUrl, final String fileName, final String destFileDir,MyHttpCallBack callBack) {

        File dir = new File(destFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        final File file = new File(destFileDir, fileName);
        if (file.exists()) {
            file.delete();
        }
        final Request request = new Request.Builder()
                .url(fileUrl)
                .build();
        final Call call = okhttp.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println("当前线程id为"+Thread.currentThread().getId());
                InputStream is = null;
                byte[] buf = new byte[1024];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    callBack.onSuccess();
                } catch (final IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public interface MyHttpCallBack{
        void onSuccess();
    }
}