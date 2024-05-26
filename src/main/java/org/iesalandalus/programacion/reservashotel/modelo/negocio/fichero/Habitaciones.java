package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;

import javax.naming.OperationNotSupportedException;
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

public class Habitaciones implements IHabitaciones {

    private List<Habitacion> coleccionHabitaciones;
    private static final String RUTA_FICHERO = "datos/habitaciones.xml";
    private static final String RAIZ = "Habitaciones";
    private static final String HABITACION = "Habitacion";
    private static final String IDENTIFICADOR = "Identificador";
    private static final String PLANTA = "Planta";
    private static final String PUERTA = "Puerta";
    private static final String PRECIO = "Precio";
    private static final String CAMAS_INDIVIDUALES = "CamasIndividuales";
    private static final String CAMAS_DOBLES = "CamasDobles";
    private static final String BANOS = "Banos";
    private static final String JACUZZI = "Jacuzzi";
    private static final String TIPO = "Tipo";
    private static final String SIMPLE = "Simple";
    private static final String DOBLE = "Doble";
    private static final String TRIPLE = "Triple";
    private static final String SUITE = "Suite";

    private static Habitaciones instancia;

    private Habitaciones() {
        coleccionHabitaciones = new ArrayList<>();
    }

    public static Habitaciones getInstancia() {
        if (instancia == null) {
            instancia = new Habitaciones();
        }
        return instancia;
    }

    public List<Habitacion> get() {
        return copiaProfundaHabitaciones();
    }

