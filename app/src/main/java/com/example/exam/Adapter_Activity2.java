package com.example.exam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exam.Model.Livre;

import java.util.ArrayList;

public class Adapter_Activity2 extends BaseAdapter {

    //Array for items
    ArrayList<Livre> arrayList;

    String s;

    public Adapter_Activity2(ArrayList<Livre> arrayList) {this.arrayList = arrayList;}

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        view = layoutInflater.inflate(R.layout.item,null);

        TextView txt = view.findViewById(R.id.category);
        TextView dispo = view.findViewById(R.id.dispo);

        s = arrayList.get(i).getTitre();

        txt.setText(s);
        dispo.setText(arrayList.get(i).getDisponible());
        return view;
    }
}
