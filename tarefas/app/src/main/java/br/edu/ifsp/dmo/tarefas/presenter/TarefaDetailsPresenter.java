package br.edu.ifsp.dmo.tarefas.presenter;

import android.os.Bundle;

import br.edu.ifsp.dmo.tarefas.model.dao.ITarefaDao;
import br.edu.ifsp.dmo.tarefas.model.dao.TarefaDaoSingleton;
import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.tarefas.utils.Constant;

public class TarefaDetailsPresenter implements TarefaDetailsMVP.Presenter {

    private TarefaDetailsMVP.View view;
    private Tarefa tarefa;
    private ITarefaDao dao;

    public TarefaDetailsPresenter(TarefaDetailsMVP.View view) {
        this.view = view;
        tarefa = null;
        dao = TarefaDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void verifyUpdate() {
        String title;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            title = bundle.getString(Constant.ATTR_TITLE);
            tarefa = dao.findByTitle(title);
            view.updateUI(tarefa.getTitulo(), tarefa.getDescricao());
        }
    }

    @Override
    public void saveTarefa(String titulo, String descricao) {

        if(tarefa == null){
            //New article
            tarefa = new Tarefa(descricao, titulo);
            dao.create(tarefa);
            view.showToast("Nova tarefa adicionada com sucesso.");
            view.close();
        }else{
            //Update a existent article
            String tituloAnterior = tarefa.getTitulo();
            Tarefa newArticle = new Tarefa(descricao, titulo, tarefa.isFavorito());
            if(dao.update(tituloAnterior, newArticle)){
                view.showToast("Tarefa atualizada com sucesso.");
                view.close();
            }else{
                view.showToast("Erro ao atualizar a Tarefa.");
            }
        }
    }
}
