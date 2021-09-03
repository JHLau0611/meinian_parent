package com.atguigu.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * @author JHLau
 * @create 2021-08-25 0:58
 */
public class TestQiniu {
    @Test
    public void upload(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "USEkoOZeQytjGVH2tPqKWLe8T_8AfSXuGPBM-HJI";
        String secretKey = "fdNjyp8q0BEICJLBSmibsOhz2hgiqXk4Oe3SlMS2";
        String bucket = "0526jhl";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\JHLau\\Desktop\\Snipaste_2021-08-24_01-35-09.png";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }

    @Test
    public void delete(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释

        String accessKey = "USEkoOZeQytjGVH2tPqKWLe8T_8AfSXuGPBM-HJI";
        String secretKey = "fdNjyp8q0BEICJLBSmibsOhz2hgiqXk4Oe3SlMS2";

        String bucket = "0526jhl";
        String key = "Fr_D8rRh8NXIMGjPXB3cOPbcJ1dr";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

    }
}
