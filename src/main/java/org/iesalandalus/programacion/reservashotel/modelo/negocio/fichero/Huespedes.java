package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero.utilidades.UtilidadesXML;
import javax.naming.OperationNotSupportedException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
        String dni = elemento.getAttribute(DNI);
        String correo = elemento.getElementsByTagName(CORREO).item(0).getTextContent();
        String telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();
        LocalDate fechaNacimiento = LocalDate.parse(elemento.getElementsByTagName(FECHA_NACIMIENTO).item(0).getTextContent(), FORMATO_FECHA);
        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    private void leerXML() {
        try {
            Document documento = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            NodeList listaHuespedes = documento.getElementsByTagName(HUESPED);
            for (int i = 0; i < listaHuespedes.getLength(); i++) {
                Element elemento = (Element) listaHuespedes.item(i);
                Huesped huesped = elementToHuesped(elemento);
                coleccionHuespedes.add(huesped);
            }
        } catch (Exception e) {
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
        try {
            Document documento = UtilidadesXML.crearDomVacio(RAIZ);
            Element raiz = documento.getDocumentElement();

            for (Huesped huesped : coleccionHuespedes) {
                Element elementoHuesped = huespedToElement(documento, huesped);
                raiz.appendChild(elementoHuesped);
            }

            UtilidadesXML.domToXml(documento, RUTA_FICHERO);
        } catch (ParserConfigurationException | TransformerException e) {
            e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
