package com.example.demo.models;

import org.hibernate.annotations.*;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

@ComponentScan
@Entity
@Table(name = "usuario")
@SQLDelete(sql = "UPDATE usuario SET estado=true, fechabaja=now() WHERE id=?")
@Where(clause = "estado = false")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;
    private String email;
    private Integer prioridad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaalta;

    @PrePersist
    private void onCreate() {
        fechaalta = new Date();
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabaja;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;

    @PreUpdate
    private void onUpdate() {
        fechamodificacion = new Date();
    }

    @PreRemove
    private void onDelete() {
        fechabaja = new Date();
    }

    private boolean estado;

    public Date getFechaalta() {
        return fechaalta;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

  /*  public Date setFechamodificacion(Date date) {
        date = new Date();
        this.fechamodificacion = date;
        return fechamodificacion;
    }*/

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setPrioridad(Integer prioridad){
        this.prioridad = prioridad;
    }

    public Integer getPrioridad(){
        return prioridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}