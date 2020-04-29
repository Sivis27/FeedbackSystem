/*
 * package com.fms.email.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.mail.SimpleMailMessage; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.stereotype.Service;
 * 
 * import lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j
 * 
 * @Service public class testemail {
 * 
 * @Autowired JavaMailSender javaMailSender;
 * 
 * public void send() { SimpleMailMessage message = new SimpleMailMessage();
 * message.setTo("sivis"); log.info("1");
 * message.setFrom("sivismercury@gmail.com"); log.info("2");
 * message.setSubject("Participation.Please provided your feedback for EVM");
 * log.info("3"); message.setText("hi"); log.info("4");
 * javaMailSender.send(message); } }
 */