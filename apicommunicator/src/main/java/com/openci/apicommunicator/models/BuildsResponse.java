package com.openci.apicommunicator.models;

import com.google.gson.annotations.SerializedName;
import com.openci.apicommunicator.models.submodels.Pagination;

import java.util.ArrayList;

public class BuildsResponse {
    @SerializedName("@type")
    private String mType;

    @SerializedName("@href")
    private String mHREF;

    @SerializedName("@representation")
    private String mRepresentation;

    @SerializedName("@pagination")
    private Pagination mPagination;

    @SerializedName("repositories")
    private ArrayList<BuildResponse> mBuilds;

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmHREF() {
        return mHREF;
    }

    public void setmHREF(String mHREF) {
        this.mHREF = mHREF;
    }

    public String getmRepresentation() {
        return mRepresentation;
    }

    public void setmRepresentation(String mRepresentation) {
        this.mRepresentation = mRepresentation;
    }

    public Pagination getmPagination() {
        return mPagination;
    }

    public void setmPagination(Pagination mPagination) {
        this.mPagination = mPagination;
    }

    public ArrayList<BuildResponse> getmBuilds() {
        return mBuilds;
    }

    public void setmBuilds(ArrayList<BuildResponse> mBuilds) {
        this.mBuilds = mBuilds;
    }
}
