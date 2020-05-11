package com.example.test_khevoyan.response;

public class GenreResponse {

        private Genres[] genres;

        public Genres[] getGenres ()
        {
            return genres;
        }

        public void setGenres (Genres[] genres)
        {
            this.genres = genres;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [genres = "+genres+"]";
        }

}
