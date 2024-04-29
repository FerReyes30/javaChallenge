package com.pluralsight.service;

import com.pluralsight.entity.Prestaciones;
import com.pluralsight.entity.Vehiculo;
import com.pluralsight.repository.PrestacionesRepository;
import com.pluralsight.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PrestacionServiceImpl implements PrestacionService{

    @Autowired
    private PrestacionesRepository prestacionesRepository;
    @Autowired
    public void setPrestacionesRepository(PrestacionesRepository prestacionesRepository) {
        this.prestacionesRepository = prestacionesRepository;
    }


    //Crea el alta de prestacion
    @Override
    public Prestaciones altaPrestacion(String fecha,
                                       String titulo,
                                       String Observacion,
                                       String costo,
                                       String statusPrestacion,
                                       Vehiculo vehiculo){



        Prestaciones prestacion = new Prestaciones();
        prestacion.setFecha(fecha);
        prestacion.setTitulo(titulo);
        prestacion.setObservacion(Observacion);
        prestacion.setCosto(costo);
        prestacion.setStatusPrestacion(statusPrestacion);
        prestacion.setVehiculo(vehiculo);

        return prestacionesRepository.save(prestacion);
    }

    //Realiza la actualizacion de información de la presentación
    @Override
    public Prestaciones actualizaPrestacion(long id,
                                            String fecha,
                                            String titulo,
                                            String observacion,
                                            String costo,
                                            String statusPrestacion,
                                            Vehiculo vehiculo){

            Prestaciones prestacion = prestacionesRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Prestacion no encontrada" + id));

            //Se actualizan los campos
            prestacion.setFecha(fecha);
            prestacion.setTitulo(titulo);
            prestacion.setObservacion(observacion);
            prestacion.setCosto(costo);
            prestacion.setStatusPrestacion(statusPrestacion);
            prestacion.setVehiculo(vehiculo);

            return prestacionesRepository.save(prestacion);
        }

    //Realiza la baja de una prestacion del vehiculo
    @Override
    public void deletePrestacion(long id){
        Prestaciones prestacion = prestacionesRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Prestación no encontrada" + id));

        prestacionesRepository.delete(prestacion);
    }


    //Consulta los datos de todas las prestaciones
    @Override
    public List<Prestaciones> listPrestaciones() {
        return (List<Prestaciones>) prestacionesRepository.findAll();
    }

    //Buscar los vehiculos atendidos con sus prestaciones segun la fecha
    @Override
    public List<Prestaciones> obtenerAtendidosVP(String fecha) {
        List<Prestaciones> fechaVP = prestacionesRepository.findByFecha(fecha);

        return fechaVP;
    }

}
