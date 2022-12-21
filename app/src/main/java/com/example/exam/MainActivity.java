package com.example.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    ArrayList<String> arrayList = new ArrayList<String>();

    //database var
    FirebaseDatabase database ;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the listView
        listView = findViewById(R.id.listView);

        //get reference
        myRef = FirebaseDatabase.getInstance("https://test-1b23f-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Category");

        /*arrayList.add("phisique");
        arrayList.add("math");
        arrayList.add("geography");
        arrayList.add("history");
        arrayList.add("earth");
        arrayList.add("biology");*/

        Adapter adapter = new Adapter(arrayList);

        listView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren() ){
                    String a ;
                    a = (String) postSnapshot.getValue();
                    arrayList.add(a);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(arrayList.get(i));

                Intent intent = new Intent(MainActivity.this,Activite_2.class);

                intent.putExtra("categorie",arrayList.get(i));

                startActivity(intent);

            }
        });
    }
}