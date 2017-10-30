package org.king.utils;

import java.io.FileInputStream;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MailTool {
	public final static String MAIL_ADDRESS_SEPARATER = "," ;
	
	public static void sendCheckTaskMail(String toMailList, String mailTitle,String mailContent) throws Exception {
		System.err.println("send mail List---" + toMailList);
		Properties props = new Properties(); // 可以加载一个配置文件
				
		String currentPath = MailTool.class.getResource ("").getPath();		
		currentPath = currentPath.substring(0,currentPath.indexOf("classes"));		
		FileInputStream fis = new FileInputStream(currentPath + "\\mail-property.xml");
 		props.loadFromXML(fis);
	
		Session session = Session.getInstance(props);// 根据属性新建一个邮件会话
		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象
		message.setRecipients(Message.RecipientType.BCC, toMailList);// 设置收件人,并设置其接收类型为TO
		message.setSubject(mailTitle);// 设置标题
	
		// message.setText(mailContent); //发送 纯文本 邮件
		message.setContent(mailContent, "text/html;charset=gbk"); // 发送HTML邮件，内容样式比较丰富
		message.setSentDate(new Date());// 设置发信时间
		message.saveChanges();// 存储邮件信息

		Transport transport = session.getTransport("smtp");
	
		transport.connect(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
		transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	}
	
	public static void main(String[] args)
	{
		try {
			MailTool.sendCheckTaskMail("logichain@163.com;44620965@qq.com","评审任务","评审对象");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
