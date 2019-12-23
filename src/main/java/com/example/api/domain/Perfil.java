package com.example.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Perfil {
    @Id
    private int id;

    private String perfil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
