package com.unal.personal.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unal.personal.R;
import com.unal.personal.adapters.CategoryAdapter;
import com.unal.personal.dataSource.CategoryDataSource;
import com.unal.personal.interfaces.OnCategoryListListener;
import com.unal.personal.structures.Category;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final java.lang.String ARG_COLUMN = "column";
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private int columns;
    OnCategoryListListener mCallBack;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        try {
            columns = b.getInt(ARG_COLUMN);
        } catch (Exception e) {
            columns = 2;
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        List<Category> categories = CategoryDataSource.getCategories(root.getContext());
        final RecyclerView list = (RecyclerView) root.findViewById(R.id.recycle_view);
        list.setAdapter(new CategoryAdapter(categories, mCallBack));
        list.setLayoutManager(new GridLayoutManager(root.getContext(), columns));
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnCategoryListListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnCategoryListListener");
        }
    }
}
