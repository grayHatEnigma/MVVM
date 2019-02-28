package com.paramgy.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class SaveNoteViewModel extends AndroidViewModel {

    private NoteRepository repository ;


    public SaveNoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public void saveNote(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }
}
