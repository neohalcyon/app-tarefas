package br.edu.ifsp.dmo.tarefas.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmo.tarefas.model.dao.ITarefaDao;
import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;

public interface MainMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();

        void openDetails();

        void openDetails(Tarefa article);

        void populateList(RecyclerView recyclerView);

        ITarefaDao getDao();
    }
}
