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
	<name>������</name>
	<age>18</age>
	<tel>13488886666</tel>
	<email>Levelee@163.com</email>
	<qq>768678</qq>
</user>
<user id="002">
	<name>���</name>
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
			//��ȡXML�ļ�������document����
			Document doc = new SAXReader().read(file);
			
			Element rootElement = doc.getRootElement();
			
			getChild(rootElement);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private static void getChild(Element elem) {
		String s = "<" + elem.getName();
		//<��ǩ�ڵ� + ���Խڵ�>
		Iterator<Attribute> iterator = elem.attributeIterator();
		while (iterator.hasNext()) {
			Attribute a = iterator.next();
			s += " " + a.getName() + "=\"" + a.getValue() +"\"";
		}
		System.out.print(s + ">");
		
		//�ı��ڵ�   �жϵ�ǰ�ڵ����Ƿ��нڵ㣬û�����������
		/*if (elem.nodeCount() == 1) {
			System.out.print(elem.getStringValue());			
		}*/
	
		//����
		Iterator<Node> it = elem.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (node instanceof Element) {
				Element element = (Element)node;
				getChild(element);
			} else {
				//�����ı��ڵ�ֱ�����
				System.out.print(node.getStringValue());
			}
		}
		
		//������ǩ       �����ص�123321
		System.out.print("</" + elem.getName() + ">");
	}
}
















