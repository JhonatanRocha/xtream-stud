package com.xstream.stud.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.xstream.stud.model.Order;
import com.xstream.stud.model.Product;

public class OrderTest {

	@Test
    public void mustSerializeEachProductsFromOrder() {

        String expectedResult = "<order>\n"+
            "  <id>15</id>\n"+
            "  <products>\n"+
            "    <product skuCode=\"1587\">\n"+
            "      <name>geladeira</name>\n"+
            "      <price>1000.0</price>\n"+
            "      <description>geladeira duas portas</description>\n"+
            "    </product>\n"+
            "    <product skuCode=\"1588\">\n"+
            "      <name>ferro de passar</name>\n"+
            "      <price>100.0</price>\n"+
            "      <description>ferro com vaporizador</description>\n"+
            "    </product>\n"+
            "  </products>\n"+
            "</order>";
        
        Order compra = compraComGeladeiraEFerro();
        
        XStream xstream = xstreamParaCompraEProduto();
        
        String xmlGerado = xstream.toXML(compra);

        assertEquals(expectedResult, xmlGerado);
    }
	
	@Test
	public void deveGerarUmaCompraComOsProdutosAdequados() {
		String xmlOrigem = "<order>\n"+
	            "  <id>15</id>\n"+
	            "  <products>\n"+
	            "    <product skuCode=\"1587\">\n"+
	            "      <name>geladeira</name>\n"+
	            "      <price>1000.0</price>\n"+
	            "      <description>geladeira duas portas</description>\n"+
	            "    </product>\n"+
	            "    <product skuCode=\"1588\">\n"+
	            "      <name>ferro de passar</name>\n"+
	            "      <price>100.0</price>\n"+
	            "      <description>ferro com vaporizador</description>\n"+
	            "    </product>\n"+
	            "  </products>\n"+
	            "</order>";
	        
			XStream xstream = xstreamParaCompraEProduto();
	        
	        Order compraResultado = (Order) xstream.fromXML(xmlOrigem);
	        
	        
	        Order compraEsperada = compraComGeladeiraEFerro();
	        assertEquals(compraEsperada, compraResultado);
	}
	
	@Test
	public void mustSerializeTwoGeladeirasEquals() {
		String expectedResult = "<order>\n" 
	            + "  <id>15</id>\n"
	            + "  <products>\n" 
	            + "    <product skuCode=\"1587\">\n"
	            + "      <name>geladeira</name>\n"
	            + "      <price>1000.0</price>\n"
	            + "      <description>geladeira duas portas</description>\n"
	            + "    </product>\n"
	            + "    <product skuCode=\"1587\">\n"
	            + "      <name>geladeira</name>\n"
	            + "      <price>1000.0</price>\n"
	            + "      <description>geladeira duas portas</description>\n"
	            + "    </product>\n"
	            + "  </products>\n" 
	            + "</order>";

	    Order order = orderMustHaveTwoGeladeirasEquals();

	    XStream xstream = xstreamParaCompraEProduto();

//	    xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
//	    xstream.setMode(XStream.ID_REFERENCES);
	    xstream.setMode(XStream.NO_REFERENCES);
	    
	    String xmlGerado = xstream.toXML(order);

	    assertEquals(expectedResult, xmlGerado);
	}

	private XStream xstreamParaCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("order", Order.class);
		xstream.alias("product", Product.class);
		xstream.useAttributeFor(Product.class, "skuCode");
		return xstream;
	}

	private Order compraComGeladeiraEFerro() {
		Product geladeira = geladeira();
		Product ferro = ferro();
		List<Product> products = new ArrayList<Product>();
		products.add(geladeira);
		products.add(ferro);
		
		Order compraEsperada = new Order(15, products);
		return compraEsperada;
	}

	private Product ferro() {
		return new Product("ferro de passar", 100, "ferro com vaporizador", 1588);
	}

	private Product geladeira() {
		return new Product("geladeira", 1000, "geladeira duas portas", 1587);
	}
	
	private Order orderMustHaveTwoGeladeirasEquals() {
	    Product geladeira = geladeira();

	    List<Product> produtos = new ArrayList<Product>();
	    produtos.add(geladeira);
	    produtos.add(geladeira);

	    Order compra = new Order(15, produtos);
	    return compra;
	}
}
