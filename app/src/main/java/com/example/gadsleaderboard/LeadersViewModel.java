package com.example.gadsleaderboard;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.gadsleaderboard.ApiUtil.Endpoint.LEARNING_HOURS;
import static com.example.gadsleaderboard.ApiUtil.Endpoint.SKILL_IQ;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Leader>> getLearningHoursLeaders() {
        ApiUtil apiUtil = ApiUtil.getInstance();
        ArrayList<Leader> learningHoursLeaders = apiUtil.getLeadersFromJson(LEARNING_HOURS);
        MutableLiveData<List<Leader>> mutableLeadersListLiveData = new MutableLiveData<>();
        mutableLeadersListLiveData.setValue(learningHoursLeaders);
        return mutableLeadersListLiveData;
    }

    public LiveData<List<Leader>> getSkillIQLeaders() {
        ApiUtil apiUtil = ApiUtil.getInstance();
        ArrayList<Leader> learningHoursLeaders = apiUtil.getLeadersFromJson(SKILL_IQ);
        MutableLiveData<List<Leader>> mutableLeadersListLiveData = new MutableLiveData<>();
        mutableLeadersListLiveData.setValue(learningHoursLeaders);
        return mutableLeadersListLiveData;
    }

}