package br.edu.ifsp.dmo.tarefas.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmo.tarefas.R;
import br.edu.ifsp.dmo.tarefas.model.dao.ITarefaDao;
import br.edu.ifsp.dmo.tarefas.model.dao.TarefaDaoSingleton;
import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.tarefas.utils.Constant;
import br.edu.ifsp.dmo.tarefas.view.ArticleDetailsActivity;
import br.edu.ifsp.dmo.tarefas.view.RecyclerViewItemClickListener;
import br.edu.ifsp.dmo.tarefas.view.adapter.ItemPocketRecyclerAdapter;

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private ITarefaDao dao;
    Tarefa tarefa;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TarefaDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), ArticleDetailsActivity.class);
        view.getContext().startActivity(intent);
    }
    public ITarefaDao getDao(){
        return this.dao;
    }
    @Override
    public void openDetails(Tarefa tarefa) {
        Intent intent = new Intent(view.getContext(), ArticleDetailsActivity.class);
        intent.putExtra(Constant.ATTR_TITLE, tarefa.getTitulo());
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);


        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tarefa = dao.findAll().get(position);
                openDetails(tarefa);
            }


        });

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
