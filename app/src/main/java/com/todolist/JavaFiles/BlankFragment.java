package com.todolist.JavaFiles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todolist.R;

public class BlankFragment extends Fragment {

    View view;
    String categoryTitle;
    TextView categoryTitleView;

    public BlankFragment(String dayys) {
        categoryTitle = dayys;
        categoryTitleView.setText(categoryTitle);

    }

    /*public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryTitleView = view.findViewById(R.id.categoryTitleView);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        return view;
    }
}