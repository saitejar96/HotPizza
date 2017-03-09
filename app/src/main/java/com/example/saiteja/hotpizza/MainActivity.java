package com.example.saiteja.hotpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void getMovDesc(View v) {
        Intent intent = new Intent(this, MoviesActivity.class);
        EditText movie_name = (EditText)v.findViewById(R.id.editText);
        intent.putExtra("name",movie_name.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
