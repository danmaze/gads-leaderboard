package com.example.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeadersAdapter extends RecyclerView.Adapter<LeadersAdapter.LeaderViewHolder> {

    private Context context;
    private ArrayList<Leader> leaders;

    class LeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView leaderName = itemView.findViewById(R.id.leader_name);
        private TextView leaderInformation = itemView.findViewById(R.id.leader_information);

        LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public LeadersAdapter(ArrayList<Leader> leaders) {
        this.leaders = leaders;
    }

    @NonNull
    @Override
    public LeadersAdapter.LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.leader_list_item, parent, false);
        return new LeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersAdapter.LeaderViewHolder holder, int position) {
        Leader leader = leaders.get(position);
        String leader_info = context.getString(R.string.learning_leader_info, leader.getHours(), leader.getCountry());
        holder.leaderName.setText(leader.getName());
        holder.leaderInformation.setText(leader_info);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

}
