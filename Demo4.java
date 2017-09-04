package homework;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Demo4 {
	
	public static void main(String[] args) {
		test4();
	}
	public static void test4() {

		try {
			// 读取XML文件
			Document doc = new SAXReader().read(new File("./src/contact.xml"));

			//直接获取根节点对象
			Element rootElem = doc.getRootElement();
			System.out.println(doc.getXMLEncoding());
			System.out.println();
			getChildNodes(rootElem);
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
	}

	private static void getChildNodes(Element elem) {
		// start
	/*	if (1 == elem.nodeCount()) {
			System.out.print(""+"<" + elem.getName() + ">");
		} else {
			System.out.println("<" + elem.getName() + ">");
		}*/
		
		System.out.print("<" + elem.getName());
		
		Iterator<Attribute> iterator = elem.attributeIterator();
		while (iterator.hasNext()) {
			Attribute attribute = iterator.next();
			System.out.print(" " + attribute.getName() + "=\"" +attribute.getValue()+"\"");
		}
		
		System.out.print(">");

		// 获取子节点的迭代器
		Iterator<Node> it = elem.nodeIterator();

		while (it.hasNext()) {
			Node node = it.next();

			if (node instanceof Element) {
				Element e = (Element) node;
				getChildNodes(e);
				
				// end
			} else {
				
				System.out.print(node.getStringValue());
			}
		}
		System.out.print("</" + elem.getName() + ">");
	}
}


