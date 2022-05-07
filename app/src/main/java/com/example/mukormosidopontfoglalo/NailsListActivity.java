package com.example.mukormosidopontfoglalo;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class NailsListActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private RecyclerView recyclerView;
    private ArrayList<NalisListItem> itemList;
    private NailsListItemAdapter adapter;
    private static final String LOG_TAG = NailsListActivity.class.getName();
    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nails_list);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            finish();
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        itemList = new ArrayList<>();

        adapter = new NailsListItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        initializeData();
    }

    private void initializeData() {
        TypedArray itemsImageResource = getResources().obtainTypedArray(R.array.nail_images);

        for (int i = 0; i < itemsImageResource.length(); i++) {
            itemList.add(new NalisListItem(itemsImageResource.getResourceId(i, 0)));
        }

        itemsImageResource.recycle();
        adapter.notifyDataSetChanged();
    }
}