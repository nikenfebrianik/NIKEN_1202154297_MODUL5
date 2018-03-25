package id.showup.niken.niken_1202154297_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    EditText inputTodo, inputDesc, inputPriority;
    Button btnAdd;

    TodoDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        inputTodo = findViewById(R.id.etNama);
        inputDesc = findViewById(R.id.etDesc);
        inputPriority = findViewById(R.id.etPriority);
        btnAdd = findViewById(R.id.btnTambah);

        db = new TodoDbHandler(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todo = String.valueOf(inputTodo.getText());
                String desc = String.valueOf(inputDesc.getText());
                String priority = String.valueOf(inputPriority.getText());

                db.addContact(todo, desc, priority);

                Intent intent = new Intent(AddTodo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
