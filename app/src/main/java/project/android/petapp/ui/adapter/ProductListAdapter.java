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
import project.android.petapp.database.entities.Produto;
import project.android.petapp.infrastructure.ImgRequester;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListHolder> {

    private List<Produto> produtoList;
    private ImgRequester imgRequester;

    public ProductListAdapter(List<Produto> produtoList) {
        this.produtoList = produtoList;
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
        if (produtoList != null && position < produtoList.size()) {
            Produto produto = produtoList.get(position);
            holder.productTitle.setText(produto.titulo);
            holder.productPrice.setText(produto.preco);
            imgRequester.setImageFromUrl(holder.productImage, produto.url);
        }
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    class ProductListHolder extends RecyclerView.ViewHolder {

        NetworkImageView productImage;
        TextView productTitle;
        TextView productPrice;

        ProductListHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
