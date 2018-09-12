package project.android.petapp.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ProductItemDecoration extends RecyclerView.ItemDecoration {
    private int largePadding;
    private int smallPadding;

    public ProductItemDecoration(int largePadding, int smallPadding) {
        this.largePadding = largePadding;
        this.smallPadding = smallPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = smallPadding;
        outRect.right = largePadding;
    }
}
