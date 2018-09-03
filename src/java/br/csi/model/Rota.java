/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.model;

import java.util.ArrayList;

/**
 *
 * @author Enrico
 */
public class Rota {
    private int id;
    private String nome;
    private ArrayList<Cliente> pontosdeEntrega;
    private Funcionario entregador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Cliente> getPontosdeEntrega() {
        return pontosdeEntrega;
    }

    public void setPontosdeEntrega(ArrayList<Cliente> pontosdeEntrega) {
        this.pontosdeEntrega = pontosdeEntrega;
    }

    public Funcionario getEntregador() {
        return entregador;
    }

    public void setEntregador(Funcionario entregador) {
        this.entregador = entregador;
    }
   
    
    
    
    
}
