package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping( path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping( path = "/{id}")
    public void eliminarPorId(@PathVariable("id") Long id)
    {
         this.usuarioService.eliminarUsuario(id);
    }

    @PutMapping(path = "/{id}")
    public Optional<UsuarioModel> actualizarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioModel usuario)
    {

        return this.usuarioService.obtenerPorId(id)
                .map(UsuarioModel -> {
                    UsuarioModel.setNombre(usuario.getNombre());
                    UsuarioModel.setEmail(usuario.getEmail());
                    UsuarioModel.setPrioridad(usuario.getPrioridad());
                    return this.usuarioService.guardarUsuario(usuario);
                });
    }


}