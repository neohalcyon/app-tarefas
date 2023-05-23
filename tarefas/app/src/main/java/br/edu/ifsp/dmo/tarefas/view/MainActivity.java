package br.edu.ifsp.dmo.tarefas.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.ifsp.dmo.tarefas.R;
import br.edu.ifsp.dmo.tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.tarefas.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private MainMVP.Presenter presenter;
    private FloatingActionButton actionButton;
    //private ListView listView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populateList(recyclerView);
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findViews(){
        actionButton = findViewById(R.id.fab_add_article);
        recyclerView = findViewById(R.id.recyclerview_article);
    }

    private void setListener(){
        actionButton.setOnClickListener(v -> presenter.openDetails());

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article a = (Article) listView.getAdapter().getItem(position);
                presenter.openDetails(a);
            }
        });*/
    }

}