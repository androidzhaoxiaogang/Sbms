package com.zgxcw.springboot.framework.utils.file;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 解析xml工具类 
 * @author shichaomeng
 *
 */
public class XmlUtil {
	/**
	 * 解析xml返回跟标签
	 * @param xmlStr
	 * @return
	 */
	public static Element parserXml(String xmlStr){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Element root=null;
		try {
			db = dbf.newDocumentBuilder();
			Document dom = db.parse(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
			root = dom.getDocumentElement();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return root;
	}
	
	/**
	 * 解析请求参数
	 * 
	 * @param root
	 * @param childName
	 * @param maxLen
	 * @return
	 */
	public static String getChildValueByName(Element root, String childName) {
		NodeList nl = root.getChildNodes();
		String value = null;
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (childName.equals(node.getNodeName())) {
				value = "";
				try {
					value = node.getFirstChild().getNodeValue().trim();
				} catch (Exception e) {
					value = "";
				}
				break;
			}
		}
		return value;
	}

	/**
	 * 获取子标签
	 * 
	 * @param root
	 * @param childName
	 * @return
	 */
	public static Element getChildElement(Element root, String childName) {
		NodeList list = root.getElementsByTagName(childName);
		Element item = (Element) list.item(0);
		return item;
	}

	/**
	 * 获取元素列表
	 * 
	 * @param root
	 * @param rootName
	 * @param childName
	 * @return
	 */
	public static NodeList getNodeListByName(Element root, String rootName,
			String childName) {
		Element entityListE = getChildElement(root, rootName);
		NodeList entitys = entityListE.getElementsByTagName(childName);
		return entitys;
	}
	
	/**
	 * 创建XML标签
	 * 
	 * @param tag
	 * @param data
	 * @return
	 */
	public static String createXmlTag(String tag, String data) {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + tag + ">").append(data == null ? "" : data).append("</" + tag + ">")
				.append("\n");
		return sb.toString();
	}
	
	/**
	 * 样例
	 * @param args
	 */
	public static void main(String args[]){
		//xml
		StringBuffer sbf=new StringBuffer();
		sbf.append("<root>");
		sbf.append(createXmlTag("tagName","value"));
		sbf.append("</root>");
		System.out.println(sbf.toString());
		
		//解析标签
		/**
		 * <root>
		 * 	<transno>transno</transno>
		 * 	<biz>
		 * 		<date>20120123</date>
		 * 	</biz>
		 * 	<list>
		 * 		<obj>
		 * 			<bean>1</bean>
		 * 		</obj>
		 * 		<obj>
		 * 			<bean>2</bean>
		 * 		</obj>
		 * 	<list>
		 * </root>
		 * 
		 */
		
		//获取transno
//		getChildValueByName(root,"transno");
		
		//获取biz标签下的date
//		Element biz=getChildElement(root,"biz");
//		getChildValueByName(biz,"date");
		
		//获取list数据
//		NodeList entitys =getNodeListByName(root,"list","obj");
//		for (int i = 0; i < entitys.getLength(); i++) {
//			Element entity = (Element)entitys.item(i);
//			getChildValueByName(entity,"bean");
//		}
		
		
	}
	
}	
