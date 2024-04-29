package com.pluralsight.service;

import com.pluralsight.entity.Prestaciones;
import com.pluralsight.entity.Vehiculo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface VehiculoService {

    public Vehiculo createVehiculo(String patenteIdentificadora,
                                   String numeroChasis,
                                   String numeroMotor,
                                   String marca,
                                   String color,
                                   String añoFabricacion,
                                   String statusVehiculo);

    public Vehiculo actualizaVehiculo(long id, String patenteIdentificadora,
                                               String numeroChasis,
                                               String numeroMotor,
                                               String marca,
                                               String color,
                                               String añoFabricacion,
                                               String statusVehiculo);

    public void deleteVehiculo(long id);

    List<Vehiculo> listVehiculo();

    public List<Prestaciones> obtenVP(String patenteIdentificadora);
}
