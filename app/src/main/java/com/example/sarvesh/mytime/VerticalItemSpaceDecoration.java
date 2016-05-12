package com.example.sarvesh.mytime;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sarvesh on 4/16/2016.
 */
public class VerticalItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;

    public VerticalItemSpaceDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }
}
