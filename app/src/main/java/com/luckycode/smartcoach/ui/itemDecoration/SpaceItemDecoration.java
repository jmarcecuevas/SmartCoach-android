package com.luckycode.smartcoach.ui.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by marcelocuevas on 10/27/17.
 */


public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;
    private final boolean vertical;

    public SpaceItemDecoration(int space,boolean vertical) {
        this.space = space;
        this.vertical=vertical;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
            if(vertical)
                outRect.bottom = space;
            else
                outRect.right=space;
    }

    private class Item extends RecyclerView.ItemDecoration{


    }
}