package br.edu.ifsp.dmo.tarefas.model.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {

    private String descricao;
    private String titulo;
    private boolean favorito;

    //Relationships
    private List<Tag> tags;

    private void init(){
        tags = new ArrayList<>();
    }

    public Tarefa(String descricao, String titulo) {
        this.descricao = descricao;
        this.descricao = descricao;
        init();
    }

    public Tarefa(String descricao, String titulo, boolean favorito) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.favorito = favorito;
        init();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public List<Tag> getTags(){
        return tags;
    }


    @NonNull
    @Override
    public String toString() {
        return "Titulo: " + titulo;
    }
}
