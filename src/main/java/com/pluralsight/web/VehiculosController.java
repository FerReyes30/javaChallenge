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
@RequestMapping("/vehiculos")
public class VehiculosController {

    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PrestacionService prestacionService;

    //---------------------------------- Vehiculo -------------------------------------
    //Endpoint Alta de vehiculo
    @PostMapping()
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehiculo(@PathVariable("id") long id){
        try {

            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.ok("Vehiculo dado de baja");

        } catch (VehiculoNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo realizar la petición.");
        }
    }

    //Endpoint obtiene todos los registros de vehiculos
    @GetMapping()
    public ResponseEntity<List<Vehiculo>> getAllVehiculo() {
        List<Vehiculo> list = vehiculoService.listVehiculo();
        return new ResponseEntity<List<Vehiculo>>(list, HttpStatus.OK);
    }

}
