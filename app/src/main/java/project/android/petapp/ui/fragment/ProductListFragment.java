package project.android.petapp.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.android.petapp.R;
import project.android.petapp.database.dao.ProductDao;
import project.android.petapp.ui.adapter.ProductItemDecoration;
import project.android.petapp.ui.adapter.ProductListAdapter;
import project.android.petapp.ui.listener.IconClickListener;

public class ProductListFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton floatButton;

    @BindView(R.id.product_grid)
    NestedScrollView productGrid;

    @BindView(R.id.app_bar)
    Toolbar appBar;

    @BindDrawable(R.mipmap.petfootprint_launcher_foreground)
    Drawable menuAbrir;

    @BindDrawable(R.drawable.close_menu)
    Drawable menuFechar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ButterKnife.bind(this, view);
        setUpToolbar(view);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        ProductListAdapter adapter = new ProductListAdapter(ProductDao.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.pet_staggered_product_grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.pet_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductItemDecoration(largePadding, smallPadding));
        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackground(getContext().getDrawable(R.drawable.pet_product_background_shape));
        }
        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        floatButton.setOnClickListener(
                new IconClickListener(
                        getContext(),
                        productGrid,
                        floatButton,
                        appBar,
                        new AccelerateDecelerateInterpolator(),
                        menuAbrir, // Menu abrir
                        menuFechar
                )
        ); // Menu fechar
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.pet_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
}
