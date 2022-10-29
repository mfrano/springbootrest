package com.example.demo.controllers;

import org.springframework.ui.Model;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/usuarios")
    public String getAllUser(Model model)
    {
        List<UsuarioModel> usuarios = usuarioService.obtenerUsuarios();

        model.addAttribute("usuarios", usuarios);

        return "usuarios";
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

  /*  @RequestMapping(method = RequestMethod.GET, value = "/usuarios")
    public String aName() {
        return "usuarios.html";
    }*/

}