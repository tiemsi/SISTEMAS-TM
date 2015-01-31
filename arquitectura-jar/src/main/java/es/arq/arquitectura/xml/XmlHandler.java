package es.arq.arquitectura.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

public class XmlHandler {
	
	private static XStream	xstream	= new XStream();
	
	/**
	 * Método que convierte un objeto recibido por parámetro a XML (String)
	 * 
	 * @param object
	 * @return Devuelve un String con el XML generado.
	 */
	public static String objectToXml(Object object) {
		String xmlString = xstream.toXML(object);
		return xmlString;
	}
	
	/**
	 * Recibe un XML en formato String y devuelve un Document
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document xmlToDocument(String xml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF8")));
		document.getDocumentElement().normalize();
		
		return document;
	}
	
	/**
	 * Recibe un Object en formato String y devuelve un Document
	 * 
	 * @param object
	 * @return
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document objectToDocument(Object object) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		XStream xstream = new XStream();
		xstream.alias(object.getClass().getSimpleName().toLowerCase(), object.getClass());
		String xmlString = xstream.toXML(object);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF8")));
		document.getDocumentElement().normalize();
		
		return document;
	}
	
	/**
	 * Devuelve el valor de un nodo desde una ruta de propiedad de XML
	 * 
	 * @param dom
	 * @param propertyPaths
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 */
	public static HashMap<String, String> getValuesForPropertyPaths(Document dom, List<String> propertyPaths) throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, XPathExpressionException, ParseException {
		
		HashMap<String, String> pathsAndValues = new HashMap<String, String>();
		
		for (String path : propertyPaths) {
			// XPathExpression expr = xpath.compile(path);
			String value = getValue(dom, path);
			pathsAndValues.put(path, value);
		}
		
		return pathsAndValues;
	}
	
	/**
	 * Recupera un valor del Document desde una expresion. 
	 * 
	 * @param dom
	 * @param expression
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParseException
	 */
	public static String getValue(Document dom, String expression) throws XPathExpressionException, ParseException {
		String parameter = expression.substring(expression.lastIndexOf('(') + 1, expression.lastIndexOf(')'));
		
		//Calculamos la longitud de la sección del parámetro contando los dos paréntesis
		int lengthParameter = parameter.length()+2;
		
		String path = expression.substring(0, expression.length()-lengthParameter);
		path= path.substring(0, path.lastIndexOf('/'));
		
		return XPathFactory.newInstance().newXPath().compile(path).evaluate(dom).trim();
	}
	
}