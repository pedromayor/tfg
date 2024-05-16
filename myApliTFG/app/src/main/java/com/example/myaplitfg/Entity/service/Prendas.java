/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myaplitfg.Entity.service;


import java.io.Serializable;

/**
 *
 * @author Pedro
 */

public class Prendas implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer prendaId;

    private String tipo;
    private String color;
    private String talla;
    private String marca;
    private String imagenUrl;
    private String temporada;
    private Usuarios userId;

    public Prendas() {
    }

    public Prendas(Integer prendaId) {
        this.prendaId = prendaId;
    }

    public Integer getPrendaId() {
        return prendaId;
    }

    public void setPrendaId() {
        this.prendaId = prendaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
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
        hash += (prendaId != null ? prendaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prendas)) {
            return false;
        }
        Prendas other = (Prendas) object;
        if ((this.prendaId == null && other.prendaId != null) || (this.prendaId != null && !this.prendaId.equals(other.prendaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication76.Prendas[ prendaId=" + prendaId + " ]";
    }
    
}
