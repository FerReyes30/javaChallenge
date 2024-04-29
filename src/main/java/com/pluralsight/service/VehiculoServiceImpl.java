package com.pluralsight.service;


import com.pluralsight.entity.Prestaciones;
import com.pluralsight.entity.Vehiculo;
import com.pluralsight.exception.VehiculoNotFoundException;
import com.pluralsight.repository.PrestacionesRepository;
import com.pluralsight.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private PrestacionesRepository prestacionesRepository;


    @Autowired
    public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public void setPrestacionesRepository(PrestacionesRepository prestacionesRepository) {
        this.prestacionesRepository = prestacionesRepository;
    }

    //Crea la alta de vehiculo
    @Override
    public Vehiculo createVehiculo(String patenteIdentificadora,
                                   String numeroChasis,
                                   String numeroMotor,
                                   String marca,
                                   String color,
                                   String añoFabricacion,
                                   String statusVehiculo){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatenteIdentificadora(patenteIdentificadora);
        vehiculo.setNumeroChasis(numeroChasis);
        vehiculo.setNumeroMotor(numeroMotor);
        vehiculo.setMarca(marca);
        vehiculo.setColor(color);
        vehiculo.setAñoFabricacion(añoFabricacion);
        vehiculo.setStatusVehiculo(statusVehiculo);

        return vehiculoRepository.save(vehiculo);
    }

    //Realiza la actualizacion de informacion del vehiculo
    @Override
    public Vehiculo actualizaVehiculo(long id,
                                       String patenteIdentificadora,
                                       String numeroChasis,
                                       String numeroMotor,
                                       String marca,
                                       String color,
                                       String añoFabricacion,
                                       String statusVehiculo){

        Vehiculo vehiculo = vehiculoRepository.findById(id)
                                              .orElseThrow(()-> new RuntimeException("Vehiculo no encontrado" + id));

        //Se actualizan los campos
        vehiculo.setPatenteIdentificadora(patenteIdentificadora);
        vehiculo.setNumeroChasis(numeroChasis);
        vehiculo.setNumeroMotor(numeroMotor);
        vehiculo.setMarca(marca);
        vehiculo.setColor(color);
        vehiculo.setAñoFabricacion(añoFabricacion);
        vehiculo.setStatusVehiculo(statusVehiculo);

        return vehiculoRepository.save(vehiculo);
    }

    //Realiza la baja de un vehiculo
    @Override
    public void deleteVehiculo(long id){
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Vehiculo no encontrado" + id));

        vehiculoRepository.delete(vehiculo);
    }

    //Consulta los datos de todos los vehiculos
    @Override
    public List<Vehiculo> listVehiculo() {
        return (List<Vehiculo>) vehiculoRepository.findAll();
    }

    @Override
    public List<Prestaciones> obtenVP(String patenteIdentificadora) {
        Vehiculo vehiculo = vehiculoRepository.findByPatenteIdentificadora(patenteIdentificadora);
        Long idVehiculo = vehiculo.getId();

        return prestacionesRepository.findByVehiculoId(idVehiculo);
    }
}
