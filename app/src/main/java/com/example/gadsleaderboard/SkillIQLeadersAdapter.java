package com.example.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillIQLeadersAdapter extends RecyclerView.Adapter<SkillIQLeadersAdapter.LeaderViewHolder> {

    private Context context;
    private List<Leader> leaders;

    class LeaderViewHolder extends RecyclerView.ViewHolder {
        TextView leaderName = itemView.findViewById(R.id.leader_name);
        TextView leaderInformation = itemView.findViewById(R.id.leader_information);

        LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    SkillIQLeadersAdapter(Context context, List<Leader> leaders) {
        this.context = context;
        this.leaders = leaders;
    }

    @NonNull
    @Override
    public SkillIQLeadersAdapter.LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.skilliq_leader_item, parent, false);
        return new SkillIQLeadersAdapter.LeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQLeadersAdapter.LeaderViewHolder holder, int position) {
        Leader leader = leaders.get(position);
        String leader_info = context.getString(R.string.skill_iq_info, leader.getScore(), leader.getCountry());
        holder.leaderName.setText(leader.getName());
        holder.leaderInformation.setText(leader_info);
    }

    @Override
    public int getItemCount() {
        if (leaders != null)
            return leaders.size();
        else return 0;
    }

}
