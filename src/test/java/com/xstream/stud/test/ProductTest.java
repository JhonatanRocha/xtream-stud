package com.xstream.stud.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.xstream.stud.util.PriceConverter;
import com.xstream.stud.util.PriceSimpleConverter;
import com.xstream.stud.model.Product;

public class ProductTest {
	
	@Test
	public void mustGenerateXMLWithNamePriceAndDescription() {
		
		String expectedResult = "<product skuCode=\"1587\">\n" +
		        "  <name>geladeira</name>\n" +
		        "  <price>R$ 1.000,00</price>\n" +
		        "  <description>geladeira duas portas</description>\n" +
		        "</product>";
		
		Product geladeira = new Product("geladeira", 1000, "geladeira duas portas", 1587);
		
		XStream xstream = new XStream();
		
		xstream.useAttributeFor(Product.class, "skuCode");
		xstream.alias("product", Product.class);
		xstream.registerLocalConverter(Product.class, "price", new PriceSimpleConverter());
//		xstream.registerLocalConverter(Product.class, "price", new PriceConverter());
		
		String xmlGerado = xstream.toXML(geladeira);
		
		assertEquals(expectedResult, xmlGerado);
	}
}
