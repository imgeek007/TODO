package com.geektech.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        deadline = findViewById(R.id.task_deadline);

        deadline.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("ololo", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("ololo", "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("ololo", "afterTextChanged");
            }
        });
deadline.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.d("ololo", "onClickListener");
    }
});

        Button saveBtn = findViewById(R.id.task_save);
        Button editSave = findViewById(R.id.edit_save);
        editSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();

                if (title.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = title.getText().toString().trim();
                }

                if (description.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = description.getText().toString().trim();
                }

                task.oldDeadline = deadline.getText().toString();


                Intent intent = new Intent();
                intent.putExtra("edit", task);
                setResult(RESULT_OK, intent);
                finish();


            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();

                if (title.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = title.getText().toString().trim();
                }

                if (description.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = description.getText().toString().trim();
                }

                task.oldDeadline = deadline.getText().toString();


                Intent intent = new Intent();
                intent.putExtra("task", task);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }


    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
