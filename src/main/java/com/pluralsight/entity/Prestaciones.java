package com.pluralsight.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
public class Prestaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fecha;
    private String titulo;
    private String observacion;
    private String costo;
    private String statusPrestacion;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    public Prestaciones(){

    }

    public Prestaciones(String fecha,
                        String titulo,
                        String observacion,
                        String costo,
                        String statusPrestacion,
                        Vehiculo vehiculo){

        this.fecha = fecha;
        this.titulo = titulo;
        this.observacion = observacion;
        this.costo = costo;
        this.statusPrestacion = statusPrestacion;
        this.vehiculo = vehiculo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getCosto() {
        return costo;
    }
    public void setCosto(String costo) {
        this.costo = costo;
    }
    public String getStatusPrestacion() {
        return statusPrestacion;
    }
    public void setStatusPrestacion(String statusPrestacion) {
        this.statusPrestacion = statusPrestacion;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    //    @Override
//    public String toString() {
//        return "Prestaciones{" +
//                "id=" + id +
//                ", fecha='" + fecha + '\'' +
//                ", titulo=" + titulo +
//                ", observacion='" + observacion + '\'' +
//                ", costo='" + costo + '\'' +
//                ", statusPrestacion='" + statusPrestacion + '\'' +
//                ", vehiculo='" + vehiculo + '\'' +
//                '}';
//    }
}
