package br.edu.ifsp.dmo.tarefas.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.tarefas.model.entities.Tag;

public class TarefaDaoSingleton implements ITarefaDao{
    private static TarefaDaoSingleton instance = null;
    private List<Tarefa> dataset;

    private TarefaDaoSingleton() {
        dataset = new ArrayList<>();
    }

    public static TarefaDaoSingleton getInstance(){
        if(instance == null)
            instance = new TarefaDaoSingleton();
        return instance;
    }

    @Override
    public void create(Tarefa tarefa) {
        if(tarefa != null){
            dataset.add(tarefa);
        }
    }


    @Override
    public boolean delete(Tarefa Tarefa) {
        return dataset.remove(Tarefa);
    }

    @Override
    public Tarefa findByTitle(String title) {
        return dataset.stream()
                .filter(Tarefa -> Tarefa.getTitulo().equals(title))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void favorito(Tarefa tarefa) {
        if(tarefa != null){
            Tarefa tarefa1 = dataset.stream().filter(t -> t.getTitulo()  .equals(tarefa.getTitulo())).findFirst().get();
            dataset.remove(tarefa1);
            dataset.add(0, tarefa1);
        }
    }

    @Override
    public List<Tarefa> findByTag(Tag tag) {
        List<Tarefa> selection = new ArrayList<>();
        for(Tarefa a : dataset){
            for(Tag t : a.getTags()){
                if(t.getTagName().equals(tag.getTagName())){
                    selection.add(a);
                }
            }
        }
        return selection;
    }
    @Override
    public boolean update(String tituloAnterior, Tarefa Tarefa) {
        Tarefa inDataset;
        inDataset = dataset.stream()
                .filter(Tarefa1 -> Tarefa1.getTitulo().equals(tituloAnterior))
                .findAny()
                .orElse(null);
        if(inDataset != null){
            inDataset.setTitle(Tarefa.getTitulo());
            inDataset.setDescricao(Tarefa.getDescricao());
            inDataset.setFavorito(Tarefa.isFavorito());
            inDataset.getTags().clear();
            inDataset.getTags().addAll(Tarefa.getTags());
            return true;
        }
        return false;
    }
    @Override
    public List<Tarefa> findAll() {
        return dataset;
    }
}
