package com.pluralsight.repository;

import com.pluralsight.entity.Vehiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
    public Vehiculo findByPatenteIdentificadora(String patenteIdentificadora);
}
