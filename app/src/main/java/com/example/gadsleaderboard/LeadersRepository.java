package com.example.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gadsleaderboard.ApiUtil.*;

public class LeadersRepository {

    private static LeadersRepository leadersRepository;

    public static LeadersRepository getInstance(){
        if (leadersRepository == null){
            leadersRepository = new LeadersRepository();
        }
        return leadersRepository;
    }

    private LeadersApi leadersApi;

    public LeadersRepository() {
        leadersApi = RetrofitService.createService(LeadersApi.class);
    }

    public MutableLiveData<List<Leader>> getLeaders(Endpoint endpoint) {
        final MutableLiveData<List<Leader>> leadersData = new MutableLiveData<>();
        switch (endpoint) {
            case LEARNING_HOURS:
                leadersApi.getLearningHoursLeaders().enqueue(new Callback<List<Leader>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Leader>> call, @NonNull Response<List<Leader>> response) {
                        if (response.isSuccessful())
                            leadersData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Leader>> call, @NonNull Throwable throwable) {
                        leadersData.setValue(null);
                    }
                });
            case SKILL_IQ:
                leadersApi.getSkillIqLeaders().enqueue(new Callback<List<Leader>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Leader>> call, @NonNull Response<List<Leader>> response) {
                        if (response.isSuccessful())
                            leadersData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Leader>> call, @NonNull Throwable throwable) {
                        leadersData.setValue(null);
                    }
                });
        }
        return leadersData;
    }
}