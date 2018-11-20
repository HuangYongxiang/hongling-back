package com.hl.core.lib.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @Describe:
 * @Package: com.hl.core.lib.adapter
 * @Author: liyu
 * @Date: 2018/1/8 18:28
 * @Copyright: hl
 */
public class LayoutManagers {
    protected LayoutManagers() {
    }

    public static LayoutManagers.LayoutManagerFactory linear() {
        return new LayoutManagers.LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    public static LayoutManagers.LayoutManagerFactory linear(final int orientation, final boolean reverseLayout) {
        return new LayoutManagers.LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext(), orientation, reverseLayout);
            }
        };
    }

    public static LayoutManagers.LayoutManagerFactory grid(final int spanCount) {
        return new LayoutManagers.LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), spanCount);
            }
        };
    }

    public static LayoutManagers.LayoutManagerFactory grid(final int spanCount, final int orientation, final boolean reverseLayout) {
        return new LayoutManagers.LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, reverseLayout);
            }
        };
    }

    public static LayoutManagers.LayoutManagerFactory staggeredGrid(final int spanCount, final int orientation) {
        return new LayoutManagers.LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new StaggeredGridLayoutManager(spanCount, orientation);
            }
        };
    }

    public interface LayoutManagerFactory {
        RecyclerView.LayoutManager create(RecyclerView var1);
    }
}
