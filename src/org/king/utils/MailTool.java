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
		Properties props = new Properties(); // ���Լ���һ�������ļ�
				
		String currentPath = MailTool.class.getResource ("").getPath();		
		currentPath = currentPath.substring(0,currentPath.indexOf("classes"));		
		FileInputStream fis = new FileInputStream(currentPath + "\\mail-property.xml");
 		props.loadFromXML(fis);
	
		Session session = Session.getInstance(props);// ���������½�һ���ʼ��Ự
		MimeMessage message = new MimeMessage(session);// ���ʼ��Ự�½�һ����Ϣ����
		message.setRecipients(Message.RecipientType.BCC, toMailList);// �����ռ���,���������������ΪTO
		message.setSubject(mailTitle);// ���ñ���
	
		// message.setText(mailContent); //���� ���ı� �ʼ�
		message.setContent(mailContent, "text/html;charset=gbk"); // ����HTML�ʼ���������ʽ�ȽϷḻ
		message.setSentDate(new Date());// ���÷���ʱ��
		message.saveChanges();// �洢�ʼ���Ϣ

		Transport transport = session.getTransport("smtp");
	
		transport.connect(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
		transport.sendMessage(message, message.getAllRecipients());// �����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
		transport.close();
	}
	
	public static void main(String[] args)
	{
		try {
			MailTool.sendCheckTaskMail("logichain@163.com;44620965@qq.com","��������","�������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
