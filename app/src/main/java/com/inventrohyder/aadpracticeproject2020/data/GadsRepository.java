package com.inventrohyder.aadpracticeproject2020.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GadsRepository {

    String TAG = getClass().getSimpleName();


    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private GadsApi mGadsApi = mRetrofit.create(GadsApi.class);

    private LearnerCache mLearnerCache = new LearnerCache();

    public LiveData<List<Learner>> getLearnedLearners() {
        return getLearners(LearnerCache.LEARNED_CACHE);
    }

    public LiveData<List<Learner>> getSkilledLearners() {
        return getLearners(LearnerCache.SKILLED_CACHE);
    }

    private LiveData<List<Learner>> getLearners(String cachedKey) {
        LiveData<List<Learner>> cached = mLearnerCache.get(cachedKey);

        if (cached != null) {
            return cached;
        }

        MutableLiveData<List<Learner>> data = new MutableLiveData<>();

        mLearnerCache.put(cachedKey, data);

        Call<List<Learner>> call;

        switch (cachedKey) {
            case LearnerCache.LEARNED_CACHE:
                call = mGadsApi.getLearnedLearners();
                break;
            case LearnerCache.SKILLED_CACHE:
                call = mGadsApi.getSkilledLearners();
                break;
            default:
                call = null;
        }

        if (call != null) {
            call.enqueue(new Callback<List<Learner>>() {
                @Override
                public void onResponse(@NonNull Call<List<Learner>> call, @NonNull Response<List<Learner>> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "onResponse: Not Successful" + response.code());
                        return;
                    }

                    data.setValue(response.body());

                }

                @Override
                public void onFailure(@NonNull Call<List<Learner>> call, @NonNull Throwable t) {
                    // TODO
                    Log.e(TAG, "onFailure: " + t.getMessage(), t);
                }
            });
        } else {
            Log.d(TAG, "getLearners: Call is null");
        }

        return data;
    }


    static class LearnerCache {

        static final String LEARNED_CACHE = "learned";
        static final String SKILLED_CACHE = "skilled";


        HashMap<String, MutableLiveData<List<Learner>>> dataCache = new HashMap<>();

        void put(String key, MutableLiveData<List<Learner>> data) {
            dataCache.put(key, data);
        }

        LiveData<List<Learner>> get(String key) {
            return dataCache.get(key);
        }
    }


}
