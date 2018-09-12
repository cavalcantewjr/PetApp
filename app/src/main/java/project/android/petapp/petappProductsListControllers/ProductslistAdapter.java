package project.android.petapp.petappProductsListControllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import project.android.petapp.R;
import project.android.petapp.petappDao.ImgRequester;
import project.android.petapp.petappDao.ProductDao;

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
public class ProductslistAdapter extends RecyclerView.Adapter<ProductslistHolder> {

    private List<ProductDao> productList;
    private ImgRequester imgRequester;

    public ProductslistAdapter(List<ProductDao> productList) {
        this.productList = productList;
        imgRequester = ImgRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductslistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.pet_product_card;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ProductslistHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductslistHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ProductDao product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            imgRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
