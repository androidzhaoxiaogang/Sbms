package com.zgxcw.springboot.framework.utils;

import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1"> (C) 2003 com.oberon.lin All rights reserved. <font size = "3">
 * <p>
 * Class ParameterProcessor is used to process the parameters posted from client brower.
 * <p>
 * Revision information: This is the first version of ParameterProcessor
 * 
 * @author Lin Yu
 * @version 1.0, 2003-05-31
 */
public class ParameterProcessor {

  /*
   * private SimpleDateFormat ivSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd"); private
   * ParsePosition ivParsePosition = new ParsePosition(0);
   */

  protected static GregorianCalendar cvDate = new GregorianCalendar();

  public ParameterProcessor() {}

  /**
   * to judge whether a string is not null and empty.
   * 
   * @param pStr
   * @return boolean true: YES false: NO
   */
  public static boolean isNotNullAndEmpty(String pStr) {
    if (pStr == null)
      return false;
    else if (pStr.trim().length() < 1)
      return false;
    else
      return true;
  }

  /**
   * to judge whether a string is null or empty.
   * 
   * @param pStr
   * @return boolean true: YES false��NO
   */
  public static boolean isNullOrEmpty(String pStr) {
    if (pStr == null)
      return true;
    else if (pStr.trim().length() < 1)
      return true;
    else
      return false;
  }

  /**
   * to get parameter with no space posted from the client browser.
   * 
   * @param pReq
   * @param pParameterName
   * @return parameter value
   */
  public static String getParameter(HttpServletRequest pReq, HttpServletResponse pRes,
      String pParameterName) {
    String lvParam = pReq.getParameter(pParameterName);
    if (lvParam == null)
      return lvParam;
    else {
      lvParam = pRes.encodeURL(lvParam.trim());
      return lvParam;
    }
  }

