package com.zgxcw.springboot.framework.tfs.impl;

import java.io.OutputStream;

import com.taobao.common.tfs.DefaultTfsManager;
import com.taobao.common.tfs.packet.FileInfo;
import com.zgxcw.springboot.framework.tfs.TfsService;

/**
 * @Title: TfsServiceImpl.java
 * @Description: Tfs 实现类
 * @Author: liangkaiqiang
 * @DateTime: 2015年10月8日下午8:03:35
 * @Version: 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.cn All Rights Reserved
 * @Company: 诸葛修车网
 */
public class TfsServiceImpl implements TfsService {
	
	private DefaultTfsManager defaultTfsManager ;

	public void setDefaultTfsManager(DefaultTfsManager defaultTfsManager) {
		this.defaultTfsManager = defaultTfsManager;
	}

	public int setMasterIP(String ipaddr) {

		return defaultTfsManager.setMasterIP(ipaddr);
	}
	public String getMasterIP() {
		
		return defaultTfsManager.getMasterIP();
	}

	public String newTfsFileName(String suffix) {

		return defaultTfsManager.newTfsFileName(suffix);
	}
	
	public boolean fetchFile(String tfsFileName, String tfsSuffix,
			String localFileName) {

		return defaultTfsManager.fetchFile(tfsFileName, tfsSuffix, localFileName);
	}

	
	public boolean fetchFile(String tfsFileName, String tfsSuffix,
			OutputStream output) {

		return defaultTfsManager.fetchFile(tfsFileName, tfsSuffix, output);
	}
	
	public String saveFile(String localFileName, String tfsFileName,
			String tfsSuffix) {

		return defaultTfsManager.saveFile(localFileName, tfsFileName, tfsSuffix);
	}

	
	public String saveFile(String mainName, String suffix, byte[] data,
			int offset, int length) {

		return defaultTfsManager.saveFile(mainName, suffix, data, offset, length);
	}

	
	public String saveFile(byte[] data, String mainName, String suffix) {

		return defaultTfsManager.saveFile(data, mainName, suffix);
	}

	
	public FileInfo statFile(String tfsFileName, String tfsSuffix) {

		return defaultTfsManager.statFile(tfsFileName, tfsSuffix);
	}

	
	public boolean unlinkFile(String tfsFileName, String tfsSuffix) {

		return defaultTfsManager.unlinkFile(tfsFileName, tfsSuffix);
	}

	
	public boolean hideFile(String fileName, String tfsSuffix, int option) {

		return defaultTfsManager.hideFile(fileName, tfsSuffix, option);
	}

	
	public String saveUniqueFile(String localFileName, String tfsFileName,
			String tfsSuffix) {

		return defaultTfsManager.saveUniqueFile(localFileName, tfsFileName, tfsSuffix);
	}

	
	public String saveUniqueFile(String mainName, String suffix, byte[] data,
			int offset, int length) {

		return defaultTfsManager.saveUniqueFile(mainName, suffix, data, offset, length);
	}

	
	public int unlinkUniqueFile(String tfsFileName, String tfsSuffix) {

		return defaultTfsManager.unlinkUniqueFile(tfsFileName, tfsSuffix);
	}

	
	public String saveLargeFile(String localFileName, String tfsFileName,
			String tfsSuffix) {

		return defaultTfsManager.saveLargeFile(localFileName, tfsFileName, tfsSuffix);
	}

	
	public String saveLargeFile(byte[] data, String tfsFileName,
			String tfsSuffix, String key) {

		return defaultTfsManager.saveLargeFile(data, tfsFileName, tfsSuffix, key);
	}

	
	public String saveLargeFile(String mainName, String suffix, byte[] data,
			int offset, int length, String key) {

		return defaultTfsManager.saveLargeFile(mainName, suffix, data, offset, length, key);
	}

	
	public int openReadFile(String tfsFileName, String tfsSuffix) {

		return defaultTfsManager.openReadFile(tfsFileName, tfsSuffix);
	}

	
	public int readFile(int fd, byte[] data, int offset, int length) {

		return defaultTfsManager.readFile(fd, data, offset, length);
	}

	
	public int readFile(int fd, long fileOffset, byte[] data, int offset,
			int length) {

		return defaultTfsManager.readFile(fd, fileOffset, data, offset, length);
	}

	
	public int openWriteFile(String tfsFileName, String tfsSuffix, String key) {

		return defaultTfsManager.openWriteFile(tfsFileName, tfsSuffix, key);
	}

	
	public int writeFile(int fd, byte[] data, int offset, int length) {

		return defaultTfsManager.writeFile(fd, data, offset, length);
	}

	
	public String closeFile(int fd) {

		return defaultTfsManager.closeFile(fd);
	}

	
	public void destroy() {

		defaultTfsManager.destroy();
	}

	
	

}
