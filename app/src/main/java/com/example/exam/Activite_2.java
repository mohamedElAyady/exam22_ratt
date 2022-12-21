package com.example.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.exam.Model.Livre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activite_2 extends AppCompatActivity {

    DatabaseReference myRef;

    ArrayList<Livre> list = new ArrayList<Livre>();

    ListView livreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite2);

        ListView livreList = findViewById(R.id.livreList);

        Intent intent = getIntent();

        String s = intent.getStringExtra("categorie");

        //get reference
        myRef = FirebaseDatabase.getInstance("https://test-1b23f-default-rtdb.europe-west1.firebasedatabase.app").getReference("Biblio").child(s);


        Adapter_Activity2 adapter = new Adapter_Activity2(list);

        livreList.setAdapter(adapter);




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren() ){
                    Livre a = postSnapshot.getValue(Livre.class);
                    a.setKey(postSnapshot.getKey());
                    list.add(a);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });







    }
}