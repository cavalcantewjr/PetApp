package br.com.ipet.view.fragment.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import br.com.ipet.IPetApplication;
import br.com.ipet.R;
import br.com.ipet.model.entities.Servico;
import br.com.ipet.model.repository.ServicoRepository;
import br.com.ipet.view.activity.MenuActivity;
import br.com.ipet.view.adapter.ServicoListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicoFragment extends Fragment implements ServicoView {

    @BindView(R.id.servico_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.servico_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.servico_scroll_view)
    NestedScrollView scrollView;

    MenuActivity menuActivity;
    ServicoRepository servicoRepository = new ServicoRepository(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        menuActivity = ((MenuActivity) getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servico, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        servicoRepository.getAll();

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY && !IPetApplication.carrinho.pedido.isEmpty()) {
                    menuActivity.hideTabFragmentFooter();
                }
                if (scrollY < oldScrollY && !IPetApplication.carrinho.pedido.isEmpty()){
                    menuActivity.showTabFragmentFooter();
                }
            }
        });
    }

    @Override
    public void onLoadServicos(List<Servico> servicoList) {
        ServicoListAdapter adapter = new ServicoListAdapter(servicoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
