package com.xstream.stud.util;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.xstream.stud.model.Order;
import com.xstream.stud.model.Product;

public class DifferentOrderConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Order.class);
	}

	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {

		Order order = (Order) object;
		
		writer.addAttribute("style", "new");
		writer.startNode("id");
		context.convertAnother(order.getId());
		//writer.setValue(String.valueOf(order.getId()));
		writer.endNode();
		
		writer.startNode("retailer");
		writer.setValue("jhonatan.rocha@ecommerce.com");
		writer.endNode();
		
		writer.startNode("address");
		
		writer.startNode("line1");
		writer.setValue("No Where Street 999");
		writer.endNode();
		
		writer.startNode("line2");
		writer.setValue("Floor 567 - Orlando - FL");
		
		writer.endNode();
		writer.endNode();
		
		writer.startNode("products");
		context.convertAnother(order.getProducts());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		
		String style = reader.getAttribute("style");
		reader.moveDown();
		String id = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		String retailer = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		
		reader.moveDown();
		String linha1 = reader.getValue();
		reader.moveUp();
		
		
		reader.moveDown();
		String linha2 = reader.getValue();
		reader.moveUp();
		reader.moveUp();
		
		List<Product> products = new ArrayList<Product>();
		Order order = new Order(Integer.parseInt(id), products);
		
		reader.moveDown();
		List<Product> readedProducts = (List<Product>) context.convertAnother(order, List.class);
		products.addAll(readedProducts);
		reader.moveUp();
		
		return order;
	}

}
