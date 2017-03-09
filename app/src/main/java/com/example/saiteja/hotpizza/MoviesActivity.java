package com.example.saiteja.hotpizza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sai teja on 16-12-2016.
 */

public class MoviesActivity extends AppCompatActivity {

    private static String movie_url = "";
    private static String movie_name = "";
    public static ArrayList<Movie> movies_list = new ArrayList<Movie>();
    public static ArrayList<Tvshow> tv_list = new ArrayList<Tvshow>();
    public static GridView gridView;
    public static boolean movie_yes = true;

    public void getMovDesc(View v){
        if(((TextView)v.findViewById(R.id.text5)).getText().equals("movie")){
            movie_yes = true;
            TextView t1 = (TextView)findViewById(R.id.textView3);
            t1.setText("Movie Name: "+((TextView)v.findViewById(R.id.text_small)).getText());
            TextView t2 = (TextView)findViewById(R.id.textView4);
            t2.setText("Quality: "+((TextView)v.findViewById(R.id.qual)).getText());
            ImageView im = (ImageView)findViewById(R.id.image_desc);
            Picasso.with(getApplicationContext()).load(((TextView)v.findViewById(R.id.img)).getText().toString()).into(im);
            movie_url = ((TextView)v.findViewById(R.id.vidurl)).getText().toString();
            Button b  = (Button)findViewById(R.id.button_play);
            b.setText("PLAY MOVIE");
        }
        else{
            movie_yes = false;
            TextView t1 = (TextView)findViewById(R.id.textView3);
            t1.setText("TV Show Name: "+((TextView)v.findViewById(R.id.text_small)).getText());
            TextView t2 = (TextView)findViewById(R.id.textView4);
            t2.setText("Episodes: "+((TextView)v.findViewById(R.id.qual)).getText());
            ImageView im = (ImageView)findViewById(R.id.image_desc);
            Picasso.with(getApplicationContext()).load(((TextView)v.findViewById(R.id.img)).getText().toString()).into(im);
            movie_url = ((TextView)v.findViewById(R.id.vidurl)).getText().toString();
            Button b  = (Button)findViewById(R.id.button_play);
            b.setText("View TV Series");
        }
    }

    public void getVideoPage(View v){
        Intent myIntent = new Intent(this,VideoViewActivity.class);
        myIntent.putExtra("vid_url",movie_url);
        myIntent.putExtra("name",movie_name);
        startActivity(myIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        movies_list = new ArrayList<>();
        tv_list = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Intent intent = getIntent();
        movie_name = intent.getStringExtra("name");
        final Context context = this;
        String movie_nameX = movie_name.replace(" ", "%2B");
        final ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBarA);
        spinner.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println(movie_nameX);
        client.get("https://yesmovies.to/search/"+movie_nameX+".html", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                System.out.println("Started");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = "";
                try {
                    str = new String(response, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Document doc = Jsoup.parse(str);
                Elements movies = doc.select("div[class=ml-item]");
                System.out.println(movies);
                for (int i=0;i<movies.size();i++) {
                    Element movie = movies.get(i);
                    //System.out.println(movie);
                    //Movie mov = new Movie();
                    String s1 ="";
                    String s2 ="";
                    String s3 ="";
                    String s4 ="";

                    s1 = movie.select("a[class=ml-mask]").attr("title");
                    if(s1.contains("- Season")){
                        //Tvshow tv = new Tvshow();
                        s1 = movie.select("a[class=ml-mask]").attr("title");
                        s2 = movie.select("span[class=mli-eps]").select("i").text();
                        s3 = movie.select("img[class=thumb mli-thumb lazy]").attr("data-original");
                        s4 = movie.select("a[class=ml-mask]").attr("href");
                        tv_list.add(new Tvshow(s1,s2,s3,s4));
                    }
                    else {
                        s4 = movie.select("a[class=ml-mask]").attr("href");
                        s2 = movie.select("span[class=mli-quality]").text();
                        s3 = movie.select("img[class=thumb mli-thumb lazy]").attr("data-original");
                        System.out.println(movies_list);
                        movies_list.add(new Movie(s1,s2,s3,s4));
                        System.out.println(movies_list);
                    }
                    //System.out.println(movies_list);
                }
                if(movies_list.size()>0){
                    TextView t1 = (TextView)findViewById(R.id.textView3);
                    t1.append(" "+movies_list.get(0).getMovie_name());
                    TextView t2 = (TextView)findViewById(R.id.textView4);
                    t2.append(" "+movies_list.get(0).getMovie_quality());
                    ImageView im = (ImageView)findViewById(R.id.image_desc);
                    Picasso.with(getApplicationContext()).load(movies_list.get(0).getMovie_img()).into(im);
                    movie_url = movies_list.get(0).getMovie_url();
                }
                else if(tv_list.size()>0){
                    movie_yes = false;
                    TextView t1 = (TextView)findViewById(R.id.textView3);
                    t1.setText("TV Show Name: "+tv_list.get(0).getTv_name());
                    TextView t2 = (TextView)findViewById(R.id.textView4);
                    t2.setText("Episodes: "+tv_list.get(0).getTv_eps());
                    ImageView im = (ImageView)findViewById(R.id.image_desc);
                    Picasso.with(getApplicationContext()).load(tv_list.get(0).getTv_img()).into(im);
                    movie_url = tv_list.get(0).getTv_url();
                    Button b  = (Button)findViewById(R.id.button_play);
                    b.setText("View TV Series");
                }
                //System.out.println(movies_list);
                gridView = (GridView) findViewById(R.id.gridView);
                System.out.println(movies_list);
                GridViewAdapter gridAdapter = new GridViewAdapter(context, R.layout.grid_item_layout,movies_list);
                gridView.setAdapter(gridAdapter);
                GridView gridView2 = (GridView) findViewById(R.id.gridView2);
                GridViewAdapter2 gridAdapter2 = new GridViewAdapter2(context, R.layout.grid_item_layout,tv_list);
                gridView2.setAdapter(gridAdapter2);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                String fail = "";
                try {
                    fail = new String(errorResponse, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                System.out.println(fail);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                //System.out.println("Retrying  "+retryNo);
            }
        });
    }
}
