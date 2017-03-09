package com.example.saiteja.hotpizza;

/**
 * Created by sai teja on 09-03-2017.
 */

public class Tvshow {
    public  String tv_name;
    public  String tv_eps;
    public  String tv_url;
    public  String tv_img;

    public  String getTv_name() {
        return tv_name;
    }
    public Tvshow(String s1,String s2,String s3,String s4) {
        this.tv_name = s1;
        this.tv_img = s3;
        this.tv_url = s4;
        this.tv_eps = s2;
    }

    public  void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public  String getTv_eps() {
        return tv_eps;
    }

    public  void setTv_eps(String tv_eps) {
        this.tv_eps = tv_eps;
    }

    public  String getTv_url() {
        return tv_url;
    }

    public  void setTv_url(String tv_url) {
        this.tv_url = tv_url;
    }

    public  String getTv_img() {
        return tv_img;
    }

    public  void setTv_img(String tv_img) {
        this.tv_img = tv_img;
    }
}
