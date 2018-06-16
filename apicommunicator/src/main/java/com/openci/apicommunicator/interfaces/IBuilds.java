package com.openci.apicommunicator.interfaces;

import com.openci.apicommunicator.models.BuildResponse;
import com.openci.apicommunicator.models.BuildsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface IBuilds {
    @Headers({
            "Travis-API-Version: 3",
            "User-Agent: MyClient/1.0.0"
    })
    @GET("repos")
    Call<BuildsResponse> getUser(@Header("Authorization") String authorization);
}
