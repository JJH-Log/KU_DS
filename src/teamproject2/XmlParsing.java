package teamproject2;


import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlParsing {
	public final static String baseUrl= "https://opendict.korean.go.kr/api/search?key=B68A5E214C83C3BB3AC7B02F21371FF2&part=word&method=exact&num=10&q=";
	public boolean search(String word){
		try {
		Document documentInfo=DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(baseUrl+word);
		
		documentInfo.getDocumentElement();
		Node nNode=documentInfo.getElementsByTagName("word").item(0);

		if(nNode==null) return false;
		else {
			System.out.println(nNode.getTextContent()==word);
			return (nNode.getTextContent().equals(word));
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
