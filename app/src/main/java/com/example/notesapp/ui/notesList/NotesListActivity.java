package com.example.notesapp.ui.notesList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.notesapp.R;
import com.example.notesapp.repository.NoteRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity {

    @Inject
    NoteRepository noteRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
    }
}
