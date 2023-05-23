package br.edu.ifsp.dmo.tarefas.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsp.dmo.tarefas.R;
import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.tarefas.mvp.MainMVP;

public class ItemPocketAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private MainMVP.Presenter presenter;

    public ItemPocketAdapter(Context context, List<Tarefa> data, MainMVP.Presenter presenter) {
        super(context, R.layout.listview_item, data);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.txtTitulo = convertView.findViewById(R.id.titulo_tarefa);
            holder.txtDescricao = convertView.findViewById(R.id.descricao_tarefa);
            holder.btnDeletar = convertView.findViewById(R.id.btnDeletar);
            holder.btnEditar = convertView.findViewById(R.id.btnEditar);
            holder.btnFavoritar = convertView.findViewById(R.id.btnFavoritar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Tarefa tarefa = (Tarefa) getItem(position);
        holder.txtTitulo.setText(tarefa.getTitulo());
        holder.txtDescricao.setText(tarefa.getDescricao());

        return convertView;
    }

    private static class ViewHolder {
        public TextView txtTitulo;
        public TextView txtDescricao;
        public ImageButton btnEditar;
        public ImageButton btnDeletar;
        public ImageButton btnFavoritar;
    }
}
