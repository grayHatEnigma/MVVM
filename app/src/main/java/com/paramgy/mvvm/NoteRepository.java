package com.paramgy.mvvm;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes ;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();

    }


    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
       new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
       new DeleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
    return allNotes;
    }


    //*************** Background Database Operations *******************//

    private static class InsertAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public InsertAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    } // end InsertAsyncTask

    private static class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public UpdateAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    } // end UpdateAsyncTask

    private static class DeleteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public DeleteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    } // end DeleteAsyncTask

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        public DeleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    } // end DeleteAllAsyncTask

}// class end
