package es.arq.arquitectura.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@SuppressWarnings("restriction")
public class XmlUtils {

	public String objectToXml(Object object) throws Exception {
        StringWriter sw = new StringWriter();
        
        JAXBContext carContext = JAXBContext.newInstance(object.getClass());
        Marshaller carMarshaller = carContext.createMarshaller();
        carMarshaller.marshal(object, sw);
        return sw.toString();
	}
	
	public Document xmlToDocument(String xml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF8")));
		document.getDocumentElement().normalize();
		
		return document;
	}
	
    public Document objectToDocument(Object source) throws Exception {
        String result;
        StringWriter sw = new StringWriter();
        
        JAXBContext carContext = JAXBContext.newInstance(source.getClass());
        Marshaller carMarshaller = carContext.createMarshaller();
        carMarshaller.marshal(source, sw);
        result = sw.toString();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(result.getBytes("UTF8")));
		document.getDocumentElement().normalize();
		
		return document;
    }

    public String extractValue(String xml, String xpathExpression) throws Exception {
        String actual;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

        byte[] bytes = xml.getBytes("UTF-8");
        InputStream inputStream = new ByteArrayInputStream(bytes);
        Document doc = docBuilder.parse(inputStream);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        actual = xpath.evaluate(xpathExpression, doc, XPathConstants.STRING).toString();

        return actual;
    }
}