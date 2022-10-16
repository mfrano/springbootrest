package com.example.demo.services;

import java.util.ArrayList;
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

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    /*public ArrayList<UsuarioModel> findAllFilter(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enablefilter("deletedUserFilter");
        filter.("isDeleted", isDeleted);
        ArrayList<UsuarioModel> users = usuarioRepository.findAll();
        session.disableFilter("deletedUserFilter");
        return users;
    }*/

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