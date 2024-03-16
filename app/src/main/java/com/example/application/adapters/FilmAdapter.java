package com.example.application.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.application.R;
import com.example.application.models.Film;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FilmAdapter extends BaseAdapter {
    ArrayList<Film> films = new ArrayList<>();
    LayoutInflater lInflater;
    Context ctx;
    public FilmAdapter(Context context, ArrayList<Film> products) {
        ctx = context;
        films = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void add(Film film){
        films.add(film);
    }


    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int index) {
        return films.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;// используем созданные, но не используемые view
        if (view == null) {
            view = lInflater.inflate(R.layout.film_item, parent, false);
        }
        Film p = getFilm(position);

        ((TextView) view.findViewById(R.id.filmTitle)).setText(p.getTitle());// заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.filmGenre)).setText(p.getGenre());
        ((TextView) view.findViewById(R.id.filmYear)).setText(p.getYear() + "");


        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.checkBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);// присваиваем чекбоксу обработчик
        cbBuy.setTag(position);// пишем позицию
        cbBuy.setChecked(p.isSelected());// заполняем данными из товаров: в корзине или нет
        return view;
    }
    Film getFilm(int position){
        return (Film) getItem(position);
    }

    public ArrayList<Film> getFilmsBol() {// содержимое корзины
        ArrayList<Film> box = new ArrayList<Film>();
        for (Film p : films) {
            if (p.isSelected()) {// если в корзине
                box.add(p);
            }
        }
        return box;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {// обработчик для чекбоксов
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getFilm((Integer) buttonView.getTag()).setSelected(isChecked);
        }
    };
}
