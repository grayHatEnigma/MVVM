package com.paramgy.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    private EditText editTextTiltle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTiltle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

      // Changing the top back button to X icon
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        //Setting Activity Title depending on intent tag value
        if(!getIntent().hasExtra("ID")) {
            setTitle("Add Note");
        }
        else if(getIntent().hasExtra("ID")){
            setTitle("Edit Note");
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");
            int priority = getIntent().getIntExtra("priority",1);
            int ID = getIntent().getIntExtra("ID",-1);

            editTextTiltle.setText(title);
            editTextDescription.setText(description);
            numberPickerPriority.setValue(priority);
            this.ID = ID;
        }


    }// End onCreate();

    private  void saveNote(){
        String title = editTextTiltle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty()|| description.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert title and description",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        SaveNoteViewModel saveNoteViewModel = ViewModelProviders.of(this).get(SaveNoteViewModel.class);
       if(getIntent().hasExtra("ID") && ID != -1){
           Note note = new Note(title,description,priority);
           note.setId(ID);
           saveNoteViewModel.update(note);

        }else {
           saveNoteViewModel.saveNote(new Note(title, description, priority));
           Toast.makeText(this, "Note Saved",
                   Toast.LENGTH_SHORT).show();
       }
        finish();
    }// end saveNote();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_icon:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

