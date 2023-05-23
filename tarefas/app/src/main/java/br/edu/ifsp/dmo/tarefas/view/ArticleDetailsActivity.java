package br.edu.ifsp.dmo.tarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmo.tarefas.R;
import br.edu.ifsp.dmo.tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.tarefas.presenter.TarefaDetailsPresenter;

public class ArticleDetailsActivity extends AppCompatActivity
        implements TarefaDetailsMVP.View, View.OnClickListener {

    private TarefaDetailsMVP.Presenter presenter;
    private EditText txtDescricao;
    private EditText txtTitulo;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tarefa);

        presenter = new TarefaDetailsPresenter(this);
        findViews();
        setListener();
        setToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.verifyUpdate();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton){
            presenter.saveTarefa(
                    txtTitulo.getText().toString(),
                    txtDescricao.getText().toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateUI(String title, String url) {
        txtTitulo.setText(title);
        txtDescricao.setText(url);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }

    private void findViews(){
        txtDescricao = findViewById(R.id.edittext_url_details);
        txtTitulo = findViewById(R.id.edittext_title_details);
        saveButton = findViewById(R.id.button_save_article);
    }

    private void setListener(){
        saveButton.setOnClickListener(this);
    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}