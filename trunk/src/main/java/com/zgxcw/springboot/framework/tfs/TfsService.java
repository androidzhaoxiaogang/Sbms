package com.zgxcw.springboot.framework.tfs;

import java.io.OutputStream;

import com.taobao.common.tfs.packet.FileInfo;



/**
 * @Title: TfsServiceImpl.java
 * @Description: Tfs 基础服务接口
 * @Author: liangkaiqiang
 * @DateTime: 2015年10月8日下午8:03:35
 * @Version: 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.cn All Rights Reserved
 * @Company: 诸葛修车网
 */
public interface TfsService {
	
	/**
	 * 改变master ip地址，ip地址格式为 ip:port[:tfsarea] 
	 * @param ipaddr
	 * @return
	 */
	int setMasterIP(String ipaddr); 
	/**
	 * 查询master ip地址
	 * @return master ip地址
	 */
	String getMasterIP(); 
	/**
	 * 生成一个新的tfs文件名，如果失败，返回null
	 * @param suffix
	 * @return
	 */
	String newTfsFileName(String suffix); 	
	/**
	 * 读取一个tfs的文件到本地文件
	 * @param tfsFileName  需要读取的tfs文件名。
	 * @param tfsSuffix 需要读取的文件名后缀，需要和存入时后缀相同。
	 * @param localFileName 本地文件名。
	 * @return 读操作成功返回true，读操作失败返回false。
	 */
	boolean fetchFile(String tfsFileName, String tfsSuffix, String localFileName); 	
	/**
	 * 	读取一个tfs的文件到输出流
	 * @param tfsFileName 需要读取的tfs文件名。
	 * @param tfsSuffix  需要读取的文件名后缀，需要和存入时后缀相同。
	 * @param output   数据流。
	 * @return 读操作成功返回true，读操作失败返回false。
	 */
	boolean fetchFile(String tfsFileName, String tfsSuffix, OutputStream output); 
	/**
	 * 保存一个本地文件到TFS，失败返回null
	 * @param localFileName  本地文件名。
	 * @param tfsFileName  给文件指定一个TFS文件名，通常建议设为NULL。
	 * @param tfsSuffix  任意字符串，可设为NULL。
	 * @return 
	 */
	String saveFile(String localFileName, String tfsFileName, String tfsSuffix); 	
	/**
	 * 保存一个字节流data到TFS
	 * @param mainName 与前一个函数的参数tfsFileName相同，给文件指定一个TFS文件名，通常建议设为NULL。
	 * @param suffix  与前一个函数的参数tfsSuffix相同，任意字符串，可设为NULL。
	 * @param data 要写入的byte数组。
	 * @param offset 要写入的数据在data中的位置。
	 * @param length 要写入的数据长度。
	 * @return TFS文件名。
	 */
	String saveFile(String mainName, String suffix, byte[] data, int offset, int length); 	
	/**
	 * 保存一个字节流data到TFS
	 * @param data
	 * @param mainName
	 * @param suffix
	 * @return TFS文件名。
	 */
	String saveFile(byte[] data, String mainName, String suffix); 	
	/**
	 * stat一个tfs文件，成功返回FileInfo， 失败返回nul
	 * @param tfsFileName
	 * @param tfsSuffix
	 * @return
	 */
	FileInfo statFile(String tfsFileName, String tfsSuffix); 	
	/**
	 * 删除一个文件
	 * @param tfsFileName  需要读取的tfs文件名。
	 * @param tfsSuffix  需要读取的文件名后缀，需要和存入时后缀相同。
	 * @return 删除成功返回true，删除失败返回false。
	 */
	boolean unlinkFile(String tfsFileName, String tfsSuffix); 	
	/**
	 * 临时隐藏/反隐藏一个文件
	 * @param fileName
	 * @param tfsSuffix
	 * @param option
	 * @return
	 */
	boolean hideFile(String fileName, String tfsSuffix, int option); 	
	/**
	 * 排重保存文件
	 * @param localFileName
	 * @param tfsFileName
	 * @param tfsSuffix
	 * @return
	 */
	String saveUniqueFile(String localFileName, String tfsFileName, String tfsSuffix); 	
	/**
	 * 排重保存字节流
	 * @param mainName
	 * @param suffix
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	String saveUniqueFile(String mainName, String suffix, byte[] data, int offset, int length); 	
	/**
	 * 排重删除文件
	 * @param tfsFileName
	 * @param tfsSuffix
	 * @return
	 */
	int unlinkUniqueFile(String tfsFileName, String tfsSuffix); 	
	/**写大文件接口******************************************************************************************* */
	 /**
	 *保存一个大文件到tfs，成功返回tfs文件名(L开头），失败返回null
	 * @param localFileName
	 * @param tfsFileName
	 * @param tfsSuffix
	 */
	String saveLargeFile(String localFileName, String tfsFileName, String tfsSuffix);
	/**
	 * 保存一个字节流data到tfs，成功返回tfs文件名，失败返回null
	 * @param data
	 * @param tfsFileName
	 * @param tfsSuffix
	 * @return
	 */
	String saveLargeFile(byte[] data, String tfsFileName, String tfsSuffix,String key); 	
	/**
	 * 	保存一个字节流data到tfs，成功返回tfs文件名，失败返回null
	 * @param mainName
	 * @param suffix
	 * @param data
	 * @param offset
	 * @param length
	 * @param key
	 * @return
	 */
	String saveLargeFile(String mainName, String suffix, byte[] data, int offset, int length, String key); 
	/**数据多次读写接口***************************************************************************/
	/**
	 * 打开一个tfs文件读，成功返回大于0的fd，失败返回负值
	 * @param tfsFileName
	 * @param tfsSuffix
	 * @return
	 */
	int openReadFile(String tfsFileName, String tfsSuffix) ;	
	/**
	 * 读取不超过length大小的数据到一个字符数组，成功返回读取到的数据长度，失败返回负值。
	 * @param fd
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	int readFile(int fd, byte[] data, int offset, int length); 	
	/**
	 * 从文件的fileoffset开始，读取不超过length大小的数据到一个字符数组，成功返回读取到的数据长度，失败返回负值。
	 * @param fd
	 * @param fileOffset
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	int readFile(int fd, long fileOffset, byte[] data, int offset, int length);
	/**
	 * 打开一个tfs文件写，成功返回大于0的fd，失败返回负值
	 * @param tfsFileName
	 * @param tfsSuffix
	 * @param key
	 * @return
	 */
	int openWriteFile(String tfsFileName, String tfsSuffix, String key);
	/**
	 * 写data到tfs文件，成功返回写入的长度，失败返回负值
	 * @param fd
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	int writeFile(int fd, byte[] data, int offset, int length);
	/**
	 * 关闭tfs文件句柄，释放相关资源。成功返回最后tfs文件名，失败返回null
	 * @param fd
	 * @return
	 */
	String closeFile(int fd);
	/**
	 * 销毁占用的资源 (主进程调用一次） 
	 */
	void destroy(); 
}
