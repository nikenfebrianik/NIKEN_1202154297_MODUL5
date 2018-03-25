package id.showup.niken.niken_1202154297_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    RecyclerView toDoList;
    TodoAdapter toDoAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    TodoDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoList = findViewById(R.id.listTodo);

        ItemTouchHelper.SimpleCallback itemCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT) {
                    toDoAdapter.removeItem(position);
                    updateUi();
                }
            }
        };
        new ItemTouchHelper(itemCallback).attachToRecyclerView(toDoList);

        db = new TodoDbHandler(this);
        updateUi();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTodo.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateUi() {
        mLayoutManager = new LinearLayoutManager(this);
        toDoList.setLayoutManager(mLayoutManager);

        toDoAdapter = new TodoAdapter(this, db);
        toDoList.setAdapter(toDoAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            Intent intent = new Intent(MainActivity.this, settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}