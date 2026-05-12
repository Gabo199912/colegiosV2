package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.repositorio.MaestroRepositorio;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaestroServicio {
    private final MaestroRepositorio maestroRepositorio;

    public  MaestroServicio(MaestroRepositorio maestroRepositorio){
        this.maestroRepositorio = maestroRepositorio;
    }

    public Map<String, Object> crearMaestro(@NonNull MaestroModelo maestroModelo){
        if (maestroModelo == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "todos los datos deben de llenarse ");
            return respuesta;
        }

        maestroRepositorio.save(maestroModelo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "MAESTRO AGREGADO CORRECTAMENTE.");
        respuesta.put("MAESTRO: ", maestroModelo);
        return  respuesta;
    }

    public List<MaestroModelo> listarMaestros(){
        List<MaestroModelo> maestros = maestroRepositorio.findAll();
        return maestros;
    }



}
