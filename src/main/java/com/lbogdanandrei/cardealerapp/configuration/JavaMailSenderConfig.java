package com.lbogdanandrei.cardealerapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSenderConfig {
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mail = new JavaMailSenderImpl();
        mail.setHost("smtp.mailtrap.io");
        mail.setPort(2525);
        mail.setUsername("83c1404e3ff6d5");
        mail.setPassword("c5fbf379659873");

        return mail;
    }
}
