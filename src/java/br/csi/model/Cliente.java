/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.model;

/**
 * Classe de modelagem do Cliente, Será a base para criar objetos do tipo cliente para manipular
 * @author Enrico
 */
public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String latitude;
    private String longitude;

    /**
     * 
     * Metodo que retorna o codigo de identificação do cliente
     * @return 
     */
    
    public int getId() {
        return id;
    }
    
    /**
     * Metodo que define o codigo de identificação do cliente
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Metodo que retorna o nome do cliente
     * @return 
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Metodo que define o nome do cliente
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Metodo de retorna o endereço do cliente
     * @return 
     */
    public String getEndereco() {
        return endereco;
    }
    
    /**
     * Metodo que define o endereço do cliente
     * @param endereco 
     */
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    /**
     * Metodo que retorna a latitude do cliente
     * @return 
     */
    public String getLatitude() {
        return latitude;
    }
    
    /**
     * Metodo que define a latitude do cliente
     * @param latitude 
     */
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Metodo que retorna a longitude do cliente
     * @return 
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * Metodo que define a longitude do cliente
     * @param longitude 
     */
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
