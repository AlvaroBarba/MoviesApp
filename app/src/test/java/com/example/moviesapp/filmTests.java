package com.example.moviesapp;

import com.example.moviesapp.models.EntityFilm;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class filmTests {

    private EntityFilm film;

    @Before
    public void setUp() {
        this.film = new EntityFilm();
    }

    @Test
    public void filmId() {
        this.film.setId("1");
        assertEquals("1", this.film.getId());
        this.film.setId("6b");
        assertEquals("6b", this.film.getId());
    }

    @Test
    public void filmTitle() {
        assertEquals(0, this.film.setTitle("The great title"));
        assertEquals(2, this.film.setTitle("123"));
        assertEquals(1, this.film.setTitle(""));
        assertEquals("the great title", this.film.getTitle());
    }

    @Test
    public void filmSynopsis() {
        assertEquals(0, this.film.setSynopsis("A new magician at hogwarts"));
        assertEquals(2, this.film.setSynopsis("@~&"));
        assertEquals("a new magician at hogwarts", this.film.getSynopsis());
    }

    @Test
    public void filmDirector() {
        assertEquals(0, this.film.setDirector("Alvaro"));
        assertEquals(2, this.film.setDirector("12354"));
        assertEquals("alvaro", this.film.getDirector());
    }

    @Test
    public void filmRate() {
        assertEquals(2, this.film.setRate("8"));
        assertEquals(0, this.film.setRate("1"));
        assertEquals("1", this.film.getRate());
    }

    @Test
    public void filmDate() {
        assertEquals(0, this.film.setDate("20/08/2020"));
        assertEquals(2, this.film.setDate("34/12/2020"));
        assertEquals(0, this.film.setDate("16/09/2020"));
        assertEquals("16/09/2020", this.film.getDate());
    }

    @Test
    public void filmWatched() {
        this.film.setWatched(true);
        assertEquals(true, this.film.isWatched());
        this.film.setWatched(false);
        assertEquals(false, this.film.isWatched());
    }

    @Test
    public void filmGenre() {
        this.film.setGenre("Action");
        assertEquals("Action", this.film.getGenre());
        this.film.setGenre("Fantasy");
        assertEquals("Fantasy", this.film.getGenre());
    }

    @Test
    public void filmPhoto() {
        this.film.setPhoto("myPhoto.png");
        assertEquals("myPhoto.png", this.film.getPhoto());
        this.film.setPhoto("Ratata.png");
        assertEquals("Ratata.png", this.film.getPhoto());
    }
}

