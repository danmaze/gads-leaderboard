package com.example.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeadersFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ArrayList<Leader> leadersList = new ArrayList<>();
    private LeadersAdapter leadersAdapter;
    private RecyclerView leadersRecyclerView;
    private LeadersViewModel leadersViewModel;
    private int pageIndex;

    public static LeadersFragment newInstance(int index) {
        LeadersFragment fragment = new LeadersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leadersViewModel = ViewModelProviders.of(this).get(LeadersViewModel.class);
        pageIndex = 1;
        if (getArguments() != null) {
            pageIndex = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        leadersRecyclerView = root.findViewById(R.id.leaders_recyclerview);
        leadersViewModel.getLeaders(pageIndex).observe(this, new Observer<List<Leader>>() {
            @Override
            public void onChanged(List<Leader> leaders) {
                if ( leaders != null ) {
                    leadersList.addAll(leaders);
                    leadersAdapter.notifyDataSetChanged();
                }
            }
        });
        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {
        if (leadersAdapter == null) {
            leadersAdapter = new LeadersAdapter(getActivity(), leadersList, pageIndex);
            leadersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            leadersRecyclerView.setAdapter(leadersAdapter);
            leadersRecyclerView.setItemAnimator(new DefaultItemAnimator());
            leadersRecyclerView.setNestedScrollingEnabled(true);
        } else {
            leadersAdapter.notifyDataSetChanged();
        }
    }
}