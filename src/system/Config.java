package system;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Config
{
	public static String host;
	public static String database;
	public static String user;
	public static String password;
	
	private static Element configNode;
	
	public static void loadConfig()
	{
		try
		{
			Document doc = getInitializedXML("config/config.xml");
			configNode = (Element)doc.getElementsByTagName("config").item(0);
			
			host = getNodeValue("host");
			database = getNodeValue("database");
			user = getNodeValue("user");
			password = getNodeValue("password");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static Document getInitializedXML(String path) throws SAXException, IOException, ParserConfigurationException
	{
		File file = new File("config/config.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		
		return doc;
	}
	
	private static String getNodeValue(String nodeName) 
	{
		String nodeValue = "";
		NodeList node = ((Element) configNode.getElementsByTagName(nodeName).item(0)).getChildNodes();
		
		nodeValue = ((Node) node.item(0)).getNodeValue();
		
		return nodeValue;
	}
}
