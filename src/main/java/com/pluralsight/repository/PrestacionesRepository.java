package com.pluralsight.repository;

import com.pluralsight.entity.Prestaciones;
import com.pluralsight.entity.Vehiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PrestacionesRepository extends CrudRepository<Prestaciones, Long> {
    public List<Prestaciones> findByVehiculoId(Long idVehiculo);

    public List<Prestaciones> findByFecha(String fecha);
}
