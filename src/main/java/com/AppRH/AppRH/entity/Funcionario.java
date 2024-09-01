package com.AppRH.AppRH.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Funcionario implements Serializable {

    public static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String nome;
    private String data;
    private String email;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Dependentes> despendentes;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Dependentes> getDespendentes() {
        return despendentes;
    }

    public void setDespendentes(List<Dependentes> despendentes) {
        this.despendentes = despendentes;
    }
}
