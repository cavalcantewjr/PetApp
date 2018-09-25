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

import br.com.ipet.model.entities.Servico;
import timber.log.Timber;

public class ServicoRepository {

    FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void getAll(Resources resources, final RecyclerView.Adapter adapter, final List<Servico> servicoList) {
        database.collection("serviços")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot snapshot : task.getResult()) {
                                servicoList.add(snapshot.toObject(Servico.class));
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            Timber.e(task.getException(), "Erro ao tentar obter serviços do servidor.");
                        }
                    }
                });
    }
}