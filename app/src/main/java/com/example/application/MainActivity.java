package com.example.application;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.application.adapters.FilmAdapter;
import com.example.application.models.Film;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Film> films = new ArrayList<Film>();
    FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        films.add(new Film("fff", "gggg", 2025, false));//

        adapter = new FilmAdapter(this, films);//

        ListView lvMain = (ListView) findViewById(R.id.lv_films);// настраиваем список
        lvMain.setAdapter(adapter);
    }

    public void onClick(View v) {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.addFilm);
        ListView lvMain1 = (ListView) findViewById(R.id.lv_films);
        Button b = findViewById(R.id.btn);

        if (v.getId() == R.id.btn) {
            if (lvMain1.getVisibility() == View.VISIBLE) {
                setInvisibility(b, lvMain1, constraintLayout);
            } else if (lvMain1.getVisibility() == View.GONE) {
                setVisibility(b, lvMain1, constraintLayout);
            }

        }else if (v.getId() == R.id.btnS) {
//            Toast.makeText(this, "constraintLayout", Toast.LENGTH_LONG).show();
            EditText title = findViewById(R.id.name);
            EditText genre = findViewById(R.id.company);
            EditText year = findViewById(R.id.age);

            if (checkTextLength(title, genre, year)) {
                films.add(new Film(title.getText().toString(), genre.getText().toString(),
                        Integer.parseInt(year.getText().toString()), false));

                setVisibility(b, lvMain1, constraintLayout);
            }
        }
    }

    public void onbtnDelCheckedClick(View v) {
        if (v.getId() == R.id.btnDelChecked) {
            if (adapter.getFilmsBol().size() > 0) {
                for (Film p : adapter.getFilmsBol()) {
                    if (p.isSelected())
                        films.remove(p);
                }
                adapter = new FilmAdapter(this, films);
                ListView lvMain = (ListView) findViewById(R.id.lv_films);// настраиваем список
                lvMain.setAdapter(adapter);
            }else {
                Toast.makeText(this, getResources().getString(R.string.select_films), Toast.LENGTH_LONG).show();
            }

        }
    }

    protected void clearAddFilmField() {
        EditText title = findViewById(R.id.name);
        EditText genre = findViewById(R.id.company);
        EditText year = findViewById(R.id.age);
        title.getText().clear();
        genre.getText().clear();
        year.getText().clear();
    }

    protected boolean checkTextLength(EditText editText1, EditText editText2, EditText editText3) {
        if (getTextLength(editText1) > 0 && getTextLength(editText2) > 0 && getTextLength(editText3) > 0) {
            Toast.makeText(this, getResources().getString(R.string.film_added), Toast.LENGTH_LONG).show();
            return true;//"Фильм добавлен"
        }
        Toast.makeText(this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();
        return false;//"Заполните все поля"
    }

    protected int getTextLength(EditText editText) {
        return editText.getText().toString().trim().length();
    }

    protected void setVisibility(Button button, ListView listView, ConstraintLayout constraintLayout) {
        listView.setVisibility(View.VISIBLE);
        constraintLayout.setVisibility(View.GONE);
        clearAddFilmField();
        button.setText(getResources().getString(R.string.add_film));
    }

    protected void setInvisibility(Button button, ListView listView, ConstraintLayout constraintLayout) {
        listView.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.VISIBLE);
        button.setText(getResources().getString(R.string.show_films));
    }
}