    private List<Habitacion> copiaProfundaHabitaciones() {
        List<Habitacion> copia = new ArrayList<>();
        for (Habitacion habitacion : coleccionHabitaciones) {
            if (habitacion instanceof Simple) {
                copia.add(new Simple((Simple) habitacion));
            } else if (habitacion instanceof Doble) {
                copia.add(new Doble((Doble) habitacion));
            } else if (habitacion instanceof Triple) {
                copia.add(new Triple((Triple) habitacion));
            } else if (habitacion instanceof Suite) {
                copia.add(new Suite((Suite) habitacion));
            }
        }
        return copia;
    }

    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> habitacionesTipo = new ArrayList<>();
        for (Habitacion habitacion : coleccionHabitaciones) {
            if (tipoHabitacion.equals(TipoHabitacion.SIMPLE) && habitacion instanceof Simple) {
                habitacionesTipo.add(new Simple((Simple) habitacion));
            } else if (tipoHabitacion.equals(TipoHabitacion.DOBLE) && habitacion instanceof Doble) {
                habitacionesTipo.add(new Doble((Doble) habitacion));
            } else if (tipoHabitacion.equals(TipoHabitacion.TRIPLE) && habitacion instanceof Triple) {
                habitacionesTipo.add(new Triple((Triple) habitacion));
            } else if (tipoHabitacion.equals(TipoHabitacion.SUITE) && habitacion instanceof Suite) {
                habitacionesTipo.add(new Suite((Suite) habitacion));
            }
        }
        return habitacionesTipo;
    }

    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        if (habitacion instanceof Simple) {
            coleccionHabitaciones.add(new Simple((Simple) habitacion));
        } else if (habitacion instanceof Doble) {
            coleccionHabitaciones.add(new Doble((Doble) habitacion));
        } else if (habitacion instanceof Triple) {
            coleccionHabitaciones.add(new Triple((Triple) habitacion));
        } else if (habitacion instanceof Suite) {
            coleccionHabitaciones.add(new Suite((Suite) habitacion));
        }
    }

    private int buscarIndice(Habitacion habitacion) {
        return coleccionHabitaciones.indexOf(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        }
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            Habitacion encontrada = coleccionHabitaciones.get(indice);
            if (encontrada instanceof Simple) {
                return new Simple((Simple) encontrada);
            } else if (encontrada instanceof Doble) {
                return new Doble((Doble) encontrada);
            } else if (encontrada instanceof Triple) {
                return new Triple((Triple) encontrada);
            } else if (encontrada instanceof Suite) {
                return new Suite((Suite) encontrada);
            }
        }
        return null;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
        int indice = buscarIndice(habitacion);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }
        coleccionHabitaciones.remove(indice);
    }

    @Override
    public void comenzar() {
        leerXML();
    }

    @Override
    public void terminar() {
        escribirXML();
    }

    private Habitacion elementToHabitacion(Element elemento) {
        String identificador = elemento.getElementsByTagName(IDENTIFICADOR).item(0).getTextContent();
        int planta = Integer.parseInt(elemento.getElementsByTagName(PLANTA).item(0).getTextContent());
        int puerta = Integer.parseInt(elemento.getElementsByTagName(PUERTA).item(0).getTextContent());
        double precio = Double.parseDouble(elemento.getElementsByTagName(PRECIO).item(0).getTextContent());
        int camasIndividuales = Integer.parseInt(elemento.getElementsByTagName(CAMAS_INDIVIDUALES).item(0).getTextContent());
        int camasDobles = Integer.parseInt(elemento.getElementsByTagName(CAMAS_DOBLES).item(0).getTextContent());
        int banos = Integer.parseInt(elemento.getElementsByTagName(BANOS).item(0).getTextContent());
        boolean jacuzzi = Boolean.parseBoolean(elemento.getElementsByTagName(JACUZZI).item(0).getTextContent());
        String tipo = elemento.getElementsByTagName(TIPO).item(0).getTextContent();

        switch (tipo) {
            case SIMPLE:
                return new Simple(planta, puerta, precio);
            case DOBLE:
                return new Doble(planta, puerta, precio, camasIndividuales, camasDobles);
            case TRIPLE:
                return new Triple(planta, puerta, precio, camasIndividuales, camasDobles, banos);
            case SUITE:
                return new Suite(planta, puerta, precio, banos, jacuzzi);
            default:
                throw new IllegalArgumentException("ERROR: Tipo de habitación incorrecto.");
        }
    }

    private void leerXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO));
            documento.getDocumentElement().normalize();

            NodeList listaHabitaciones = documento.getElementsByTagName(HABITACION);
            for (int i = 0; i < listaHabitaciones.getLength(); i++) {
                Element elemento = (Element) listaHabitaciones.item(i);
                Habitacion habitacion = elementToHabitacion(elemento);
                coleccionHabitaciones.add(habitacion);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.getMessage();
        }
    }

    private Element habitacionToElement(Document doc, Habitacion habitacion) {
        Element elementoHabitacion = doc.createElement(HABITACION);

        Element planta = doc.createElement(PLANTA);
        planta.setTextContent(String.valueOf(habitacion.getPlanta()));
        elementoHabitacion.appendChild(planta);

        Element puerta = doc.createElement(PUERTA);
        puerta.setTextContent(String.valueOf(habitacion.getPuerta()));
        elementoHabitacion.appendChild(puerta);

        Element precio = doc.createElement(PRECIO);
        precio.setTextContent(String.valueOf(habitacion.getPrecio()));
        elementoHabitacion.appendChild(precio);

        if (habitacion instanceof Doble) {
            Doble doble = (Doble) habitacion;
            Element camasIndividuales = doc.createElement(CAMAS_INDIVIDUALES);
            camasIndividuales.setTextContent(String.valueOf(doble.getNumCamasIndividuales()));
            elementoHabitacion.appendChild(camasIndividuales);

            Element camasDobles = doc.createElement(CAMAS_DOBLES);
            camasDobles.setTextContent(String.valueOf(doble.getNumCamasDobles()));
            elementoHabitacion.appendChild(camasDobles);
        } else if (habitacion instanceof Triple) {
            Triple triple = (Triple) habitacion;
            Element banos = doc.createElement(BANOS);
            banos.setTextContent(String.valueOf(triple.getNumBanos()));
            elementoHabitacion.appendChild(banos);

            Element camasIndividuales = doc.createElement(CAMAS_INDIVIDUALES);
            camasIndividuales.setTextContent(String.valueOf(triple.getNumCamasIndividuales()));
            elementoHabitacion.appendChild(camasIndividuales);

            Element camasDobles = doc.createElement(CAMAS_DOBLES);
            camasDobles.setTextContent(String.valueOf(triple.getNumCamasDobles()));
            elementoHabitacion.appendChild(camasDobles);
        } else if (habitacion instanceof Suite) {
            Suite suite = (Suite) habitacion;
            Element banos = doc.createElement(BANOS);
            banos.setTextContent(String.valueOf(suite.getNumBanos()));
            elementoHabitacion.appendChild(banos);

            Element jacuzzi = doc.createElement(JACUZZI);
            jacuzzi.setTextContent(String.valueOf(suite.isTieneJacuzzi()));
            elementoHabitacion.appendChild(jacuzzi);
        }

        Element tipo = doc.createElement(TIPO);
        if (habitacion instanceof Simple) {
            tipo.setTextContent(SIMPLE);
        } else if (habitacion instanceof Doble) {
            tipo.setTextContent(DOBLE);
        } else if (habitacion instanceof Triple) {
            tipo.setTextContent(TRIPLE);
        } else if (habitacion instanceof Suite) {
            tipo.setTextContent(SUITE);
        }
        elementoHabitacion.appendChild(tipo);

        return elementoHabitacion;
    }


    private void escribirXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element raiz = documento.createElement(RAIZ);
            documento.appendChild(raiz);

            for (Habitacion habitacion : coleccionHabitaciones) {
                Element elementoHabitacion = habitacionToElement(documento, habitacion);
                raiz.appendChild(elementoHabitacion);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(RUTA_FICHERO));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.getMessage();
        }
    }
}
