package com.openci.apicommunicator.models;

import com.google.gson.annotations.SerializedName;
import com.openci.apicommunicator.models.submodels.BuildsPermission;

public class BuildResponse {
    @SerializedName("@type")
    private String mType;

    @SerializedName("@href")
    private String mHREF;

    @SerializedName("@representation")
    private String mRepresentation;

    @SerializedName("@permissions")
    private BuildsPermission mBuildsPermission;

    @SerializedName("id")
    private String mID;

    @SerializedName("state")
    private String mState;

    @SerializedName("started_at")
    private String mStartedAt;

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

    public BuildsPermission getmBuildsPermission() {
        return mBuildsPermission;
    }

    public void setmBuildsPermission(BuildsPermission mBuildsPermission) {
        this.mBuildsPermission = mBuildsPermission;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmStartedAt() {
        return mStartedAt;
    }

    public void setmStartedAt(String mStartedAt) {
        this.mStartedAt = mStartedAt;
    }
}
