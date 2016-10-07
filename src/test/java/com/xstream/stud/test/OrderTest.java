package com.xstream.stud.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.xstream.stud.model.Book;
import com.xstream.stud.model.Music;
import com.xstream.stud.model.Order;
import com.xstream.stud.model.Product;
import com.xstream.stud.util.DifferentOrderConverter;

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
	
	@Test
	public void mustSerializeCollectionImplicit() {
		String expectedResult = "<order>\n"+
	            "  <id>15</id>\n"+
	            "  <product skuCode=\"1587\">\n"+
	            "    <name>geladeira</name>\n"+
	            "    <price>1000.0</price>\n"+
	            "    <description>geladeira duas portas</description>\n"+
	            "  </product>\n"+
	            "  <product skuCode=\"1588\">\n"+
	            "    <name>ferro de passar</name>\n"+
	            "    <price>100.0</price>\n"+
	            "    <description>ferro com vaporizador</description>\n"+
	            "  </product>\n"+
	            "</order>";
	        
	        Order compra = compraComGeladeiraEFerro();
	        
	        XStream xstream = xstreamParaCompraEProduto();
	        
//	        Fala pro xstream que vai querer a colecao implicita
	        xstream.addImplicitCollection(Order.class, "products");
	        
	        String xmlGerado = xstream.toXML(compra);

	        assertEquals(expectedResult, xmlGerado);
	}
	
	@Test
	public void mustSerializeBookAndMusic() {
		String expectedResult = "<order>\n" 
	            + "  <id>15</id>\n"
	            + "  <products>\n" 
	            + "    <book skuCode=\"1589\">\n"
	            + "      <name>O Pássaro Raro</name>\n"
	            + "      <price>100.0</price>\n"
	            + "      <description>dez histórias sobre a existência</description>\n"
	            + "    </book>\n"
	            + "    <music skuCode=\"1590\">\n"
	            + "      <name>Meu Passeio</name>\n"
	            + "      <price>100.0</price>\n"
	            + "      <description>música livre</description>\n"
	            + "    </music>\n"
	            + "  </products>\n" 
	            + "</order>";
		
		Order order = orderWithBookAndMusic();
		
		 XStream xstream = xstreamParaCompraEProduto();
	
		 String xmlGerado = xstream.toXML(order);

	     assertEquals(expectedResult, xmlGerado);
	}
	
	@Test
	public void mustUseConverterFullyCustomized() {
		String expectedResult = "<order style=\"new\">\n" 
	            + "  <id>15</id>\n"
				+ "  <retailer>jhonatan.rocha@ecommerce.com</retailer>\n"
				+ "  <address>\n"
				+ "    <line1>No Where Street 999</line1>\n"
				+ "    <line2>Floor 567 - Orlando - FL</line2>\n"
				+ "  </address>\n"
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

	    xstream.registerConverter(new DifferentOrderConverter());
	    
//	    xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
//	    xstream.setMode(XStream.ID_REFERENCES);
	    xstream.setMode(XStream.NO_REFERENCES);
	    
	    String xmlGerado = xstream.toXML(order);

	    assertEquals(expectedResult, xmlGerado);
	    
	    Order deserializedOrder = (Order) xstream.fromXML(xmlGerado);
	    
	    assertEquals(order, deserializedOrder);
	}

	private XStream xstreamParaCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("order", Order.class);
		xstream.alias("product", Product.class);
	    xstream.alias("book", Book.class);
	    xstream.alias("music", Music.class);
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
	
	private Book book() {
		return new Book("O Pássaro Raro", 100, "dez histórias sobre a existência", 1589);
	}
	
	private Music music() {
		return new Music("Meu Passeio", 100, "música livre", 1590);
	}
	
	private Order orderWithBookAndMusic(){
		
		Book book = book();
		Music music = music();

	    List<Product> produtos = new ArrayList<Product>();
	    produtos.add(book);
	    produtos.add(music);

	    Order compra = new Order(15, produtos);
	    return compra;
	}
}
