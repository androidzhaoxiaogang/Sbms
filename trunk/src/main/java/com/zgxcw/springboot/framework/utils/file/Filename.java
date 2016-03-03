package com.zgxcw.springboot.framework.utils.file;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *      This class assumes that the string used to initialize
 * fullPath has a directory path, filename, and extension.
 * The methods won't work if it doesn't.
 * <p>
 * Revision information:
 *      This is the first version of Filename
 * @author Lin Yu
 * @version 1.0, 2016-01-14
 */

public class Filename {
    private String fullPath;
    private char pathSeparator, extensionSeparator;

    public Filename(String str, char sep, char ext) {
        fullPath = str;
        pathSeparator = sep;
        extensionSeparator = ext;
    }

    public String extension() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        return fullPath.substring(dot + 1);
    }

    public String filename() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, dot);
    }

    public String path() {
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep);
    }
}
