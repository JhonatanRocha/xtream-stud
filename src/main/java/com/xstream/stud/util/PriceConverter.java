package com.xstream.stud.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PriceConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
		Double valor =  (Double) object;
		
		NumberFormat format = currencyFormater();
		
		String stringValor = format.format(valor);
		
		writer.setValue(stringValor);
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		String valor = reader.getValue();
		
		try {
			return currencyFormater().parse(valor);
		} catch (ParseException e) {
			throw new ConversionException("Nao consegui converter " + valor, e);
		}
	}
	
	private NumberFormat currencyFormater() {
		Locale locale = new Locale("pt", "br");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format;
	}
}
