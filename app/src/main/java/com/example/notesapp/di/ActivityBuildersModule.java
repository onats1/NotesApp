package com.example.notesapp.di;

import com.example.notesapp.ui.notesList.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributesNotesListActivity();
}
