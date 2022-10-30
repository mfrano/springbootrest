package com.example.demo.controllers;

import com.example.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = "text/html")
    public String getAllUser(Model model)
    {
        List<UsuarioModel> usuarios = usuarioService.obtenerUsuarios();

        model.addAttribute("usuarios", usuarios);

        return "usuarios";
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UsuarioModel> getAllUserRest()
    {
        return this.usuarioService.obtenerUsuarios();

    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {

        if(this.usuarioService.obtenerPorId(id).isEmpty()){
            throw new BusinessException("ERROR_001", HttpStatus.NOT_FOUND, "EL ID NO EXISTE");
        }
        return this.usuarioService.obtenerPorId(id);
    }

    @RequestMapping(value = "/usuarios/prioridad", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void eliminarPorId(@PathVariable("id") Long id)
    {
         this.usuarioService.eliminarUsuario(id);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
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