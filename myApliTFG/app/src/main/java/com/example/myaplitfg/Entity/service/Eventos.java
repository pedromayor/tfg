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

public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer eventoId;

    private String nombreEvento;

    private Date fecha;

    private String ubicacion;

    private String descripcion;

    private Collection<Atuendos> atuendosCollection;

    public Eventos() {
    }

    public Eventos(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Atuendos> getAtuendosCollection() {
        return atuendosCollection;
    }

    public void setAtuendosCollection(Collection<Atuendos> atuendosCollection) {
        this.atuendosCollection = atuendosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventoId != null ? eventoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.eventoId == null && other.eventoId != null) || (this.eventoId != null && !this.eventoId.equals(other.eventoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication76.Eventos[ eventoId=" + eventoId + " ]";
    }
    
}

