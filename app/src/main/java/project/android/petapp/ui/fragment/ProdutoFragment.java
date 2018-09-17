package project.android.petapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.android.petapp.R;
import project.android.petapp.database.dao.ProductDao;
import project.android.petapp.ui.adapter.ProductListAdapter;

public class ProdutoFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

//    @BindView(R.id.app_bar)
//    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produto, container, false);
//        View view = inflater.inflate(R.layout.fragment_produto, null);
        ButterKnife.bind(this, view);

//        setUpToolbar(view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ProductListAdapter adapter = new ProductListAdapter(ProductDao.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        return view;
    }

//    private void setUpToolbar(View view) {
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        if (activity != null) {
//            activity.setSupportActionBar(toolbar);
//        }
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        menuInflater.inflate(R.menu.toolbar_menu, menu);
//        super.onCreateOptionsMenu(menu, menuInflater);
//    }
}
