package com.inventrohyder.aadpracticeproject2020.ui.main;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsApi {

    @GET("api/hours")
    Call<List<Learner>> getLearnedLearners();

    @GET("api/skilliq")
    Call<List<Learner>> getSkilledLearners();
}
