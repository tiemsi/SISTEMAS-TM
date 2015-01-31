package es.arq.arquitectura.xml;

import com.thoughtworks.xstream.XStream;

public class XmlParser<T> {
	
	private XStream	xstream	= new XStream();
	
	private T		objeto;
	
	/**
	 * Crea un objeto desde un XML
	 * 
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public XmlParser(String xml) {
		super();
		objeto = (T) xstream.fromXML(xml);
	}
	
	/**
	 * @return Devuelve un objeto del Tipo correspondiente
	 */
	public T getObject() {
		return objeto;
	}
	
}