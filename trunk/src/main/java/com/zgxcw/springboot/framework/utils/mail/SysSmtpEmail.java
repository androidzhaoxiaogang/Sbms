package com.zgxcw.springboot.framework.utils.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *
 * This class was implemented for sending email through SMTP. Some method was overrided
 * to tackle the behaviour of SMTP, e.g. <code>setFrom</code> was overrided.
 *
 * If user input an invalid email address(user is not exist in server), e.g. "thomastsoi"
 * the <code>send</code> method will still return 0 (will not throw AddressException).
 * Although user has set the correct "From", if the mail cannot be delivery. The behaviour
 * observed is the "From" person will not receive a notification mail.
 *
 * For class InternetAddress will not throw AddressException although user enter an 
 * invalid email, e.g. "thomastsoi". While white space is not allowed, e.g. "thomas tsoi".
 *
 * Three jars are required for this class : activation.jar, smtp.jar, and mailapi.jar 
 * </font>
 * <p>
 * Revision information:
 *      This is the first version of SysAbstractEmail
 * @author Lin Yu
 * @version @version1.0, 2016-01-14
 */

public class SysSmtpEmail extends SysAbstractEmail {
	Session mvSession = null;

	public SysSmtpEmail(String host) {
		super(host);
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		mvSession = Session.getDefaultInstance(props, null);
		mvSession.setDebug(false);
	}
	public SysSmtpEmail(String host, String pMailFrom) {
		this(host);
		setFrom(pMailFrom);
	}

	/**
	 * Validate the "From" email address. If we do not check here, 
	 * AddressException will throw.
	 * We do not check the Recipient email address. It is because if 
	 * user set wrong email, <code>send</code> method will return 
	 * INVALID_EMAIL_ADDRESS (6).
	 */
	public void setFrom(String pMailFrom) {
		// we will use Message.setFrom() if we found pMailFrom is null
		if (pMailFrom == null) {
			super.setFrom(null);
			return;
		}
		int atPos = pMailFrom.indexOf('@');
		if (atPos == -1 || atPos == 0 || atPos == pMailFrom.length() - 1) {
			throw new IllegalArgumentException("@ character missed.\nFrom address should be in format of xxx@yyy");
		}
		super.setFrom(pMailFrom);
	}
	/**
	 * Implement the abstract method <code>send</code>
	 */
	public int send() {
		int retCode = checkValidEmail();
		if (retCode != 0)
			return retCode;

		try {
			Message lvMsg = new MimeMessage(mvSession);
			addReceipt(mvToReceipt, Message.RecipientType.TO, lvMsg);
			addReceipt(mvCcReceipt, Message.RecipientType.CC, lvMsg);
			addReceipt(mvBccReceipt, Message.RecipientType.BCC, lvMsg);

			if (mvFrom == null)
				lvMsg.setFrom();
			else {
				lvMsg.setFrom(new InternetAddress(mvFrom));
			}
			lvMsg.setSubject(mvSubject);
			lvMsg.setSentDate(new Date());

			// construct the message body
			MimeBodyPart[] lvParts = attachment2MimeBody();
			if (lvParts.length == 0) {
				lvMsg.setText(mvBody);
			} else {
				// add text body
				Multipart mp = new MimeMultipart();
				MimeBodyPart body = new MimeBodyPart();
				// insert line such that text and attachment will not overlap
				body.setText(mvBody + "\r\n");
				mp.addBodyPart(body);
				// add attachment
				for (int i = 0; i < lvParts.length; i++)
					mp.addBodyPart(lvParts[i]);
				lvMsg.setContent(mp);
			}
			Transport.send(lvMsg);
			return 0;
		} catch (AddressException ae) {
			ae.printStackTrace();
			return INVALID_EMAIL_ADDRESS;
		} catch (MessagingException me) {
			// in some case, use input a invalid email will cause the statement above
			// to throw MessagingException
			me.printStackTrace();
			return MESSAGE_ERROR;
		}
	}

	private void addReceipt(
		Vector pReceipts,
		Message.RecipientType pType,
		Message pMsg)
		throws AddressException, MessagingException {
		int size = pReceipts.size();
		for (int i = 0; i < size; i++)
			// may need to validate user email 
			pMsg.addRecipient(
				pType,
				new InternetAddress(pReceipts.elementAt(i).toString()));
	}

	/**
	 * Convert from attachment hashtable to MimeBodyPart[]
	 */
	private MimeBodyPart[] attachment2MimeBody() throws MessagingException {
		if (mvAttachmentMap.size() == 0)
			return new MimeBodyPart[0];

		Iterator itr = mvAttachmentMap.keySet().iterator();
		Object obj = null;
		String filename = null;
		int index = 0;
		MimeBodyPart bodyParts[] = new MimeBodyPart[mvAttachmentMap.size()];
		while (itr.hasNext()) {
			obj = itr.next();
			filename = mvAttachmentMap.get(obj).toString();
			bodyParts[index] = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(filename);
			bodyParts[index].setDataHandler(new DataHandler(fds));
			// if the attachment is an inputstream, then the filename is a temp filename
			// therefore, we should use the key (filename) of <code>mvAttachmentMap</code>
			bodyParts[index].setFileName(obj.toString());
			index++;
		}
		return bodyParts;
	}

	public static void main(String args[]) {
		if (args.length < 5) {
			System.out.println(
				"Usage: java SysSmtpEmail host from to subject body [attachment path]");
			return;
		}
		SysSmtpEmail email =
			new SysSmtpEmail(args[0], args[1].equals("") ? null : args[1]);
		email.addToReceipts(args[2]);
		email.setSubject(args[3]);
		email.setBody(args[4]);
		int index = 5;
		while (index < args.length) {
			System.out.print("Add attachment " + args[index] + " ? : ");
			System.out.println(email.addAttachment(args[index++]));
		}

		// The following is for testing of inputstream
		/*
		try {
			FileInputStream is = new FileInputStream("c:/SCANDISK.LOG");			
			email.addAttachment("scan.log", is);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}*/
		System.out.print("Return result of send(): ");
		System.out.println(email.send());
	}
}
