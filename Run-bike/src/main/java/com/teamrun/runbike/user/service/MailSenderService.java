package com.teamrun.runbike.user.service;

import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.teamrun.runbike.user.dao.UserDao;
import com.teamrun.runbike.user.domain.UserInfo;

@Service("mailService")
public class MailSenderService implements UserService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private SqlSessionTemplate template;
	
	private UserDao dao;
	
	public void mailSend(String u_id, String u_code,String u_name) {	
		MimeMessage message = sender.createMimeMessage();
		
		try {
			message.setSubject("[RUN BIKE] 이메일 계정을 인증해주세요. ("+u_id+")","utf-8");
			String html = "<h1>회원가입을 축하드립니다.</h1><p>안녕하세요. "+u_name+" 님. <br> 이메일 계정을 인증 받으시려면 아래 링크를 클릭해주세요.</p><a href='http://localhost:8080/runbike/verify?id="+u_id+"&code="+u_code+"'>이메일 인증하기</a>";
			message.setText(html, "utf-8", "html");
			message.setFrom(new InternetAddress("j35147@naver.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(u_id," 고객님","utf-8"));
			
			sender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void mailResend(String u_id) {
		dao = template.getMapper(UserDao.class);
		
		UserInfo userInfo = dao.selectUserById(u_id);
		
		System.out.println("resend 메일 userInfo : "+userInfo);
		
		MimeMessage message = sender.createMimeMessage();
		
		try {
			message.setSubject("[RUN BIKE] 이메일 계정을 인증해주세요. ("+u_id+")","utf-8");
			String html = "<h1>이메일 재인증</h1><p>안녕하세요. "+userInfo.getU_name()+" 님. <br> 이메일 계정을 인증 받으시려면 아래 링크를 클릭해주세요.</p><a href='http://localhost:8080/runbike/verify?id="+u_id+"&code="+userInfo.getU_code()+"'>이메일 인증하기</a>";
			message.setText(html, "utf-8", "html");
			message.setFrom(new InternetAddress("j35147@naver.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(u_id," 고객님","utf-8"));
			
			sender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
