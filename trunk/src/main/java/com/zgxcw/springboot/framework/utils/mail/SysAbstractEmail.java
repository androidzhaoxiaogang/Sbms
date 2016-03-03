package com.zgxcw.springboot.framework.utils.mail;
import java.io.*;
import java.util.*;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *
 * This abstract class implemented the method of adding receipt, setting
   subject body, and adding attachment.
 * It left the abstract method <code>send</code> for it subclass to
   implement.
 * </font>
 * <p>
 * Revision information:
 *      This is the first version of SysAbstractEmail
 * @author Lin Yu
 * @version @version1.0, 2016-01-14
 */

public abstract class SysAbstractEmail {

	public final static int EMAIL_SENT_SUCCESSFUL = 0;
	public final static int VALID_EMAIL = 0;
	public final static int MISS_TO_RECEIPT_ERROR = 1;
	public final static int MISS_ATTACHMENT_ERROR = 2;
	public final static int UNKNOWN_ERROR = 3;
	public final static int LOGIN_ERROR = 4;
	public final static int IO_ERROR = 5;
	public final static int INVALID_EMAIL_ADDRESS = 6;
	public final static int MESSAGE_ERROR = 7;
	protected String mvServerHost;
	protected String mvFrom;
	protected Vector mvToReceipt;
	protected Vector mvCcReceipt;
	protected Vector mvBccReceipt;
	protected String mvSubject;
	protected String mvBody;

	/**
	 * This map contains key of filename, and contain value for corresponding
	   file's absolute path
	 */
	protected Map mvAttachmentMap;

	/*
	public int getDeliveryPriority() 
	public int getDeliveryReport() 
	public int getImportance() */

	public abstract int send();

	protected SysAbstractEmail(String host) {
		this();
		mvServerHost = host;
	}
	protected SysAbstractEmail() {
		mvToReceipt = new Vector();
		mvCcReceipt = new Vector();
		mvBccReceipt = new Vector();
		mvAttachmentMap = new HashMap();
	}

	// get/set to receipts
	public String getFrom() {
		return mvFrom;
	}
	/**
	 * Set "FROM" email address
	 * @param pMailFrom from email address
	 */
	public void setFrom(String pMailFrom) {
		mvFrom = pMailFrom;
	}
	/**
	 * Get list of To receipient
	 * @return list of To receipient
	 */
	public String[] getToReceipts() {
		return (String[]) mvToReceipt.toArray(new String[mvToReceipt.size()]);
	}
	/**
	 * Add To receipient
	 * @param pReceipt To receipient email address
	 */
	public void addToReceipts(String pReceipt) {
		mvToReceipt.add(pReceipt);
	}
	/**
	 * Add list of To receipient
	 * @param pReceipt list of To receipient
	 */
	public void addToReceipts(Vector pReceipt) {
		addReceipt(mvToReceipt, pReceipt);
	}
	// get/set cc receipts
	/**
	 * Get list of Cc receipient
	 * @return list of cc receipient
	 */
	public String[] getCcReceipts() {
		return (String[]) mvCcReceipt.toArray(new String[mvCcReceipt.size()]);
	}
	/**
	 * Add Cc receipient 
	 * @param pReceipt Cc receipient
	 */
	public void addCcReceipts(String pReceipt) {
		mvCcReceipt.add(pReceipt);
	}
	/**
	 * Add list of Cc receipient
	 * @param pReceipt Cc reciepient
	 */
	public void addCcReceipts(Vector pReceipt) {
		addReceipt(mvCcReceipt, pReceipt);
	}
	// get/set bcc receipts
	/**
	 * Get list of Bcc receipient
	 * @return list of Bcc receipient
	 */
	public String[] getBccReceipts() {
		return (String[]) mvBccReceipt.toArray(new String[mvBccReceipt.size()]);
	}
	/**
	 * Add Bcc receipient 
	 * @param pReceipt bcc receipient email
	 */
	public void addBccReceipts(String pReceipt) {
		mvBccReceipt.add(pReceipt);
	}
	/**
	 * Add list of Bcc receipient 
	 * @param pReceipt list of Bcc receipient
	 */
	public void addBccReceipts(Vector pReceipt) {
		addReceipt(mvBccReceipt, pReceipt);
	}

