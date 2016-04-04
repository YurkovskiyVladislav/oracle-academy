package oracle.academy.parsers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import oracle.academy.models.Goods;
import oracle.academy.models.Shop;

public class MyHandler extends DefaultHandler{

	String thisElement;
	Shop shop;
	Goods goods;
	List<Goods> listGoods = new ArrayList<Goods>();
	
	
	public void startDocument() throws SAXException{
		System.out.println();
		System.out.println("Start SAX parser");		
	}
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException{
		thisElement = qName;	
		if (qName.equalsIgnoreCase("shop")) {
			shop = new Shop();
		}
		if (qName.equalsIgnoreCase("goods")) {			
			goods = new Goods();
			goods.setId(Integer.parseInt(atts.getValue("id")));
		}		
	}	
	
	public void characters(char[] ch, int start, int length) throws SAXException{
		if (thisElement.equals("name")) {
			goods.setName(new String(ch, start, length));
		}
		if (thisElement.equals("price")) {
			String price =  new String(ch, start, length);
			goods.setPrice(Integer.parseInt(price));
		}
		if (thisElement.equals("category")) {
			goods.setCategory(new String(ch, start, length));
		}
		if (thisElement.equals("description")) {
			goods.setDescription(new String(ch, start, length));
		}
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException{		
		thisElement = "";                          
		if (qName.equalsIgnoreCase("goods")) {
			listGoods.add(goods);
		}
	}
	
	public void endDocument() throws SAXException{
		shop.setGoods(listGoods);
		System.out.print("Result of parsing: ");
		System.out.println(shop.toString());		
	}
	
}
