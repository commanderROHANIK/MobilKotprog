package com.example.mukormosidopontfoglalo.Services;

import android.widget.TextView;

import com.example.mukormosidopontfoglalo.Model.Idopont;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class IdopontService {

    private final FirebaseAuth auth;
    private final FirebaseFirestore firestore;
    private final CollectionReference idopontok;

    public IdopontService() {
        this.auth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
        this.idopontok = firestore.collection("Idopontok");
    }

    public void create(Idopont idopont) {
        idopontok.add(idopont);
    }

    public Task<DocumentSnapshot> getAll() {
        return idopontok.document().get();
    }

    public void update(Idopont idopont) {
        idopontok.document().update("id", idopont);
    }

    public void delete(String id) {
        idopontok.document("id").delete();
    }

    public void getLegtavolabbi(TextView textView){
        firestore.collection("Idopontok")
                .whereEqualTo("userID", auth.getCurrentUser().getUid())
                .orderBy("idopont", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.d(LOG_TAG, document.getId() + " => " + document.getData());
                            textView.setText((CharSequence) document.getData().get("idopont"));
                        }
                    } else {
//                        Log.d(LOG_TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public void getFoglalasok(TextView textView){
        firestore.collection("Idopontok")
                .whereEqualTo("userID", auth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.d(LOG_TAG, document.getId() + " => " + document.getData());
                            textView.setText((CharSequence) document.getData().get("idopont"));
                        }
                    } else {
//                        Log.d(LOG_TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
