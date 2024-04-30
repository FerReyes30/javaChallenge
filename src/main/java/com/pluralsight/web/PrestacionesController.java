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

import java.util.List;

@RestController
@RequestMapping("/prestaciones")
public class PrestacionesController {

    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PrestacionService prestacionService;

    //---------------------------------- Prestacion -------------------------------------
    //Endpoint alta de prestacion
    @PostMapping()
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrestacion(@PathVariable("id") long id){
        try {

            prestacionService.deletePrestacion(id);
            return ResponseEntity.ok("Prestación dada de baja");

        } catch (VehiculoNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo realizar la petición.");
        }
    }


    //Endpoint obtiene todos los registros de prestaciones
    @GetMapping()
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
