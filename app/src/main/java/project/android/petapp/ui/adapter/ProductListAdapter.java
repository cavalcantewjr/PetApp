package project.android.petapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import project.android.petapp.R;
import project.android.petapp.database.ImgRequester;
import project.android.petapp.database.dao.ProductDao;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListHolder> {

    private List<ProductDao> productList;
    private ImgRequester imgRequester;

    public ProductListAdapter(List<ProductDao> productList) {
        this.productList = productList;
        imgRequester = ImgRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.card_produto;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ProductListHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListHolder holder, int position) {
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

    class ProductListHolder extends RecyclerView.ViewHolder {

        public NetworkImageView productImage;
        public TextView productTitle;
        public TextView productPrice;

        ProductListHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
