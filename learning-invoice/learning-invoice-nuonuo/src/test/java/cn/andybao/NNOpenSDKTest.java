package cn.andybao;

import nuonuo.open.sdk.NNOpenSDK;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author AndyBao
 * @description
 * @create_at 2022/11/23 18:08
 * @since
 */
class NNOpenSDKTest {
    /**
     * 自用型应用获取令牌
     */
    @Test
    void getIntance() {
        String json = NNOpenSDK.getIntance().getMerchantToken("appKey", "appSecret");
    }

    /**
     * 发送报文请求
     */
    @Test
    void sendPostSyncRequest() {
        NNOpenSDK sdk = NNOpenSDK.getIntance();
        String taxnum = "23***789";//ISV下授权商户税号，自用型应用置""即可
        String appKey = "Hn***XL";
        String appSecret = "F65***65F";
        String method = "nuonuo.***";//API方法名
        String token = "2d484e**************pdui";//访问令牌
        String content = "{\"city\":\"广州市\",  …,  \"maxid\":\"0\"}";//API私有请求参数
        String url = "https://sdk.nuonuo.com/open/v1/services"; //SDK请求地址
        String senid = UUID.randomUUID().toString().replace("-", ""); // 唯一标识，由企业自己生成32位随机码
        String json = sdk.sendPostSyncRequest(url, senid, appKey, appSecret, token, taxnum, method, content); // 请求API
        // 响应示例
        // {"code":"200","describe":"调用成功","result":"****"}
    }
}
