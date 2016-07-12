package com.unal.personal.gui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unal.personal.R;
import com.unal.personal.adapters.CategoryAdapter;
import com.unal.personal.dataSource.CategoryDataSource;
import com.unal.personal.structures.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        List<Category> categories = CategoryDataSource.getCategories(getContext());
        RecyclerView list = (RecyclerView) root.findViewById(R.id.recycle_view);
        list.setAdapter(new CategoryAdapter(categories, getActivity()));
        list.setLayoutManager(new GridLayoutManager(getContext(),2));
        return root;
    }
}
