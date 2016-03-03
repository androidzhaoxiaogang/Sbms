package com.zgxcw.springboot.framework.utils.mail;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *      Class MailReceiver is used to process mail receive.
 * <p>
 * Revision information:
 *      This is the first version of MailReceiver
 * @author Lin Yu
 * @version 1.0, 2016-01-14
 */

import java.util.*;
import javax.mail.*;

public class MailReceiver {
	static Properties mvSysProperties = new Properties();
	static String POP_MAIL = "pop3";

	String mvPOP3Server;
	String mvLoginID;
	String mvLoginPassword;
	Session mvSession;
	Store mvStore;

	boolean mvDebug = false;

	public MailReceiver(
		String pPOP3Server,
		String pLoginID,
		String pLoginPassword) {
		this.mvPOP3Server = pPOP3Server;
		this.mvLoginID = pLoginID;
		this.mvLoginPassword = pLoginPassword;
	}

	public boolean login() throws NoSuchProviderException {
		mvSession = Session.getDefaultInstance(mvSysProperties, null);
		mvSession.setDebug(mvDebug);
		mvStore = mvSession.getStore(POP_MAIL);
		try {
			mvStore.connect(mvPOP3Server, -1, mvLoginID, mvLoginPassword);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void logout() throws MessagingException {
		if (mvStore != null)
			mvStore.close();
	}

	public Folder getFolder(String pFolderName) {
		try {
			return mvStore.getFolder(pFolderName);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Message[] getMessages(Folder pFolder) {
		try {
			pFolder.open(Folder.READ_WRITE);

			//to get the new messages.
			Message[] lvMessages = pFolder.getMessages();
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.FLAGS);
			fp.add("X-Mailer");
			pFolder.fetch(lvMessages, fp);
			return lvMessages;
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}

}