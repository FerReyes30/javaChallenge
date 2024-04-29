package com.pluralsight.service;

import com.pluralsight.entity.Prestaciones;
import com.pluralsight.entity.Vehiculo;

import java.util.Date;
import java.util.List;

public interface PrestacionService {

    public Prestaciones altaPrestacion(String fecha,
                                       String titulo,
                                       String Observacion,
                                       String costo,
                                       String statusPrestacion,
                                       Vehiculo id_vehiculo);

    public Prestaciones actualizaPrestacion(long id,
                                            String fecha,
                                            String titulo,
                                            String observacion,
                                            String costo,
                                            String statusPrestacion,
                                            Vehiculo vehiculo);

    public void deletePrestacion(long id);

    public List<Prestaciones> listPrestaciones();

    public List<Prestaciones> obtenerAtendidosVP(String fecha);

}
