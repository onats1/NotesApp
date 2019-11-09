package com.example.notesapp.ui.note;

import com.example.notesapp.repository.NoteRepository;
import com.example.notesapp.ui.notes.NoteViewModel;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

public class NoteViewModelTest {

    private NoteViewModel noteViewModel;

    private NoteRepository noteRepository;

    @BeforeEach
    public void init(){
        noteRepository = mock(NoteRepository.class);
        noteViewModel = new NoteViewModel(noteRepository);
    }
}
