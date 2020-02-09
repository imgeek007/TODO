package com.geektech.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<Task> tasks;
    OnItemClickListener onItemClickListener;

    public TaskAdapter(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_task, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onBind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void deleteLastTask() {
        if (tasks != null) {
            try {
                tasks.remove(tasks.get(tasks.size() - 1));
                notifyDataSetChanged();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView deadline;

        public TaskViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.vh_title);
            description = itemView.findViewById(R.id.vh_description);
            deadline = itemView.findViewById(R.id.vh_deadline);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return false;
                }
            });
        }

        public void onBind(Task task) {
            title.setText(task.title);
            description.setText(task.description);
            deadline.setText(task.oldDeadline);
        }
    }
}







