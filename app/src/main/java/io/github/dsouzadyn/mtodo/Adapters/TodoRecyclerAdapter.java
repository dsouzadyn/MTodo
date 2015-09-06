package io.github.dsouzadyn.mtodo.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import io.github.dsouzadyn.mtodo.R;
import io.github.dsouzadyn.mtodo.Todos;

/**
 * Created by DYLAN on 09-08-2015.
 */
public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.CustomViewHolder> {
    private List<Todos> todosList;


    public TodoRecyclerAdapter(List<Todos> todosList) {
        this.todosList = todosList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView todoTitle;
        protected TextView todoDescription;
        protected TextView todoPriority;
        public CustomViewHolder(View view) {
            super(view);
            this.todoTitle = (TextView) view.findViewById(R.id.todoTitle);
            this.todoDescription = (TextView) view.findViewById(R.id.todoDescription);
            this.todoPriority = (TextView) view.findViewById(R.id.todoPriority);
        }
    }

    @Override
    public TodoRecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todo_item, viewGroup, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int p) {
        holder.todoTitle.setText(todosList.get(p).getTitle());
        holder.todoDescription.setText(todosList.get(p).getDescription());
        holder.todoPriority.setText(Integer.toString(todosList.get(p).getPriority()));
    }

    @Override
    public int getItemCount() {
        return todosList.size();
    }
}

