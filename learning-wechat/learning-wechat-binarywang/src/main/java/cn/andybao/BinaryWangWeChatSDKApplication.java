package cn.andybao;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AndyBao
 * @description 代码王微信公众号SDK
 * @create_at ${DATE} ${TIME}
 * @since
 */
@RestController
@RequestMapping("/")
@SpringBootApplication
public class BinaryWangWeChatSDKApplication {
    @Autowired
    private WxMpService mpService;
    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    public static void main(String[] args) {
        SpringApplication.run(BinaryWangWeChatSDKApplication.class, args);
    }

    @GetMapping("/test")
    public void test() throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("ocxtU6Ah7ak2tUA3GZMsQPNTPhXo")
                .templateId("dyLk7-qGE0nTuuIb_vYtZAaXjj_IjAyH_Hx7R_GURXM")
                .url("https://u-jiaxiao.visioneschool.com")
                .build();

        templateMessage.addData(new WxMpTemplateData("first", "家长您好，你的孩子【学员姓名】未按时上课，请知悉！", "#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("keyword1", "缺勤"));
        templateMessage.addData(new WxMpTemplateData("keyword2", "2021-07-15 15:01"));
        templateMessage.addData(new WxMpTemplateData("keyword3", "大学语文提升班"));
        templateMessage.addData(new WxMpTemplateData("keyword4", "缺勤"));
        templateMessage.addData(new WxMpTemplateData("remark", "备注：点击查看详情", "#FF00FF"));
        WxMpTemplateMsgService templateMsgService = mpService.getTemplateMsgService();
        String msgId = templateMsgService.sendTemplateMsg(templateMessage);
        System.out.println(msgId);
    }
}