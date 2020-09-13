package com.inventrohyder.aadpracticeproject2020.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inventrohyder.aadpracticeproject2020.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    public static final String ARG_SECTION_LEARNED = "learned";
    public static final String ARG_SECTION_SKILLED = "skilled";
    private static final String ARG_SECTION = "section";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(String section) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SECTION, section);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.learners_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final ProgressBar progressBar = root.findViewById(R.id.progressBar);


        assert getArguments() != null;
        String section = getArguments().getString(ARG_SECTION);


        assert section != null;
        switch (section) {
            case ARG_SECTION_LEARNED:
                pageViewModel.getLearnedLearners().observe(
                        getViewLifecycleOwner(),
                        learners -> {
                            recyclerView.setAdapter(
                                    new LearnerRecyclerViewAdapter(learners, getContext())
                            );
                            progressBar.setVisibility(View.GONE);
                        }
                );
                break;
            case ARG_SECTION_SKILLED:
                pageViewModel.getSkilledLearners().observe(
                        getViewLifecycleOwner(),
                        learners -> {
                            recyclerView.setAdapter(
                                    new LearnerRecyclerViewAdapter(learners, getContext())
                            );
                            progressBar.setVisibility(View.GONE);
                        }
                );
                break;
        }

        return root;
    }
}