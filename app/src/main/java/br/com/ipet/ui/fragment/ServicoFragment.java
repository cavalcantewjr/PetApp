package br.com.ipet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.ipet.R;
import br.com.ipet.database.entities.Servico;
import br.com.ipet.database.repository.ServicoRepository;
import br.com.ipet.ui.adapter.ServicoListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicoFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ServicoRepository produtoRepository = new ServicoRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servico, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Servico> servicoList = new ArrayList<>();
        ServicoListAdapter adapter = new ServicoListAdapter(servicoList);
        recyclerView.setAdapter(adapter);

        produtoRepository.getAll(getResources(), adapter, servicoList);
        return view;
    }
}
