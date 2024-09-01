package com.AppRH.AppRH.entity;

import jakarta.persistence.*;

@Entity
public class Dependentes {
    @Id
    @GeneratedValue
    private long Id;
    @Column(unique = true)
    private String cpf;
    private String nome;
    private String datadenascimento;
    @ManyToOne //Muitos dependentes para um funcionario
    private Funcionario funcionario;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatadenascimento() {
        return datadenascimento;
    }

    public void setDatadenascimento(String datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
