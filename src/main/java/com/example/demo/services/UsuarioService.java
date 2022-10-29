package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.websocket.Session;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private EntityManager entityManager;

    public List<UsuarioModel> obtenerUsuarios(){
         List<UsuarioModel> usuarios = new ArrayList<>();

                usuarioRepository.findAll()
                .forEach(usuarios::add);

                return usuarios;

    }


    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }


    public ArrayList<UsuarioModel>  obtenerPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public void eliminarUsuario(Long id) {
            usuarioRepository.deleteById(id);
    }

    public UsuarioModel actualizarUsuario(Long id, UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    
}