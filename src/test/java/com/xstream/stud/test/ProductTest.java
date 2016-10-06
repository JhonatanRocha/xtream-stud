package com.xstream.stud.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.xstream.stud.model.Product;

public class ProductTest {
	
	@Test
	public void mustGenerateXMLWithNamePriceAndDescription() {
		
		String resultadoEsperado = "<product skuCode=\"1587\">\n" +
		        "  <name>geladeira</name>\n" +
		        "  <price>1000.0</price>\n" +
		        "  <description>geladeira duas portas</description>\n" +
		        "</product>";
		
		Product geladeira = new Product("geladeira", 1000, "geladeira duas portas", 1587);
		
		XStream xstream = new XStream();
		
		xstream.useAttributeFor(Product.class, "skuCode");
		xstream.alias("product", Product.class);
		String xmlGerado = xstream.toXML(geladeira);
		
		assertEquals(resultadoEsperado, xmlGerado);
	}
}
