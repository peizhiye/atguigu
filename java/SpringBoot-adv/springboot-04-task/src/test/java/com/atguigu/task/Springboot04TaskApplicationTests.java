package com.atguigu.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        // 简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("17512080612@163.com");
        message.setFrom("534096094@qq.com");
        mailSender.send(message);
    }

    @Test
    public void test02() throws Exception {
        // 创建一个复杂的消息邮件（如：带附件）
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 不能直接操作 MimeMessage，要通过 MimeMessageHelper 来操作
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("通知-今晚开会");
        // 若要使 text 中的 html 标签生效，需要将 setText 方法的第2个参数设置为 true
        helper.setText("<b style='color:red'>今天 7:30 开会</b>", true);
        helper.setTo("17512080612@163.com");
        helper.setFrom("534096094@qq.com");
        // 上传文件
        helper.addAttachment("1.jpg", new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\1.jpg"));
        helper.addAttachment("2.jpg", new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\2.jpg"));
        mailSender.send(mimeMessage);
    }
}

