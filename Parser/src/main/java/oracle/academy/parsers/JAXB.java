package oracle.academy.parsers;

import oracle.academy.models.Shop;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXB {
static Shop shop;
	public static Shop parseFromXML(File file){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			shop = (Shop) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		
		return shop;	
	}
	
	public static void parseToXML(File file){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(shop, file);
			marshaller.marshal(shop, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}	
	}
}
