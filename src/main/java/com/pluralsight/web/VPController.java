package com.pluralsight.web;

import com.pluralsight.entity.Prestaciones;
import com.pluralsight.entity.Vehiculo;
import com.pluralsight.exception.VehiculoNotFoundException;
import com.pluralsight.service.PrestacionService;
import com.pluralsight.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vp")
public class VPController {

    private VehiculoService vehiculoService;
    private PrestacionService prestacionService;

    @Autowired
    public void setVehiculoService(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @Autowired
    public void setPrestacionService(PrestacionService prestacionService) {
        this.prestacionService = prestacionService;
    }
    //---------------------------------- Vehiculo -------------------------------------
    //Endpoint Alta de vehiculo
    @PostMapping("/altaVehiculo")
    public Vehiculo altaVehiculo(@RequestBody Vehiculo vehiculo){
        try{

            return vehiculoService.createVehiculo(
                    vehiculo.getPatenteIdentificadora(),
                    vehiculo.getNumeroChasis(),
                    vehiculo.getNumeroMotor(),
                    vehiculo.getMarca(),
                    vehiculo.getColor(),
                    vehiculo.getAñoFabricacion(),
                    "Alta"
                    );

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el vehiculo" + e);
        }

    }

    //Endpoint para actualizacion de informacion de un vehiculo
    @PutMapping("/actualizaInfoAuto/{id}")
    public ResponseEntity<Vehiculo> actualizaInfoVehiculo(@PathVariable("id") long id, @RequestBody Vehiculo vehiculo){
        try {

            Vehiculo actualizaInfoV = vehiculoService.actualizaVehiculo(id, vehiculo.getPatenteIdentificadora(),
                                                                            vehiculo.getNumeroChasis(),
                                                                            vehiculo.getNumeroMotor(),
                                                                            vehiculo.getMarca(),
                                                                            vehiculo.getColor(),
                                                                            vehiculo.getAñoFabricacion(),
                                                                            vehiculo.getStatusVehiculo());
            return ResponseEntity.ok(actualizaInfoV);

        } catch (VehiculoNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo realizar la petición.");
        }

    }

    //Endpoint para dar de baja un vehiculo
    @DeleteMapping("/bajaVehiculo/{id}")
    public ResponseEntity<String> deleteVehiculo(@PathVariable("id") long id){
        try {

            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.ok("Vehiculo dado de baja");

        } catch (VehiculoNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo realizar la petición.");
        }
    }

    //Endpoint obtiene todos los registros de vehiculos
    @GetMapping("/vehiculos")
    public ResponseEntity<List<Vehiculo>> getAllVehiculo() {
        List<Vehiculo> list = vehiculoService.listVehiculo();
        return new ResponseEntity<List<Vehiculo>>(list, HttpStatus.OK);
    }

    //---------------------------------- Prestacion -------------------------------------
    //Endpoint alta de prestacion
    @PostMapping("/altaPrestacion")
    public Prestaciones altaPrestacion(@RequestBody Prestaciones prestacion){
        try{
            return prestacionService.altaPrestacion(
                    prestacion.getFecha(),
                    prestacion.getTitulo(),
                    prestacion.getObservacion(),
                    prestacion.getCosto(),
                    "Alta",
                    prestacion.getVehiculo()
            );
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la prestación" + e);
        }
    }

    //Endpoint modificacion de informacion de prestacion
    @PutMapping("/actualizaInfoPrestacion/{id}")
    public ResponseEntity<Prestaciones> actualizaInfoPrestacion(@PathVariable("id") long id, @RequestBody Prestaciones prestacion){
        try {

            Prestaciones actualizaInfoP = prestacionService.actualizaPrestacion(id,
                                                                                prestacion.getFecha(),
                                                                                prestacion.getTitulo(),
                                                                                prestacion.getObservacion(),
                                                                                prestacion.getCosto(),
                                                                                "Alta",
                                                                                prestacion.getVehiculo());

            return ResponseEntity.ok(actualizaInfoP);

        } catch (VehiculoNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo realizar la petición.");
        }

    }

    //Endpoint para dar de baja una presentancion
    @DeleteMapping("/bajaPrestacion/{id}")
    public ResponseEntity<String> deletePrestacion(@PathVariable("id") long id){
        try {

            prestacionService.deletePrestacion(id);
            return ResponseEntity.ok("Prestación dada de baja");

        } catch (VehiculoNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo realizar la petición.");
        }
    }


    //Endpoint obtiene todos los registros de prestaciones
    @GetMapping("/prestaciones")
    public ResponseEntity<List<Prestaciones>> getAllPrestaciones() {
        List<Prestaciones> list = prestacionService.listPrestaciones();
        return new ResponseEntity<List<Prestaciones>>(list, HttpStatus.OK);
    }

    //------------------- Filtrado de datos para vehiculos y prestaciones ----------------
    //Obtener vehiculo con prestaciones por medio de patente identificatoria
    @GetMapping("/vehiculoInfoPrestacion/{patenteIdentificadora}")
    public ResponseEntity<List<Prestaciones>> obtenerVehiculoConPrestaciones(@PathVariable("patenteIdentificadora") String patenteIdentificadora){
        List<Prestaciones> vehiculoPrestaciones = vehiculoService.obtenVP(patenteIdentificadora);
        if(!vehiculoPrestaciones.isEmpty()){
            return new ResponseEntity<>(vehiculoPrestaciones, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Obtener los vehiculos atendidos con sus prestaciones
    @GetMapping("/fechaAtendidos/{fecha}")
    public ResponseEntity<List<Prestaciones>> obtenerAtendidosVP(@PathVariable("fecha") String fecha){
        List<Prestaciones> fechaAtendidosVP = prestacionService.obtenerAtendidosVP(fecha);
        if(!fechaAtendidosVP.isEmpty()){
            return new ResponseEntity<>(fechaAtendidosVP, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
