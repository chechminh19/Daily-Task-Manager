package com.example.daily_task_manager;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {
    private  ArrayList<Model> modelTask;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Model> modelTask, Context context) {
        this.modelTask = modelTask;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(modelTask.get(position).getName());
        holder.time.setText(modelTask.get(position).getTime());

        holder.btnEdit.setOnClickListener(v -> {
            showEditDialog(modelTask.get(position), position);
        });

        holder.btnDelete.setOnClickListener(v -> {
            showDeleteConfirmDialog(modelTask.get(position), position);
        });
    }
    private void showDeleteConfirmDialog(Model task, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.popupdelete, null);
        builder.setView(dialogView);

        TextView taskName = dialogView.findViewById(R.id.showTextTaskName);
        TextView taskTime = dialogView.findViewById(R.id.showTextTaskTime);
        Button btnYes = dialogView.findViewById(R.id.buttonYes);
        Button btnNo = dialogView.findViewById(R.id.buttonNo);

        taskName.setText(task.getName());
        taskTime.setText(task.getTime());

        AlertDialog dialog = builder.create();
        dialog.show();

        btnYes.setOnClickListener(v -> {
            modelTask.remove(position);
            notifyItemRemoved(position);
            dialog.dismiss();
            Toast.makeText(context, "Task Deleteed successfully!", Toast.LENGTH_SHORT).show();

        });

        btnNo.setOnClickListener(v -> {dialog.dismiss();});
    }

    private void showEditDialog(Model task, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.popup, null);
        builder.setView(dialogView);

        EditText etTaskName = dialogView.findViewById(R.id.editTaskName);
        EditText etTaskTime = dialogView.findViewById(R.id.editTaskTime);
        Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
        Button btnCancel = dialogView.findViewById(R.id.btnUpdateCancel);
        etTaskName.setText(task.getName());
        etTaskTime.setText(task.getTime());

        AlertDialog dialog = builder.create();
        dialog.show();

        btnUpdate.setOnClickListener(v -> {
            String newName = etTaskName.getText().toString();
            String newTime = etTaskTime.getText().toString();

            Model updatedTask = new Model(newName, newTime);
            modelTask.set(position, updatedTask);
            notifyItemChanged(position);
            dialog.dismiss();
            Toast.makeText(context, "Task updated successfully!", Toast.LENGTH_SHORT).show();

        });
        btnCancel.setOnClickListener(v -> {dialog.dismiss();});
    }
    @Override
    public int getItemCount() {
        return modelTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,time;
        Button btnEdit;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.showtaskName);
            time = itemView.findViewById(R.id.showtaskTime);
            btnEdit = itemView.findViewById(R.id.btnEditTask);
            btnDelete = itemView.findViewById(R.id.btnDeleteTask);
        }

    }
}
