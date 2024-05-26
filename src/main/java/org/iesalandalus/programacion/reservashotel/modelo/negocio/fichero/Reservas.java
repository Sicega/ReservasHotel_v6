package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

public class Reservas implements IReservas {

    private final List<Reserva> coleccionReservas;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String RUTA_FICHERO = "datos/reservas.xml";
    private static final String RAIZ = "Reservas";
    private static final String RESERVA = "Reserva";
    private static final String DNI_HUESPED = "Dni";
    private static final String PLANTA_HABITACION = "Planta";
    private static final String PUERTA_HABITACION = "Puerta";
    private static final String FECHA_INICIO_RESERVA = "FechaInicioReserva";
    private static final String FECHA_FIN_RESERVA = "FechaFinReserva";
    private static final String REGIMEN = "Regimen";
    private static final String NUMERO_PERSONAS = "Personas";
    private static final String CHECKIN = "FechaCheckIn";
    private static final String CHECKOUT = "FechaCheckOut";
    private static final String PRECIO = "Precio";

    private static Reservas instancia;

    private Reservas() {
        this.coleccionReservas = new ArrayList<>();
    }

    public static Reservas getInstancia() {
        if (instancia == null) {
            instancia = new Reservas();
        }
        return instancia;
    }

    public List<Reserva> get() {
        return copiaProfundaReservas();
    }

