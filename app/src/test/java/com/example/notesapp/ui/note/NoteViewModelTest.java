package com.example.notesapp.ui.note;

import com.example.notesapp.models.Note;
import com.example.notesapp.repository.NoteRepository;
import com.example.notesapp.ui.Resource;
import com.example.notesapp.ui.notes.NoteViewModel;
import com.example.notesapp.util.InstantExecutorExtension;
import com.example.notesapp.util.LiveDataTestUtil;
import com.example.notesapp.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;
import static com.example.notesapp.repository.NoteRepository.INSERT_SUCCESS;
import static com.example.notesapp.repository.NoteRepository.NOTE_TITLE_NULL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {

    private NoteViewModel noteViewModel;

    private NoteRepository noteRepository;

    @BeforeEach
    public void init() {
        noteRepository = mock(NoteRepository.class);
        noteViewModel = new NoteViewModel(noteRepository);
    }

    @Test
    void observeEmptyNote_whenNotSet() throws Exception {
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();

        Note note = liveDataTestUtil.getValue(noteViewModel.observeNote());

        assertNull(note);
    }

    @Test
    void observeNote_whenSet() throws Exception {

        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();
        Note noteInsert = new Note(TestUtil.TEST_NOTE_1);

        noteViewModel.setNote(noteInsert);
        Note observedNote = liveDataTestUtil.getValue(noteViewModel.observeNote());

        assertEquals(noteInsert, observedNote);
    }

    @Test
    void insertNote_returnRow() throws Exception {

        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();

        final int insertedRow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(insertedRow, INSERT_SUCCESS));

        Mockito.when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);

        //Act
        noteViewModel.setNote(note);
        Resource<Integer> returnedValue = liveDataTestUtil.getValue(noteViewModel.insertNote());

        //Assert
        assertEquals(Resource.success(insertedRow, INSERT_SUCCESS), returnedValue);

    }

    @Test
    void dontInsertRowWithoutObserver() throws Exception {
        Note note = new Note(TestUtil.TEST_NOTE_1);

        noteViewModel.setNote(note);

        verify(noteRepository, never()).insertNote(any(Note.class));
    }

    @Test
    void setNoteNullTitle_throwException() throws Exception {
       Exception exception = assertThrows(Exception.class, new Executable() {
           @Override
           public void execute() throws Throwable {
               Note note = new Note(TestUtil.TEST_NOTE_1);
               note.setTitle(null);

               noteViewModel.setNote(note);
           }
       });

        assertEquals(NOTE_TITLE_NULL, exception.getMessage());
    }

}
