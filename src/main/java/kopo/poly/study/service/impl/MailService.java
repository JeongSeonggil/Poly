package kopo.poly.study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service(value = "MailService")
public class MailService {
    private Environment env;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public MailService(Environment env) {
        this.env = env;
    }

    @Async // 비동기 처리
    public void sendMail(String mailAddress, String title, String mailMessage) {
        /**
         * Gmail Setting
         * 2단계 인증 활성화 And 웹 비밀번호 발급
         * POP And IMAP 사용
         * */


        log.info(this.getClass().getName() + ".SendMail Start!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(mailAddress);
        message.setSubject(title);
        message.setText(mailMessage);

        javaMailSender.send(message);

        log.info(this.getClass().getName() + ".SendMail End!");
    }
}