    private List<Reserva> copiaProfundaReservas() {
        List<Reserva> misReservas = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            misReservas.add(new Reserva(reserva));
        }
        return misReservas;
    }

    public int getTamano() {
        return coleccionReservas.size();
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        if (buscar(reserva) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        coleccionReservas.add(new Reserva(reserva));
    }

    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        }
        for (Reserva r : coleccionReservas) {
            if (r.equals(reserva)) {
                return new Reserva(r);
            }
        }
        return null;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
        if (!coleccionReservas.remove(reserva)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
    }

    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huésped nulo.");
        }
        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(new Reserva(reserva));
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        List<Reserva> reservasTipo = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva.getHabitacion().getClass().getSimpleName().equals(tipoHabitacion.name())) {
                reservasTipo.add(new Reserva(reserva));
            }
        }
        return reservasTipo;
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        LocalDate fechaActual = LocalDate.now();
        List<Reserva> reservasFuturas = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                reservasFuturas.add(new Reserva(reserva));
            }
        }
        return reservasFuturas;
    }

    public List<Reserva> getReservas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva.getHabitacion().equals(habitacion)) {
                reservasHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasHabitacion;
    }

    @Override
    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new NullPointerException("ERROR: La reserva y la fecha no pueden ser nulas.");
        }
        if (!coleccionReservas.contains(reserva)) {
            throw new IllegalArgumentException("ERROR: No existe ninguna reserva como la indicada.");
        }
        if (fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay())) {
            throw new IllegalArgumentException("ERROR: La fecha del checkIn no puede ser anterior a la reserva.");
        }
        for (Reserva actual : coleccionReservas) {
            if (actual.equals(reserva)) {
                actual.setCheckIn(fecha);
                return;
            }
        }
    }

    @Override
    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new NullPointerException("ERROR: La reserva y la fecha no pueden ser nulas.");
        }
        if (reserva.getCheckIn() == null) {
            throw new NullPointerException("ERROR: No puedes hacer checkOut si el checkIn es nulo.");
        }
        if (!coleccionReservas.contains(reserva)) {
            throw new IllegalArgumentException("ERROR: No existe ninguna reserva como la indicada.");
        }
        if (fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isBefore(reserva.getCheckIn())) {
            throw new IllegalArgumentException("ERROR: La fecha del checkOut no puede ser anterior a la de inicio de reserva o antes del checkIn.");
        }
        for (Reserva actual : coleccionReservas) {
            if (actual.equals(reserva)) {
                actual.setCheckOut(fecha);
                return;
            }
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

    private Reserva elementToReserva(Element elemento) {
        String dni = elemento.getElementsByTagName(DNI_HUESPED).item(0).getTextContent();
        String nombre="",correo="",telefono="";
        LocalDate fechaNacimiento= LocalDate.parse("");
        double precio=0;
        int planta = Integer.parseInt(elemento.getElementsByTagName(PLANTA_HABITACION).item(0).getTextContent());
        int puerta = Integer.parseInt(elemento.getElementsByTagName(PUERTA_HABITACION).item(0).getTextContent());
        LocalDate fechaInicio = LocalDate.parse(elemento.getElementsByTagName(FECHA_INICIO_RESERVA).item(0).getTextContent(), FORMATO_FECHA);
        LocalDate fechaFin = LocalDate.parse(elemento.getElementsByTagName(FECHA_FIN_RESERVA).item(0).getTextContent(), FORMATO_FECHA);
        Regimen regimen = Regimen.valueOf(elemento.getElementsByTagName(REGIMEN).item(0).getTextContent());
        int numeroPersonas = Integer.parseInt(elemento.getElementsByTagName(NUMERO_PERSONAS).item(0).getTextContent());

        LocalDateTime checkIn = null;
        if (elemento.getElementsByTagName(CHECKIN).item(0) != null) {
            checkIn = LocalDateTime.parse(elemento.getElementsByTagName(CHECKIN).item(0).getTextContent(), FORMATO_FECHA_HORA);
        }
        LocalDateTime checkOut = null;
        if (elemento.getElementsByTagName(CHECKOUT).item(0) != null) {
            checkOut = LocalDateTime.parse(elemento.getElementsByTagName(CHECKOUT).item(0).getTextContent(), FORMATO_FECHA_HORA);
        }

        Huesped huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
        Habitacion habitacion;
        String tipo = elemento.getAttribute("tipoHabitacion");
        switch (tipo) {
            case "Simple":
                habitacion = new Simple(planta, puerta, precio);
                break;
            case "Doble":
                habitacion = new Doble(planta, puerta, precio, 0, 0);
                break;
            case "Triple":
                habitacion = new Triple(planta, puerta, precio, 0, 0, 0);
                break;
            case "Suite":
                habitacion = new Suite(planta, puerta, precio, 0, false);
                break;
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido: " + tipo);
        }

        Reserva reserva = new Reserva(huesped, habitacion, regimen, fechaInicio, fechaFin, numeroPersonas);
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);

        return reserva;
    }

    private void leerXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO));
            NodeList listaReservas = documento.getElementsByTagName(RESERVA);
            for (int i = 0; i < listaReservas.getLength(); i++) {
                Element elementoReserva = (Element) listaReservas.item(i);
                Reserva reserva = elementToReserva(elementoReserva);
                if (reserva != null) {
                    coleccionReservas.add(reserva);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.getMessage();
        }
    }

    private Element reservaToElement(Document doc, Reserva reserva) {
        Element elementoReserva = doc.createElement(RESERVA);

        Element dni = doc.createElement(DNI_HUESPED);
        dni.setTextContent(reserva.getHuesped().getDni());
        elementoReserva.appendChild(dni);

        Element planta = doc.createElement(PLANTA_HABITACION);
        planta.setTextContent(String.valueOf(reserva.getHabitacion().getPlanta()));
        elementoReserva.appendChild(planta);

        Element puerta = doc.createElement(PUERTA_HABITACION);
        puerta.setTextContent(String.valueOf(reserva.getHabitacion().getPuerta()));
        elementoReserva.appendChild(puerta);

        Element fechaInicio = doc.createElement(FECHA_INICIO_RESERVA);
        fechaInicio.setTextContent(reserva.getFechaInicioReserva().format(FORMATO_FECHA));
        elementoReserva.appendChild(fechaInicio);

        Element fechaFin = doc.createElement(FECHA_FIN_RESERVA);
        fechaFin.setTextContent(reserva.getFechaFinReserva().format(FORMATO_FECHA));
        elementoReserva.appendChild(fechaFin);

        Element regimen = doc.createElement(REGIMEN);
        regimen.setTextContent(reserva.getRegimen().toString());
        elementoReserva.appendChild(regimen);

        Element numeroPersonas = doc.createElement(NUMERO_PERSONAS);
        numeroPersonas.setTextContent(String.valueOf(reserva.getNumeroPersonas()));
        elementoReserva.appendChild(numeroPersonas);

        if (reserva.getCheckIn() != null) {
            Element checkIn = doc.createElement(CHECKIN);
            checkIn.setTextContent(reserva.getCheckIn().format(FORMATO_FECHA_HORA));
            elementoReserva.appendChild(checkIn);
        }

        if (reserva.getCheckOut() != null) {
            Element checkOut = doc.createElement(CHECKOUT);
            checkOut.setTextContent(reserva.getCheckOut().format(FORMATO_FECHA_HORA));
            elementoReserva.appendChild(checkOut);
        }

        Element precio = doc.createElement(PRECIO);
        precio.setTextContent(String.valueOf(reserva.getPrecio()));
        elementoReserva.appendChild(precio);

        return elementoReserva;
    }

    private void escribirXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element raiz = documento.createElement(RAIZ);
            documento.appendChild(raiz);

            for (Reserva reserva : coleccionReservas) {
                Element elementoReserva = reservaToElement(documento, reserva);
                raiz.appendChild(elementoReserva);
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
