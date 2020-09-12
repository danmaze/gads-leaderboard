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

public class HoursLeadersFragment extends Fragment {

    private ArrayList<Leader> leadersList = new ArrayList<>();
    private RecyclerView leadersRecyclerView;
    private LeadersViewModel leadersViewModel;
    private HoursLeadersAdapter hoursLeadersAdapter;

    static HoursLeadersFragment newInstance() {
        return new HoursLeadersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leadersViewModel = ViewModelProviders.of(this).get(LeadersViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hours_leaders_fragment, container, false);
        leadersRecyclerView = root.findViewById(R.id.hours_leaders_rview);
        leadersViewModel.getLearningHoursLeaders().observe(this, new Observer<List<Leader>>() {
            @Override
            public void onChanged(List<Leader> leaders) {
                leadersList.addAll(leaders);
                hoursLeadersAdapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {
        if (hoursLeadersAdapter == null) {
            hoursLeadersAdapter = new HoursLeadersAdapter(getActivity(), leadersList);
            leadersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            leadersRecyclerView.setAdapter(hoursLeadersAdapter);
            leadersRecyclerView.setItemAnimator(new DefaultItemAnimator());
            leadersRecyclerView.setNestedScrollingEnabled(true);
        } else {
            hoursLeadersAdapter.notifyDataSetChanged();
        }
    }

}