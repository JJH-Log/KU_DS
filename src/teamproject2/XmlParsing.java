package teamproject2;


import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParsing {
	public final static String baseUrl= "https://opendict.korean.go.kr/api/search?key=B68A5E214C83C3BB3AC7B02F21371FF2&part=word&method=exact&num=10&q=";
	private static String getTagValue(String tag,Element eElement) {
		if(eElement==null) return "-1";
		NodeList nList=eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue=(Node)nList.item(0);
		if(nValue==null) return null;
		return nValue.getNodeValue();
	}
	public boolean search(String word){
		try {
		Document documentInfo=DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(baseUrl+word);
		
		documentInfo.getDocumentElement().normalize();
		NodeList nList=documentInfo.getElementsByTagName("item");
		
		Node nNode=nList.item(0);
		Element eElement=(Element)nNode;
		return (getTagValue("word",eElement).equals(word));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
}
}
