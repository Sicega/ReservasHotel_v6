package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;

import javax.naming.OperationNotSupportedException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Huespedes implements IHuespedes {

    private static final DateTimeFormatter FORMATO_FECHA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String RUTA_FICHERO="datos/huespedes.xml";
    private static final String RAIZ="Huespedes";
    private static final String HUESPED="Huesped";
    private static final String NOMBRE="Nombre";
    private static final String DNI="Dni";
    private static final String CORREO="Correo";
    private static final String TELEFONO="Telefono";
    private static final String FECHA_NACIMIENTO="FechaNacimiento";

    private List<Huesped> coleccionHuespedes;
    private static Huespedes instancia;

    private Huespedes() {
        coleccionHuespedes = new ArrayList<>();
    }

    public static Huespedes getInstancia() {
        if (instancia == null) {
            instancia = new Huespedes();
        }
        return instancia;
    }

    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    private List<Huesped> copiaProfundaHuespedes() {
        List<Huesped> copia = new ArrayList<>();
        for (Huesped huesped : coleccionHuespedes) {
            copia.add(new Huesped(huesped));
        }
        return copia;
    }

    public int getTamano() {
        return coleccionHuespedes.size();
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }
        coleccionHuespedes.add(new Huesped(huesped));
    }

    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        }
        for (Huesped huespedExistente : coleccionHuespedes) {
            if (huespedExistente.equals(huesped)) {
                return new Huesped(huespedExistente);
            }
        }
        return null;
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }
        if (!coleccionHuespedes.remove(huesped)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        }
    }

    @Override
    public void comenzar() {
        leerXML();
    }

    @Override
    public void terminar() {
        escribirXML();
    }

    private Huesped elementToHuesped(Element elemento) {
        String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
        String dni = elemento.getElementsByTagName(DNI).item(0).getTextContent();
        String correo = elemento.getElementsByTagName(CORREO).item(0).getTextContent();
        String telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();
        LocalDate fechaNacimiento = LocalDate.parse(elemento.getElementsByTagName(FECHA_NACIMIENTO).item(0).getTextContent(), FORMATO_FECHA);
        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    private void leerXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO));
            documento.getDocumentElement().normalize();

            NodeList listaHuespedes = documento.getElementsByTagName(HUESPED);
            for (int i = 0; i < listaHuespedes.getLength(); i++) {
                Element elemento = (Element) listaHuespedes.item(i);
                Huesped huesped = elementToHuesped(elemento);
                coleccionHuespedes.add(huesped);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.getMessage();
        }
    }

    private Element huespedToElement(Document doc, Huesped huesped) {
        Element elementoHuesped = doc.createElement(HUESPED);

        Element nombre = doc.createElement(NOMBRE);
        nombre.setTextContent(huesped.getNombre());
        elementoHuesped.appendChild(nombre);

        Element dni = doc.createElement(DNI);
        dni.setTextContent(huesped.getDni());
        elementoHuesped.appendChild(dni);

        Element correo = doc.createElement(CORREO);
        correo.setTextContent(huesped.getCorreo());
        elementoHuesped.appendChild(correo);

        Element telefono = doc.createElement(TELEFONO);
        telefono.setTextContent(huesped.getTelefono());
        elementoHuesped.appendChild(telefono);

        Element fechaNacimiento = doc.createElement(FECHA_NACIMIENTO);
        fechaNacimiento.setTextContent(huesped.getFechaNacimiento().format(FORMATO_FECHA));
        elementoHuesped.appendChild(fechaNacimiento);

        return elementoHuesped;
    }

    private void escribirXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();
            Element raiz = documento.createElement(RAIZ);
            documento.appendChild(raiz);

            for (Huesped huesped : coleccionHuespedes) {
                Element elementoHuesped = huespedToElement(documento, huesped);
                raiz.appendChild(elementoHuesped);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(RUTA_FICHERO));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.getMessage();
        }
    }
}
