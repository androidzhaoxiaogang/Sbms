package com.zgxcw.springboot.framework.utils.file;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *      interface FileOperator is used to promote file operation.
 * <p>
 * Revision information:
 *      This is the first version of FileOperator
 * @author Lin Yu
 * @version 1.0, 2016-01-14
 */

import java.io.*;

public class FileOperator {
    public FileOperator() {
    }

    /**
     * to move a source file to destination path.
     * @return -1  Fail
     * 	       -2  Source file is not existed
     *         0   Sucess
     */
    public static   int   moveFile(String pSourceFile,String pDestinationFile){

        try{
            BufferedReader lvReader=new BufferedReader(new FileReader(pSourceFile));
            BufferedWriter lvWriter=new BufferedWriter(new FileWriter(pDestinationFile));

            //copy source file to destination file.
            String lvDateLine=lvReader.readLine();
            while (lvDateLine!=null && lvDateLine.length()>0){
                lvWriter.write(lvDateLine,0,lvDateLine.length());
                lvWriter.write("\r\n");
                lvDateLine=lvReader.readLine();
            }
            lvReader.close();
            lvWriter.close();

            //delete old file.
            File lvSource=new File(pSourceFile);
            lvSource.delete();

            return  0;
        }
        catch(FileNotFoundException e){
            return  -2;
        }
        catch(Exception e){
            return  -1;
        }
    }
    
    /**
     * 清空指定目录下的所有文件。
     * @param lvDir  目录绝对路径。
     */
    public static void empty(String lvDir){
    	File lvCurrDir=new File(lvDir);
    	
		if (lvCurrDir!=null && lvCurrDir.isDirectory()){
			File[] lvFiles=lvCurrDir.listFiles();
			if (lvFiles!=null && lvFiles.length>0){
				for (int i=0;i<lvFiles.length;i++){
					lvFiles[i].delete();
				}
			}
		}
    }
    
  /**
   * 删除指定目录下带有过滤器的文件。
   * @param pDir  目录绝对路径
   * @param pFilter  过滤设置
   * @param pIncludedStr 文件名包含字符串
   */
	public static void deleteFiles(String pDir,FileFilter pFilter,String pIncludedStr){
		File lvCurrDir=new File(pDir);
    	
		if (lvCurrDir!=null && lvCurrDir.isDirectory()){
			File[] lvFiles=lvCurrDir.listFiles(pFilter);
			if (lvFiles!=null && lvFiles.length>0){
				for (int i=0;i<lvFiles.length;i++){
					if (lvFiles[i].getName().indexOf(pIncludedStr)!=-1){
						lvFiles[i].delete();
					}
				}
			}
		}
	}    

}