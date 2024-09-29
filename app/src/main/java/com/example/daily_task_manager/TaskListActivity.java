package com.example.daily_task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class TaskListActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private ArrayList<Model> model;
    private RecyclerView recyclerView;
    private final ActivityResultLauncher<Intent> addTaskActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String taskName = data.getStringExtra("taskName");
                    String taskTime = data.getStringExtra("taskTime");

                    if (taskName != null && taskTime != null) {
                        // Ensure the model list is not null
                        if (model == null) {
                            model = new ArrayList<>();
                        }
                        model.add(new Model(taskName, taskTime));
                        adapter.notifyItemInserted(model.size() - 1);
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView viewRe = findViewById(R.id.RecycleViewTask);
        Button btnAddTask = findViewById(R.id.btnAddTask);
        setUpSampleData();
        adapter = new RecyclerViewAdapter(model, this);
        viewRe.setAdapter(adapter);
        viewRe.setLayoutManager(new LinearLayoutManager(this));
        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(TaskListActivity.this, TaskAddActivity.class);
            addTaskActivityLauncher.launch(intent);
        });
    }
    private void setUpSampleData() {
        model = new ArrayList<>();
        model.add(new Model("Task 1", "10:00 AM"));
        model.add(new Model("Task 2", "2:00 PM"));
        model.add(new Model("Task 3", "4:00 PM"));
        model.add(new Model("Task 4", "10:00 AM"));
        model.add(new Model("Task 5", "2:00 PM"));
        model.add(new Model("Task 6", "4:00 PM"));
        model.add(new Model("Task 7", "10:00 AM"));
        model.add(new Model("Task 8", "2:00 PM"));
        model.add(new Model("Task 9", "4:00 PM"));
        // Add more sample tasks if needed
    }


}