package oracle.academy.runners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import oracle.academy.models.Goods;
import oracle.academy.models.Shop;
import oracle.academy.parsers.DOM;
import oracle.academy.parsers.JAXB;
import oracle.academy.parsers.SAX;

public class App {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File file = new File("Shope.xml");
		Shop shop = new Shop();
		List<Goods> list = new ArrayList<Goods>();
		list.add(new Goods("���������� GTX 960", 600, "������������ �������", "�������", 1));
		list.add(new Goods("���������", 100.00, "������ ����������", "����� ������", 2));
		list.add(new Goods("������", 1, "������", "��������", 3));
		shop.setGoods(list);
			
		System.out.println("Start JAXB parser from object to XML");
		JAXB.parseToXML(file);
		System.out.println("Start JAXB parser from XML to Object");
		JAXB.parseFromXML(file);
		System.out.println("Start DOM parser");
		DOM.parse(file);
		System.out.println("Start SAX parser");
		SAX.parse(file);	
		
				
		}
	}


