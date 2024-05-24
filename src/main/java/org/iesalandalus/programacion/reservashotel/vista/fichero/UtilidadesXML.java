package org.iesalandalus.programacion.reservashotel.vista.fichero;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class UtilidadesXML {

    // Metodo para convertir un archivo XML a arbol DOM

    public static Document xmlToDom(String archivoXML) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento;

        try {
            documento = builder.parse(new File(archivoXML));
            documento.getDocumentElement().normalize();
        } catch (SAXException | IOException e) {
            throw new Exception("Error al convertir XML a DOM: " + e.getMessage(), e);
        }

        return documento;
    }

    // Metodo para convertir de arbol DOM a archivo XML

    public static void domToXml(Document documento, String archivoXML) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(new File(archivoXML));

        try {
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new Exception("Error al convertir DOM a XML: " + e.getMessage(), e);
        }
    }

    // Metodo para crear un DOM vacio con un elemento raiz

    public static Document crearDomVacio(String etiquetaRaiz) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.newDocument();
        documento.appendChild(documento.createElement(etiquetaRaiz));
        return documento;
    }
}
