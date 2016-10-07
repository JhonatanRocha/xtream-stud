package com.xstream.stud.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class PriceSimpleConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	@Override
	public Object fromString(String valor) {
		NumberFormat format = currencyFormater();
		
		try {
			return format.parse(valor);
		} catch (ParseException e) {
			throw new ConversionException("Nao consegui converter " + valor, e);
		}
	}

	@Override
	public String toString(Object valor) {
		return currencyFormater().format(valor);
	}
	
	private NumberFormat currencyFormater() {
		Locale locale = new Locale("pt", "br");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format;
	}
}
