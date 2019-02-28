package com.paramgy.mvvm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    List<Note> notes = new ArrayList<>();
    OnRecyclerItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_list_style, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.priorityTextView.setText(String.valueOf(currentNote.getPriority()));
        holder.titleTextView.setText(currentNote.getTitle());
        holder.descriptionTextView.setText(currentNote.getDescribtion());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }


    class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView priorityTextView;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priorityTextView = itemView.findViewById(R.id.priorityTextView);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(notes.get(getAdapterPosition()));
            }
        }
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(Note note);
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        Log.i("Recycler", "setListener Called!");
        this.listener = listener;
        Log.i("RecyclerMainListener", listener.toString());
        Log.i("RecyclerAdabterListener", this.listener.toString());
    }

}
