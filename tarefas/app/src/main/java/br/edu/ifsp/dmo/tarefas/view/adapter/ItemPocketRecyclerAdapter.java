package br.edu.ifsp.dmo.tarefas.view.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmo.tarefas.R;
import br.edu.ifsp.dmo.tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.tarefas.view.RecyclerViewItemClickListener;

public class ItemPocketRecyclerAdapter extends RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder>{

    private Context context;
    private MainMVP.Presenter presenter;
    private List<Tarefa> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemPocketRecyclerAdapter(Context context, List<Tarefa> data, MainMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarefa tarefa = data.get(position);
        holder.txtTitulo.setText(tarefa.getTitulo());
        holder.txtDescricao.setText(tarefa.getDescricao());
        holder.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getDao().delete(tarefa);
                notifyDataSetChanged();
            }
        });

        holder.btnFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tarefa.isFavorito()){
                    tarefa.setFavorito(false);
                    view.setForegroundTintMode(null);
                }
                view.setForegroundTintMode(PorterDuff.Mode.LIGHTEN);
                presenter.getDao().favorito(tarefa);
                notifyDataSetChanged();
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openDetails(tarefa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtTitulo;
        public TextView txtDescricao;

        public ImageButton btnEditar;
        public ImageButton btnDeletar;
        public ImageButton btnFavoritar;

        public ViewHolder(View itemView){
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.titulo_tarefa);
            txtDescricao = itemView.findViewById(R.id.descricao_tarefa);
            btnDeletar = itemView.findViewById(R.id.btnDeletar);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnFavoritar = itemView.findViewById(R.id.btnFavoritar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
