package es.arq.arquitectura.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import es.arq.arquitectura.xml.XmlHandler;

/**
 * @author vsempere
 * 
 */
public class ObjectInspector {
	
	/**
	 * Este método explora un objeto de forma recursiva y obtiene los valores de
	 * las propiedades que se le especifiquen en el parámetro propertyPaths Las
	 * rutas de las propiedades de este parámetro deben respetar el formato de
	 * este ejemplo: "garantia/aval/codAval/text()" Por tanto los nodos de las
	 * rutas se separarán con barras "/" y se añadirá "/text()" al final de cada
	 * ruta
	 * 
	 * @param object
	 *            Objeto a explorar
	 * @param propertyPaths
	 *            {@link List} de {@link String} con las rutas de las
	 *            propiedades que se desea obtener.
	 * @return Este método retorna un {@link HashMap}&lt;{@link String},
	 *         {@link String}&gt;. La clave de cada entrada del hashMap será uno
	 *         de los <b>propertyPaths</b> recibidos, y los valores serán el
	 *         resultado de ejecutar tostring sobre cada una de las propiedades.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws XPathExpressionException
	 * @throws Exception
	 */
	public static HashMap<String, String> getValuesForPropertyPaths(Object object, List<String> propertyPaths) throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, XPathExpressionException, ParseException {
		
		HashMap<String, String> pathsAndValues = null ;
		
		Document dom = XmlHandler.objectToDocument(object);
		
		pathsAndValues = XmlHandler.getValuesForPropertyPaths(dom,propertyPaths);
		
//		for (String path : propertyPaths) {
//			
//			String value = getValue(dom, path);
//			pathsAndValues.put(path, value);
//		}
//		
		return pathsAndValues;
	}
	
	// apunte/contacto/nombre/date(dd/mm/yyy)"

	
}
