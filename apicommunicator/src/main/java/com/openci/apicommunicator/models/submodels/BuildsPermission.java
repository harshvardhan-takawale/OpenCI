package com.openci.apicommunicator.models.submodels;

import com.google.gson.annotations.SerializedName;

public class BuildsPermission {
    @SerializedName("read")
    private boolean mRead;

    @SerializedName("cancel")
    private boolean mCancel;

    @SerializedName("restart")
    private boolean mRestart;

    public boolean ismRead() {
        return mRead;
    }

    public void setmRead(boolean mRead) {
        this.mRead = mRead;
    }

    public boolean ismCancel() {
        return mCancel;
    }

    public void setmCancel(boolean mCancel) {
        this.mCancel = mCancel;
    }

    public boolean ismRestart() {
        return mRestart;
    }

    public void setmRestart(boolean mRestart) {
        this.mRestart = mRestart;
    }
}
