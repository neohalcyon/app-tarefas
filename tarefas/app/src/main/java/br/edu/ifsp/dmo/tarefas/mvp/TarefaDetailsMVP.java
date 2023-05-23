package br.edu.ifsp.dmo.tarefas.mvp;

import android.os.Bundle;

public interface TarefaDetailsMVP {

    interface View{
        void updateUI(String title, String url);

        Bundle getBundle();

        void showToast(String message);

        void close();
    }

    interface Presenter{
        void deatach();

        void verifyUpdate();

        void saveTarefa(String titulo, String descricao);
    }
}
