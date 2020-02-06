package com.geektech.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> tasks = new ArrayList<>();
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ArrayList<Task> savedTasks = Storage.read(this);
        tasks = savedTasks;

        adapter = new TaskAdapter(tasks);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tasks.get(position);
                editTask();
                Storage.save(tasks, MainActivity.this);
                Toast.makeText(MainActivity.this, "редактирование", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                adapter.deleteLastTask();
                Storage.save(tasks, MainActivity.this);
                Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();

            }
        });

        Button button = findViewById(R.id.open);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 42);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 42) {
            Task task = (Task) data.getSerializableExtra("task");
            tasks.add(task);
            adapter.notifyDataSetChanged();
            Storage.save(tasks, this);
        }
        if (resultCode == RESULT_OK && requestCode == 43) {
            Task task = (Task) data.getSerializableExtra("edit");
            adapter.deleteLastTask();
            tasks.add(task);
            adapter.notifyDataSetChanged();
            Storage.save(tasks, this);
        }
    }

    public void editTask() {
        if (tasks != null) {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 43);
        } else {
            Toast.makeText(MainActivity.this, "Список пуст", Toast.LENGTH_SHORT).show();
        }
    }
}
