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

public class Atuendos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer atuendoId;

    private String nombre;

    private String descripcion;

    private String imagenUrl;

    private Date fechaCreacion;

    private Eventos fechaEvento;

    private Usuarios userId;



       public Atuendos() {
    }

    public Atuendos(Integer atuendoId) {
        this.atuendoId = atuendoId;
    }

    public Integer getAtuendoId() {
        return atuendoId;
    }

    public void setAtuendoId(Integer atuendoId) {
        this.atuendoId = atuendoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Eventos getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Eventos fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Usuarios getUserId() {
        return userId;
    }

    public void setUserId(Usuarios userId) {
        this.userId = userId;
    }





    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atuendoId != null ? atuendoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atuendos)) {
            return false;
        }
        Atuendos other = (Atuendos) object;
        if ((this.atuendoId == null && other.atuendoId != null) || (this.atuendoId != null && !this.atuendoId.equals(other.atuendoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication76.Atuendos[ atuendoId=" + atuendoId + " ]";
    }
    
}

