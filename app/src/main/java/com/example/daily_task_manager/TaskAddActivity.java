package com.example.daily_task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TaskAddActivity extends AppCompatActivity {
    private EditText etTaskName;
    private EditText etTaskTime;
    private Button buttonAddTask;
    private Button buttonNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            });

        etTaskName = findViewById(R.id.editTextName);
        etTaskTime = findViewById(R.id.editTextTime);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonNo = findViewById(R.id.buttonNo);
        buttonAddTask.setOnClickListener(v -> {
            String taskName = etTaskName.getText().toString();
            String taskTime = etTaskTime.getText().toString();

            if (!taskName.isEmpty() && !taskTime.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("taskName", taskName);
                resultIntent.putExtra("taskTime", taskTime);
                setResult(RESULT_OK, resultIntent);
                Toast.makeText(TaskAddActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(TaskAddActivity.this, "Please enter task name and time", Toast.LENGTH_SHORT).show();
            }
        });
        buttonNo.setOnClickListener(v -> {
            finish();
        });
    }
}