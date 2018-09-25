package br.com.ipet.view.fragment.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ipet.R;
import br.com.ipet.infrastructure.network.retrofit.RetrofitClientInstance;
import br.com.ipet.infrastructure.network.retrofit.service.InfnetService;
import br.com.ipet.model.entities.InfnetTarefa;
import br.com.ipet.model.entities.Produto;
import br.com.ipet.model.repository.ProdutoRepository;
import br.com.ipet.view.adapter.ProdutoListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdutoFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ProdutoRepository produtoRepository = new ProdutoRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produto, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Produto> produtoList = new ArrayList<>();
        ProdutoListAdapter adapter = new ProdutoListAdapter(produtoList);
        recyclerView.setAdapter(adapter);

        produtoRepository.getAll(getResources(), adapter, produtoList);

        consumirApiInfnet();

        return view;
    }

    private void consumirApiInfnet() {
        // TODO: Exemplo de consumo de api com o retrofit. Remover quando usar numa api do app
        InfnetService service = RetrofitClientInstance.getInstance().create(InfnetService.class);
        Call<List<InfnetTarefa>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<InfnetTarefa>>() {
            @Override
            public void onResponse(Call<List<InfnetTarefa>> call, Response<List<InfnetTarefa>> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<List<InfnetTarefa>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
