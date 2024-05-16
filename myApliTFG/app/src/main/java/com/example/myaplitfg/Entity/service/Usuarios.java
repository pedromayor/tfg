/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myaplitfg.Entity.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Pedro
 */

public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String ubicacion;
    private Collection<Atuendos> atuendosCollection;


    private Collection<Prendas> prendasCollection;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuarios() {
    }

    public Usuarios(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Collection<Atuendos> getAtuendosCollection() {
        return atuendosCollection;
    }

    public void setAtuendosCollection(Collection<Atuendos> atuendosCollection) {
        this.atuendosCollection = atuendosCollection;
    }



    public Collection<Prendas> getPrendasCollection() {
        return prendasCollection;
    }

    public void setPrendasCollection(Collection<Prendas> prendasCollection) {
        this.prendasCollection = prendasCollection;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", ubicacion='" + ubicacion + '\'' +
                ", atuendosCollection=" + atuendosCollection +
                ", prendasCollection=" + prendasCollection +
                ", cliente=" + cliente +
                '}';
    }
}

