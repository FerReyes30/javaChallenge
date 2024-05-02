package com.pluralsight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_vehiculo")
    private Long id;

    @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "La patente debe tener un formato de 3 letras seguidas de 3 números")
    private String patenteIdentificadora;

    private String numeroChasis;
    private String numeroMotor;
    private String marca;
    private String color;

    @Size(min = 4, max = 4, message = "El año debe contener los 4 numeros")
    private String añoFabricacion;

    private String statusVehiculo;


    public Vehiculo() {

    }

    public Vehiculo(String patenteIdentificadora,
                    String numeroChasis,
                    String numeroMotor,
                    String marca,
                    String color,
                    String añoFabricacion,
                    String statusVehiculo){

        this.patenteIdentificadora = patenteIdentificadora;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.marca = marca;
        this.color = color;
        this.añoFabricacion = añoFabricacion;
        this.statusVehiculo = statusVehiculo;


    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPatenteIdentificadora() {
        return patenteIdentificadora;
    }
    public void setPatenteIdentificadora(String patenteIdentificadora) {
        this.patenteIdentificadora = patenteIdentificadora;
    }
    public String getNumeroChasis() {
        return numeroChasis;
    }
    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }
    public String getNumeroMotor() {
        return numeroMotor;
    }
    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getAñoFabricacion() {
        return añoFabricacion;
    }
    public void setAñoFabricacion(String añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }
    public String getStatusVehiculo() {
        return statusVehiculo;
    }
    public void setStatusVehiculo(String statusVehiculo) {
        this.statusVehiculo = statusVehiculo;
    }


    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", patenteIdentificadora='" + patenteIdentificadora + '\'' +
                ", numeroChasis=" + numeroChasis +
                ", numeroMotor='" + numeroMotor + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", añoFabricacion='" + añoFabricacion + '\'' +
                ", statusVehiculo='" + statusVehiculo + '\'' +
                '}';
    }

}