	/**
	 * Get subject of mail
	 * @return subject of mail
	 */
	public String getSubject() {
		return mvSubject;
	}
	/**
	 * set subject of mail
	 * @param pSubject subject of email
	 */
	public void setSubject(String pSubject) {
		mvSubject = pSubject;
	}
	/**
	 * Get message body of email
	 * @return body of email
	 */
	public String getBody() {
		return mvBody;
	}
	/**
	 * Set the message body
	 * @param pBody message body of email
	 */
	public void setBody(String pBody) {
		mvBody = pBody;
	}

	/**
	 * @return all the attachments' filename (not absolute path)
	 */
	public String[] getAttachmentFilenames() {
		return (String[]) mvAttachmentMap.keySet().toArray(
			new String[mvAttachmentMap.size()]);
	}

	/**
	 * Get attachment path by filename
	 * @param pFilename filename of attachment
	 * @return attachment file path
	 */
	public String getAttachmentFileByName(String pFilename) {
		return mvAttachmentMap.get(pFilename) == null
			? null
			: (String) mvAttachmentMap.get(pFilename);
	}
	/**
	 * Add attachment by filepath
	 * @return true if attachment added succesfully, else false
	 */
	public boolean addAttachment(String pPath) {
		File lvFile = new File(pPath);
		if (!lvFile.exists() || !lvFile.isFile())
			return false;

		try {
			mvAttachmentMap.put(lvFile.getName(), lvFile.getCanonicalPath());
			return true;
		} catch (IOException ioe) {
			return false;
		}
	}
	/**
	 * Add attachment by InputStream
	 * @param pFilename filename of attachment
	 * @param pIs InputStream of attachment
	 * @return true if attachment added successfully, else false
	 */
	public boolean addAttachment(String pFilename, InputStream pIs) {
		final int lvByteSize = 1024;
		int lvByteRead = 0;
		byte[] lvData = new byte[lvByteSize];
		try {
			File lvTempFile;
			// determine temproray file for storing attachment
			if (pFilename == null) {
				lvTempFile = File.createTempFile("BFTEMPATTACH", ".DAT");
			} else {
				int dotpos = pFilename.lastIndexOf(".");
				String prefix;
				String suffix = "";
				if (dotpos != -1) {
					prefix = pFilename.substring(0, dotpos);
					suffix = pFilename.substring(dotpos);
					lvTempFile = File.createTempFile(prefix, suffix);
				} else
					lvTempFile = File.createTempFile(pFilename, "");
			}

			FileOutputStream lvFos = new FileOutputStream(lvTempFile);
			while ((lvByteRead = pIs.read(lvData)) > -1) {
				lvFos.write(lvData, 0, lvByteRead);
			}
			pIs.close();
			lvFos.close();
			mvAttachmentMap.put(pFilename, lvTempFile.getCanonicalPath());
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * Check error of email before send mail
	 * @return constant VALID_EMAIL if no error
	 */
	protected int checkValidEmail() {
		if (mvToReceipt.size() == 0)
			return MISS_TO_RECEIPT_ERROR;
		if (mvAttachmentMap.size() > 0 && false) {
			Iterator itr = mvAttachmentMap.keySet().iterator();
			Object fnameObj = null;
			File file = null;
			while (itr.hasNext()) {
				fnameObj = itr.next();
				file = new File((String) mvAttachmentMap.get(fnameObj));
				if (file.exists()) {
					file = null;
					continue;
				} else
					return MISS_ATTACHMENT_ERROR;
			}
		}

		// some email system cannot set subject to null, thus, set it to
		// null string
		if (mvSubject == null)
			mvSubject = "";
		if (mvBody == null)
			mvBody = "";
		return VALID_EMAIL;
	}

	// private method for adding receipts vector

	/**
	 * Copy email address from one vector to one
	 * @param pDstVector vector email address will be copied to 
	 * @param pSrcVector vector email address that will be copied from
	 */
	private void addReceipt(Vector pDstVector, Vector pSrcVector) {
		for (int i = 0; i < pSrcVector.size(); i++) {
			pDstVector.add(pSrcVector.elementAt(i));
		}
	}

	public static void main(String[] args) {
		/*
		AbstractEmail email = new AbstractEmail();
		email.addToReceipts("thomas");
		email.addToReceipts("haha");
		
		String[] tos = email.getToReceipts();
		for (int i=0; i<tos.length; i++) {
			System.out.println(tos[i]);	
		}*/
	}
}
