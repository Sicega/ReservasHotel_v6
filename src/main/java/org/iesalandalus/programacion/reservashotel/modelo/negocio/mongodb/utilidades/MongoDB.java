package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.iesalandalus.programacion.reservashotel.modelo.*;

import java.time.format.DateTimeFormatter;

public class MongoDB {

    public static final DateTimeFormatter FORMATO_DIA=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter FORMATO_DIA_HORA=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

   // private static final String SERVIDOR=mongodb+srv://reservashotel:<password>@cluster0.pdenqqn.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0;

    private static final int PUERTO = 27017;
    private static final String BD = "reservashotel";
    private static final String USUARIO= "reservashotel";
    private static final String CONTRASENA="reservashotel-2024";
    public static final String HUESPED= "huesped";
    public static final String NOMBRE= "nombre";
    public static final String DNI= "dni";
    public static final String TELEFONO="telefono";
    public static final String CORREO="correo";
    public static final String FECHA_NACIMIENTO="fecha_nacimiento";
    public static final String HUESPED_DNI=HUESPED+"."+DNI;
    public static final String HABITACION="habitacion";
    public static final String IDENTIFICADOR="identificador";
    public static final String PLANTA="planta";
    public static final String PUERTA="puerta";
    public static final String PRECIO="precio";
    public static final String HABITACION_IDENTIFICADOR=HABITACION+"."+IDENTIFICADOR;
    public static final String TIPO="tipo";
    public static final String HABITACION_TIPO=HABITACION+"."+TIPO;
    public static final String TIPO_SIMPLE="SIMPLE";
    public static final String TIPO_DOBLE="DOBLE";
    public static final String TIPO_TRIPLE="TRIPLE";
    public static final String TIPO_SUITE="SUITE";
    public static final String CAMAS_INDIVIDUALES="camas_individuales";
    public static final String CAMAS_DOBLES="camas_dobles";
    public static final String BANOS="banos";
    public static final String JACUZZI="jacuzzi";
    public static final String REGIMEN="regimen";
    public static final String FECHA_INICIO_RESERVA="fecha_inicio_reserva";
    public static final String FECHA_FIN_RESERVA="fecha_fin_reserva";
    public static final String CHECKIN="checkin";
    public static final String CHECKOUT="checkout";
    public static final String PRECIO_RESERVA="precio_reserva";
    public static final String NUMERO_PERSONAS="numero_personas";
    private MongoClient conexion;

    // METODOS

    private MongoDB(){}
    //public static getDB(){}
    private static void establecerConexion(){}
    public static void cerrarConexion(){}
    //public static getDocumento(Huesped huesped){}
    //public static getHuesped(Document documentoHuesped){}
    // public static getDocumento(Habitacion habitacion){}
    // public static getHabitacion(Document documentoHabitacion){}
    // public static getReserva(Document documentoReserva){}
    // pubic static getDocumento(Reserva reserva){}


}
