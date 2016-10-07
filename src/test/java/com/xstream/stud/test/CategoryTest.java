package com.xstream.stud.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;
import com.xstream.stud.model.Category;

public class CategoryTest {

	@Test(expected = CircularReferenceException.class)
	public void mustSerializeOneCicle() {
		Category esporte = new Category(null, "esporte");
		Category futebol = new Category(esporte, "futebol");
		Category geral = new Category(futebol, "geral");
		esporte.setFather(geral); // fechou o ciclo
		
	//	E tentamos serializar com o XStream com referências baseadas em ID:
	
		XStream xstream = new XStream();
//	    xstream.setMode(XStream.ID_REFERENCES);
		xstream.setMode(XStream.NO_REFERENCES);
		
	    xstream.aliasType("category", Category.class);

	    String xmlEsperado = "<category id=\"1\">\n" +
	            "  <father id=\"2\">\n" +
	            "    <father id=\"3\">\n" +
	            "      <father reference=\"1\"/>\n" +
	            "      <name>futebol</name>\n" +
	            "    </father>\n" +
	            "    <name>geral</name>\n" +
	            "  </father>\n" +
	            "  <name>esporte</name>\n" +
	            "</category>";
	    assertEquals(xmlEsperado, xstream.toXML(esporte));
	}
}
