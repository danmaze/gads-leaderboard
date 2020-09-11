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

import java.util.List;

public class HoursLeadersFragment extends Fragment {

    private RecyclerView leadersRecyclerView;
    private LeadersViewModel leadersViewModel;

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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        leadersRecyclerView = root.findViewById(R.id.leaders_recyclerview);
        leadersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        leadersRecyclerView.setItemAnimator(new DefaultItemAnimator());
        leadersRecyclerView.setNestedScrollingEnabled(true);
        leadersViewModel.getLearningHoursLeaders().observe(this, new Observer<List<Leader>>() {
            @Override
            public void onChanged(List<Leader> leaders) {
                HoursLeadersAdapter hoursLeadersAdapter = new HoursLeadersAdapter(getActivity(), leaders);
                leadersRecyclerView.setAdapter(hoursLeadersAdapter);
                hoursLeadersAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }

}