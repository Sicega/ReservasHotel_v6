package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.FuenteDatosMongoDB;

public enum FactoriaFuenteDatos  {

    MONGODB{
        public IFuenteDatos crear(){
            return new FuenteDatosMongoDB();
        }
    },

    MEMORIA {
        public IFuenteDatos crear(){
            return new FuenteDatosMemoria();
        }
    };


}
