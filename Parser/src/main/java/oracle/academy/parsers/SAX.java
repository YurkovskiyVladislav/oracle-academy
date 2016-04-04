package oracle.academy.parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAX {

	public static void parse(File file) throws SAXException, IOException, ParserConfigurationException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
			SAXParser parser = factory.newSAXParser();
			MyHandler handler = new MyHandler();
			parser.parse(file, handler);			
			
			
	}
	
}