  /**
   * to judge whether a string is integer.
   * 
   * @param pParam
   * @return boolean true false
   */
  public static boolean isInteger(String pParam) {
    try {
      Integer.valueOf(pParam);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean isScopeInteger(Integer pParam, Integer pStartValue, Integer pEndValue) {
    try {
      if (pParam.compareTo(pStartValue) >= 0 && pParam.compareTo(pEndValue) <= 0)
        return true;
      else
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * to judge whether date is in the scope of the date.
   * 
   * @param pDate
   * @param pStartDate
   * @param pEndDate
   * @return boolean true��YES false��NO
   */
  public static boolean isScopeDate(Date pDate, Date pStartDate, Date pEndDate) {
    try {
      if (pDate.after(pStartDate) && pDate.before(pEndDate))
        return true;
      else
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Convert Float string to integer string
   */
  public static String convertNumberToInteger(String p1) {

    int iTemp = 0;
    try {
      java.lang.Float f1 = java.lang.Float.valueOf(p1);
      iTemp = f1.intValue();
    } catch (Exception e) {
      iTemp = 0;
    }
    return String.valueOf(iTemp);
  }

  /**
   * trim string make it no space at the first and last.
   * 
   * @param pStr
   * @return new String
   */
  public static String trimString(String pStr) {
    return (pStr != null ? pStr.trim() : null);
  }

  /**
   * trim string make it no space at the first and last.
   * 
   * @param pStr
   * @return string
   */
  public static String adjustString2(String pStr) {
    return (pStr != null ? pStr.trim() : "");
  }


  /**
   * to parse string date into date with date pattern.
   * 
   * @param pDate
   * @param pDatePattern
   * @return Date new Date
   */
  public static java.util.Date parseDate(String pDate, String pDatePattern) {
    SimpleDateFormat lvFormatter = new SimpleDateFormat(pDatePattern);
    java.util.Date lvDate = null;
    try {
      lvDate = lvFormatter.parse(pDate);
    } catch (Throwable e) {
    }
    return lvDate;
  }

  /**
   * to unparse date into date string with date pattern.
   * 
   * @param pDate
   * @param pDatePattern
   * @return new Date String
   */
  public static String unparseDate(java.util.Date pDate, String pDatePattern) {

    SimpleDateFormat lvSimpleDateFormat = new SimpleDateFormat(pDatePattern);
    return lvSimpleDateFormat.format(pDate);
  }

  /**
   * to convert 8859-1 to GBK.
   * 
   * @param pStr �ַ�
   * @return chinese string
   */
  public String getCHNStr(String pStr) {
    try {
      String temp_p = pStr;
      byte[] temp_t = temp_p.getBytes("ISO8859-1");
      String temp = new String(temp_t, "GBK");
      return temp;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Validate the Date Format
   */
  public boolean validateDateFormatter(String pDay, String pMonth, String pYear) {
    int lvDay;
    int lvMonth;
    int lvYear;

    // Simple Date valid without critical Error Message
    /*
     * String lvDate = pYear + "." + pMonth + "." + pDay; if (ivSimpleDateFormat.parse(lvDate,
     * ivParsePosition) == null) return false; else return true;
     */

    /**
     * Check whether Day is integer and can be convert
     */
    try {
      lvDay = Integer.parseInt(pDay);
    } catch (NumberFormatException lvException) {
      return false;
    }

    /**
     * Check whether Month is integer and can be convert
     */
    try {
      lvMonth = Integer.parseInt(pMonth);
    } catch (NumberFormatException lvException) {
      return false;
    }

    /**
     * Check whether Day is integer and can be convert
     */
    try {
      lvYear = Integer.parseInt(pYear);
    } catch (NumberFormatException lvException) {
      return false;
    }

    if (!isDateValid(lvDay, lvMonth, lvYear))
      return false;
    else
      return true;

  }

  /**
   * Checking whether Date is Valid
   */
  protected boolean isDateValid(int pDay, int pMonth, int pYear) {

    int lvdayspermonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if ((pMonth < 1) || (pMonth > 12)) {
      return false;
    }

    if (cvDate.isLeapYear(pYear))
      lvdayspermonth[2] = 29;

    if ((pDay < 1) || (pDay > lvdayspermonth[pMonth])) {
      return false;
    } else
      return true;
  }

  public boolean validateDateWithCondition(String pStartDate, String pEndDate, int pControl) {
    int lvStartDate;
    int lvEndDate;

    lvStartDate = Integer.parseInt(pStartDate);
    lvEndDate = Integer.parseInt(pEndDate);

    switch (pControl) {
      // Start Date must equal to End Date
      case 0:
        if (lvStartDate != lvEndDate) {
          return false;
        }
        break;

      // Start Date must be equal to or before End Date
      case 1:
        if (lvStartDate > lvEndDate) {
          return false;
        }
        break;

      // Start Date must be equal to or after End Date
      case 2:
        if (lvStartDate < lvEndDate) {
          return false;
        }
        break;

      // Start Date must be before End Date
      case 3:
        if (lvStartDate >= lvEndDate) {
          return false;
        }
        break;

      // Start Date must be after End Date
      case 4:
        if (lvStartDate <= lvEndDate) {
          return false;
        }
        break;
    }

    return true;
  }

  /**
   * extract all request parameters into a hashtable object.
   * 
   * @param request
   * @return parameter hash
   * @throws ServletException
   * @throws IOException
   */
  public Hashtable getAllParameter(HttpServletRequest request)
      throws ServletException, IOException {
    Enumeration e = request.getParameterNames();
    Hashtable hash = new Hashtable();
    while (e.hasMoreElements()) {
      String key = (String) e.nextElement();
      String values[] = request.getParameterValues(key);
      if (values.length == 1) {
        hash.put(key, values[0].trim());
      } else {
        String tmpValues[] = new String[values.length];
        for (int i = 0; i < values.length; i++)
          tmpValues[i] = values[i].trim();

        hash.put(key, tmpValues);
      }
    }
    return hash;
  }

  /**
   * adjust a string to leagal form.
   * 
   * @param pStr
   * @return new String adjusted
   */
  public static String adjustString(String pStr) {
    return pStr == null ? "-" : pStr.trim();
  }

  public static int getCharNum(char[] pStr, char pChar) {
    int count = 0;
    for (int i = 0; i < pStr.length; i++) {
      if (pStr[i] == pChar)
        count++;
    }
    return count;
  }

  /**
   * ȡ�ָ����ĸ���
   * 
   * @param pStr ԭ��
   * @param pSeperator �ָ���
   * @return ����
   */
  public static int getSeperatorNum(String pStr, String pSeperator) {
    int lvRet = 0;

    if (pStr.equals("") || pSeperator.equals(""))
      return 0;
    int lvBlockLen = pSeperator.length();
    for (int i = 0; i < pStr.length() - lvBlockLen; i++) {
      String lvTemp = pStr.substring(i, i + lvBlockLen);
      if (lvTemp.equals(pSeperator))
        lvRet++;
    }
    return lvRet;
  }

  /**
   * to split string.
   * 
   * @param pStr
   * @param pSeprator
   * @return new String Array
   */
  public static String[] splitString(String pStr, char pSeprator) {
    char[] lvCharStr = new char[pStr.length()];
    pStr.getChars(0, pStr.length(), lvCharStr, 0);

    int lvSepratorCount = getCharNum(lvCharStr, pSeprator);
    String[] lvRet = new String[lvSepratorCount + 1];

    int pos = 0;
    int index = 0;
    for (int i = 0; i < lvCharStr.length; i++) {
      if (lvCharStr[i] == pSeprator) {
        lvRet[index] = pStr.substring(pos, i);
        index++;
        pos = i + 1;
      }
    }
    lvRet[index] = pStr.substring(pos);
    return lvRet;
  }

  /**
   * �з��ַ�
   * 
   * @param pStr ԭ��
   * @param pSeprator �ָ��ַ�
   * @return �ַ�����
   */
  public static String[] splitString(String pStr, String pSeprator) {
    String[] lvRet = null;

    if (pSeprator.equals("") || pStr.equals(""))
      return new String[0];
    int lvSepratorCount = getSeperatorNum(pStr, pSeprator);
    lvRet = new String[lvSepratorCount + 1];

    int lvStart = 0;
    int lvBlockLen = pSeprator.length();
    int lvIndex = -1;
    for (int i = 0; i < pStr.length() - lvBlockLen; i++) {
      String lvTemp = pStr.substring(i, i + lvBlockLen);
      if (lvTemp.equals(pSeprator)) {
        lvIndex++;
        lvRet[lvIndex] = pStr.substring(lvStart, i);
        lvStart = i + lvBlockLen;
        i = i + lvBlockLen - 1;
      }
    }
    lvIndex++;
    lvRet[lvIndex] = pStr.substring(lvStart);

    return lvRet;
  }


  /**
   * replace char of the string with given char.
   * 
   * @param pStr Դ��
   * @param pOldChar ԭ�ִ�
   * @param pNewChar �滻Ϊ�Ĵ�
   * @return new String
   */
  public static String replaceCharsInString(String pStr, char pOldChar, char pNewChar) {
    char[] lvCharStr = new char[pStr.length()];
    pStr.getChars(0, pStr.length(), lvCharStr, 0);

    for (int i = 0; i < lvCharStr.length; i++) {
      if (lvCharStr[i] == pOldChar) {
        lvCharStr[i] = pNewChar;
      }
    }
    return new String(lvCharStr);
  }

  /**
   * �ַ��滻
   * 
   * @param text Դ��
   * @param orgtext ԭ��
   * @param destext �滻ΪĿ�괮
   * @return �µĴ�
   */
  private String replaceStr(String text, String orgtext, String destext) {
    StringBuffer sb = new StringBuffer();
    if (text == null)
      return "";

    int pos = 0;
    if (text.indexOf(orgtext, pos) == -1)
      return text;


    while (text.indexOf(orgtext, pos) != -1) {
      sb.append(text.substring(pos, text.indexOf(orgtext, pos))).append(destext);
      pos = text.indexOf(orgtext, pos) + orgtext.length();
    }

    if (pos < text.length())
      sb.append(text.substring(pos, text.length()));

    return sb.toString();
  }

  public static void main(String[] args) {
    String lvRet[] = ParameterProcessor.splitString("breast<br>hug<br>vest<br>fast", "<br>");
    for (int i = 0; i < lvRet.length; i++) {
      System.out.println("lvRet" + i + "=" + lvRet[i]);
    }

  }



}
