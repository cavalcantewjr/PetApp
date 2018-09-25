package br.com.ipet.model.repository;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.ipet.model.entities.Produto;
import timber.log.Timber;

public class ProdutoRepository {

    FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void getAll(Resources resources, final RecyclerView.Adapter adapter, final List<Produto> produtoList) {
        database.collection("produtos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot snapshot : task.getResult()) {
                                produtoList.add(snapshot.toObject(Produto.class));
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            Timber.e(task.getException(), "Erro ao tentar obter produtos do servidor.");
                        }
                    }
                });
    }

//    private void addUser(String userId, String name, String email) {
//        // Create a new user with a first, middle, and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("middle", "Mathison");
//        user.put("last", "Turing");
//        user.put("born", 1912);
//
//        // Add a new document with a generated ID
//        database.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Timber.d("DocumentSnapshot added with ID: %s", documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Timber.e(e, "Error adding document");
//                    }
//                });
//    }
}