package com.example.saiteja.hotpizza;

/**
 * Created by sai teja on 09-03-2017.
 */

public class Movie {
    public  String movie_name;
    public  String movie_quality;
    public  String movie_url;
    public  String movie_img;

    public  String getMovie_url() {
        return movie_url;
    }

    public Movie() {
    }

    public  String getMovie_img() {
        return movie_img;
    }

    public Movie(String s1,String s2,String s3,String s4) {
        this.movie_name = s1;
        this.movie_img = s3;
        this.movie_url = s4;
        this.movie_quality = s2;
    }


    @Override
    public String toString() {
        return this.movie_name;
    }

    public  void setMovie_img(String movie_img) {
        this.movie_img = movie_img;
    }

    public  void setMovie_url(String movie_url) {
        this.movie_url = movie_url;

    }

    public  String getMovie_name() {

        return movie_name;
    }

    public  void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public  String getMovie_quality() {
        return movie_quality;
    }

    public  void setMovie_quality(String movie_quality) {
        this.movie_quality = movie_quality;
    }
}
