package br.edu.ifsp.dmo.tarefas.model.dao;

import java.util.List;

import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.tarefas.model.entities.Tag;

public interface ITarefaDao {
    void create(Tarefa Tarefa);

    boolean update(String oldTitle, Tarefa Tarefa);

    boolean delete(Tarefa Tarefa);

    Tarefa findByTitle(String title);

    List<Tarefa> findByTag(Tag tag);

    List<Tarefa> findAll();

    void favorito(Tarefa tarefa);
}
