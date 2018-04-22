package com.example.springbootjpa;

import com.example.springbootjpa.utils.MailUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    @Autowired
    private MailUtils mailUtils;

    @Test
    public void contextLoads() {
        mailUtils.sendSimpleMail("18851200889@163.com", "test simple mail..", " hello this is simple mail");
    }

    @Test
    public void test() {

        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            email.setHostName("smtp.qq.com");
            // 字符编码集的设置
            email.setCharset("utf-8");
            //端口号，QQ邮箱需要使用SSL，端口号465或587
            email.setSmtpPort(587);
            // 收件人的邮箱
            email.addTo("826358610@qq.com");
            // 发送人的邮箱2
            email.setFrom("542869246@qq.com", "542869246@qq.com");
            // 如果需要认证信息的话，设置认证：用户名-密码     ***是你开启POP3服务时的授权码，不是登录密码
            email.setAuthentication("542869246@qq.com", "hyzcnxubndxzbfcj");
            // 要发送的邮件主题
            email.setSubject("Test...");
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg("spring boot测试发送邮件..");
            // 发送
            email.send();
            System.out.println("发送成功");
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("发送失败");

        }
    }

}
