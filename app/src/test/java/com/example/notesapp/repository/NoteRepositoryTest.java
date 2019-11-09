package com.example.notesapp.repository;

import com.example.notesapp.models.Note;
import com.example.notesapp.persistence.NoteDao;
import com.example.notesapp.ui.Resource;
import com.example.notesapp.util.TestUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import io.reactivex.Single;

import static com.example.notesapp.repository.NoteRepository.INSERT_FAILURE;
import static com.example.notesapp.repository.NoteRepository.INSERT_SUCCESS;
import static com.example.notesapp.repository.NoteRepository.NOTE_TITLE_NULL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class NoteRepositoryTest {

    private NoteRepository noteRepository;

    private NoteDao noteDao;

    @BeforeEach
    public void initEach() {
        noteDao = mock(NoteDao.class);
        noteRepository = new NoteRepository(noteDao);
    }

    @Test
    void dummyTest() throws Exception {
        assertNotNull(noteDao);
        assertNotNull(noteRepository);
    }

    /*
     * Insert note
     * verify the correct method is called
     * confirm observer is triggered
     * confirm new rows inserted
     *
     * */
    @Test
    void insertNote_returnRow() throws Exception {

        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        final Resource<Integer> returnedValue = noteRepository.insertNote(TestUtil.TEST_NOTE_1).blockingFirst();

        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value: " + returnedValue.data);
        assertEquals(Resource.success(1, INSERT_SUCCESS),  returnedValue);


       /* noteRepository.insertNote(TestUtil.TEST_NOTE_1)
                .test()
                .await()
                .assertValue(Resource.success(1, INSERT_SUCCESS));*/
    }

    /*
     * Insert note
     * failure (return -1)
     * */
    @Test
    void insertNote_returnFailure() throws Exception {
        final Long failedRow = -1L;
        final Single<Long> returnedData = Single.just(failedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        final Resource<Integer> returnedValue = noteRepository.insertNote(TestUtil.TEST_NOTE_1).blockingFirst();

        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, INSERT_FAILURE),  returnedValue);

    }

    /*
     * insert note
     * null title
     * confirm throw exception.
     * */
    @Test
    void insertNote_nullTitle_throwException() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.insertNote(note);
            }
        });

        assertEquals(NOTE_TITLE_NULL, exception.getMessage());
    }
}
