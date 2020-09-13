package com.inventrohyder.aadpracticeproject2020.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PageViewModel extends ViewModel {

    private GadsRepository mLearnerRepository;

    public PageViewModel() {
        mLearnerRepository = new GadsRepository();
    }


    public LiveData<List<Learner>> getLearnedLearners() {
        return mLearnerRepository.getLearnedLearners();
    }

    public LiveData<List<Learner>> getSkilledLearners() {
        return mLearnerRepository.getSkilledLearners();
    }
}