package id.showup.niken.niken_1202154297_modul5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nikenfebriani on 25/03/18.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private LayoutInflater mInflater;
    TodoDbHandler mDB;
    Context mContext;

    TodoViewHolder holder;

    public TodoAdapter(Context context, TodoDbHandler mDB) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mDB = mDB;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.todo_list, parent, false);
        return new TodoViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(TodoAdapter.TodoViewHolder holder, int position) {
        Todo current = mDB.getAllToDos(position);

        holder.todo.setText(current.getName());
        holder.desc.setText(current.getDesc());
        holder.priority.setText(current.getPriority());

        this.holder = holder;
    }

    @Override
    public int getItemCount() {
        return (int) mDB.count();
    }

    public void removeItem(int position) {
        Todo current = mDB.getAllToDos(position);

        int deleted = mDB.delete(current.getmId());
        if (deleted >= 0)
            notifyItemRemoved(holder.getAdapterPosition());
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public final TextView todo, desc, priority;
        final TodoAdapter mAdapter;

        public TodoViewHolder(View itemView, TodoAdapter mAdapter) {
            super(itemView);
            todo = itemView.findViewById(R.id.todoName);
            desc = itemView.findViewById(R.id.todoDesc);
            priority = itemView.findViewById(R.id.todoPriority);

            this.mAdapter = mAdapter;
        }
    }
}