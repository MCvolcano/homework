package homework;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/*<contactlist>
<user id="001">
	<name>李凉凉</name>
	<age>18</age>
	<tel>13488886666</tel>
	<email>Levelee@163.com</email>
	<qq>768678</qq>
</user>
<user id="002">
	<name>李狗蛋</name>
	<age>21</age>
	<tel>2138</tel>
	<email>Lee@163.com</email>
	<qq>123123123</qq>
</user>
</contactlist>*/
public class Demo {	
	public static void main(String[] args) {
		File file = new File("./src/contact.xml");
		getXMLFile(file);
	}
	
	public static void getXMLFile(File file) {
		try {
			//读取XML文件，创建document对象
			Document doc = new SAXReader().read(file);
			
			Element rootElement = doc.getRootElement();
			
			getChild(rootElement);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private static void getChild(Element elem) {
		String s = "<" + elem.getName();
		//<标签节点 + 属性节点>
		Iterator<Attribute> iterator = elem.attributeIterator();
		while (iterator.hasNext()) {
			Attribute a = iterator.next();
			s += " " + a.getName() + "=\"" + a.getValue() +"\"";
		}
		System.out.print(s + ">");
		
		//文本节点   判断当前节点下是否有节点，没有则输出内容
		/*if (elem.nodeCount() == 1) {
			System.out.print(elem.getStringValue());			
		}*/
	
		//迭代
		Iterator<Node> it = elem.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (node instanceof Element) {
				Element element = (Element)node;
				getChild(element);
			} else {
				//读到文本节点直接输出
				System.out.print(node.getStringValue());
			}
		}
		
		//结束标签       迭代特点123321
		System.out.print("</" + elem.getName() + ">");
	}
}
















