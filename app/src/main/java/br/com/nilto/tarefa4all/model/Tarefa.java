package br.com.nilto.tarefa4all.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilto on 17/10/2016.
 */

public class Tarefa {
    private String idList;
    private String cidade;
    private String bairro;
    private String urlFoto;
    private String urlLogo;
    private String titulo;
    private String telefone;
    private String texto;
    private String endereco;
    private double latitude;
    private double longitude;
    private ArrayList<Comentario> comentario;


    public Tarefa() {

    }


    public Tarefa(String idList, String cidade, String bairro, String urlFoto, String urlLogo, String titulo, String telefone,
                  String texto, String endereco, double latitude, double longitude) {
        this.idList = idList;
        this.cidade = cidade;
        this.bairro = bairro;
        this.urlFoto = urlFoto;
        this.urlLogo = urlLogo;
        this.titulo = titulo;
        this.telefone = telefone;
        this.texto = texto;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Tarefa(String idList, String cidade, String bairro, String urlFoto, String urlLogo, String titulo, String telefone, String texto,
                  String endereco, double latitude, double longitude, ArrayList<Comentario> comentario) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.urlFoto = urlFoto;
        this.urlLogo = urlLogo;
        this.titulo = titulo;
        this.telefone = telefone;
        this.texto = texto;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comentario = comentario;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(ArrayList<Comentario> comentario) {
        this.comentario = comentario;
    }
}
