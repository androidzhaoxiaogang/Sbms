package com.zgxcw.springboot.framework.utils.file;

import java.io.File;
import java.io.FileFilter;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *      interface ExtensionFileFilter is filter files.
 * <p>
 * Revision information:
 *      This is the first version of ExtensionFileFilter.
 * @author Lin Yu
 * @version 1.0, 2016-01-14
 */
public class ExtensionFileFilter implements FileFilter {

	private String extension;

	public ExtensionFileFilter(String extension) {
		this.extension = extension;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return false;
		}
		String name = file.getName();
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		} else if (index == name.length() - 1) {
			return false;
		} else {
			return this.extension.equals(name.substring(index + 1));
		}
	}

}
