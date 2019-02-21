import android.content.Context;

import com.paramgy.mvvm.Note;
import com.paramgy.mvvm.NoteDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance ;

    public abstract NoteDao noteDao();

    private static NoteDatabase getInstance(Context context){

        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,
                    "note_database").fallbackToDestructiveMigration().build();

        }
        return instance;
    }




}
