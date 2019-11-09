package com.example.notesapp.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    public static final String TIMESTAMP_1 = "10-2019";
    public static final String TIMESTAMP_2 = "11-2019";


    @Test
    void isNotesEqual_IdenticalProperties_returnTrue() throws Exception {

        //Arrange the notes to be tested.
        Note note1 = new Note("Note #1", "This is the first note", TIMESTAMP_1);
        Note note2 = new Note("Note #1", "This is the first note", TIMESTAMP_1);

        //Assert
        assertEquals(note1, note2);
        System.out.println("Notes are identical!");
    }

    @Test
    void isNotesEqual_differentProperties_returnFalse() throws Exception {

        //Arrange the notes to be tested.
        Note note1 = new Note("Note #", "This is the first note", TIMESTAMP_1);
        Note note2 = new Note("Note #1", "This is the first note", TIMESTAMP_1);

        //Assert
        assertNotEquals(note1, note2);
        System.out.println("Notes are not identical!");
    }

    @Test
    void isNotesEqual_differentTimestamps_returnTrue() throws Exception {

        //Arrange the notes to be tested.
        Note note1 = new Note("Note #1", "This is the first note", TIMESTAMP_1);
        Note note2 = new Note("Note #1", "This is the first note", TIMESTAMP_2);

        //Assert
        assertEquals(note1, note2);
        System.out.println("Notes are identical!");
    }

    @Test
    void isNotesEqual_differentTitles_returnFalse() throws Exception {

        //Arrange the notes to be tested.
        Note note1 = new Note("Note #1", "This is the first note", TIMESTAMP_1);
        Note note2 = new Note("Note #2", "This is the first note", TIMESTAMP_2);

        //Assert
        assertNotEquals(note1, note2);
        System.out.println("Notes are not identical cuz of different titles!");
    }

    @Test
    void isNotesEqual_differentContent_returnFalse() throws Exception {

        //Arrange the notes to be tested.
        Note note1 = new Note("Note #1", "This is the first note", TIMESTAMP_1);
        Note note2 = new Note("Note #2", "This is the second note", TIMESTAMP_2);

        //Assert
        assertNotEquals(note1, note2);
        System.out.println("Notes are not identical cuz of different content!");
    }
}
