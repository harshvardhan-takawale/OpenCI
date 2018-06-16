package com.openci.apicommunicator.restservices;

import android.support.annotation.Nullable;

import com.openci.apicommunicator.R;
import com.openci.apicommunicator.callbacks.IAPICallBack;
import com.openci.apicommunicator.interfaces.IBuilds;
import com.openci.apicommunicator.interfaces.IRepos;
import com.openci.apicommunicator.models.BuildsResponse;
import com.openci.apicommunicator.models.LibApp;
import com.openci.apicommunicator.models.ReposResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.openci.apicommunicator.restservices.APIClient.getPrivateClient;
import static com.openci.apicommunicator.restservices.APIClient.getPublicClient;

public class BuildsService {

    public static void getBuilds(String public_travis_token,  String private_travis_token, @Nullable final IAPICallBack callback){
        String public_authorization_token;
        String private_authorization_token;
        Retrofit retrofit;
        Call<BuildsResponse> buildsResponseCall;

        if(public_travis_token != null){
            public_authorization_token = "token " + public_travis_token;
            retrofit = getPublicClient();
            IBuilds builds = retrofit.create(IBuilds.class);
            buildsResponseCall = builds.getUser(
                    public_authorization_token
            );
        }
        else{
            private_authorization_token = "token " + private_travis_token;
            retrofit = getPrivateClient();
            IBuilds builds = retrofit.create(IBuilds.class);
            buildsResponseCall = builds.getUser(
                    private_authorization_token
            );
        }

        buildsResponseCall.enqueue(new Callback<BuildsResponse>() {
            @Override
            public void onResponse(Call<BuildsResponse> call, Response<BuildsResponse> response) {
                if(response != null){
                    callback.onSuccess(response.body());
                }
                else{
                    callback.onError(LibApp.getContext().getString(R.string.null_general_response));
                }
            }

            @Override
            public void onFailure(Call<BuildsResponse> call, Throwable t) {
                if(t != null && t.getMessage() != null){
                    callback.onError(t.getMessage());
                }
                else{
                    callback.onError(LibApp.getContext().getString(R.string.null_failure_response));
                }
            }
        });

    }
}
