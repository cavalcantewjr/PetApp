package br.com.ipet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import br.com.ipet.IPetApplication;
import br.com.ipet.R;
import br.com.ipet.infrastructure.animation.AnimationUtil;
import br.com.ipet.model.entities.Produto;
import br.com.ipet.model.entities.Servico;
import br.com.ipet.view.adapter.CarrinhoProdutoListAdapter;
import br.com.ipet.view.adapter.CarrinhoServicoListAdapter;
import br.com.ipet.view.fragment.tab.ProdutoFragment;
import br.com.ipet.view.fragment.tab.ServicoFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TabFragment extends Fragment {

    @BindView(R.id.tabs)
    public TabLayout tabLayout;
    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    @BindView(R.id.footer_carrinho)

    public RelativeLayout footerCarrinho;
    public BottomSheetDialog bottomSheetDialog;
    public MaterialButton buttonFinalizarCompra;

    CarrinhoProdutoListAdapter carrinhoProdutoAdapter;
    CarrinhoServicoListAdapter carrinhoServicoAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        viewPager.setAdapter(new MenuFragmentAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupBottomSheetCarrinho();
        setupCarrinho();

//        consumirApiInfnet();
    }

    private void setupBottomSheetCarrinho() {
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.bottom_sheet_carrinho, null);

        // TODO: Remover após implementação do adicionar produto/serviço ao carrinho
        Produto produtoTeste = new Produto();
        produtoTeste.preco = 30;
        produtoTeste.titulo = "Coleira";
        produtoTeste.url = "";
        IPetApplication.carrinho.pedido.adicionarProduto(produtoTeste);

        Servico servicoTeste = new Servico();
        servicoTeste.preco = 120;
        servicoTeste.titulo = "Consulta";
        servicoTeste.url = "";
        IPetApplication.carrinho.pedido.adicionarServico(servicoTeste);
        // TODO: Remover após implementação do adicionar produto/serviço ao carrinho

        buttonFinalizarCompra = sheetView.findViewById(R.id.button_finalizar_compra);
        buttonFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: salvar pedido no firebase e executar código abaixo no callback
                IPetApplication.carrinho.limparCarrinho();
                Toast.makeText(getActivity(), "Compra finalizada com sucesso", Toast.LENGTH_SHORT).show();

                bottomSheetDialog.hide();
                hideFooter();
                setupBottomSheetCarrinho();
            }
        });

        carrinhoProdutoAdapter = new CarrinhoProdutoListAdapter(IPetApplication.carrinho.pedido.produtoList);
        RecyclerView produtoRecyclerView = sheetView.findViewById(R.id.carrinho_produto_recycler_view);
        produtoRecyclerView.setHasFixedSize(true);
        produtoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        produtoRecyclerView.setAdapter(carrinhoProdutoAdapter);

        carrinhoServicoAdapter = new CarrinhoServicoListAdapter(IPetApplication.carrinho.pedido.servicoList);
        RecyclerView servicoRecyclerView = sheetView.findViewById(R.id.carrinho_servico_recycler_view);
        servicoRecyclerView.setHasFixedSize(true);
        servicoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        servicoRecyclerView.setAdapter(carrinhoServicoAdapter);

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(sheetView);
    }

    private void setupCarrinho(){

        footerCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });
    }

    public void hideFooter(){
        AnimationUtil.slideDown(footerCarrinho);
    }

    public void showFooter(){
        AnimationUtil.slideUp(footerCarrinho);
    }

    public class MenuFragmentAdapter extends FragmentPagerAdapter {

        MenuFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ProdutoFragment();
                case 1:
                    return new ServicoFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Produtos";
                case 1:
                    return "Serviços";
            }
            return null;
        }
    }
}
