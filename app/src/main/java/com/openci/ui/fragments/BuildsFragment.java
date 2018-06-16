package com.openci.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.vipulasri.timelineview.TimelineView;
import com.openci.R;
import com.openci.apicommunicator.callbacks.IAPICallBack;
import com.openci.apicommunicator.models.BuildResponse;
import com.openci.apicommunicator.models.BuildsResponse;
import com.openci.apicommunicator.models.submodels.Pagination;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

import static com.openci.apicommunicator.restservices.BuildsService.getBuilds;

public class BuildsFragment extends android.support.v4.app.Fragment {

    RecyclerView BuildsRecyclerView;
    private static String public_travis_token = null;
    private static String PREFS_NAME = "SHARED_PREFS";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        BuildsRecyclerView = (RecyclerView) inflater.inflate(R.layout.activity_builds, container,
                false);
        BuildsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return BuildsRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        public_travis_token = sharedPreferences.getString("public_travis_token", null);

        getBuilds(public_travis_token, null, new IAPICallBack() {
            @Override
            public void onSuccess(@NonNull Object value) {
                if(value instanceof BuildsResponse){
                    BuildsResponse buildsResponse = (BuildsResponse) value;
                    Pagination reposPagination = buildsResponse.getmPagination();
                    if(reposPagination.getCount() != 0){
                        BuildsAdapter adapter = new BuildsAdapter(getActivity(),buildsResponse.getmBuilds());
                        BuildsRecyclerView.setAdapter(adapter);
                    }
                    else {
                        // display on the screen
                        Toast.makeText(getActivity(), "No public repos",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onError(@NonNull String errorMessage) {
                // handle error response
            }
        });
    }


    public class BuildsAdapter extends RecyclerView.Adapter<BuildsAdapter.BuildsViewHolder> {
        private ArrayList<BuildResponse> builds;
        private Context mContext;

        public BuildsAdapter(Context mContext, ArrayList<BuildResponse> builds) {
            this.builds = builds;
            this.mContext = mContext;
        }

        @Override
        public BuildsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
            return new BuildsViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(BuildsViewHolder holder, int position) {
            BuildResponse build =  builds.get(position);
            holder.mSlug.setText(build.getmID());
            holder.mStatus.setText(build.getmState());
            holder.mStarttime.setText(build.getmStartedAt());
        }

        @Override
        public int getItemCount() {
            return builds.size();
        }

        @Override
        public int getItemViewType(int position) {
            return TimelineView.getTimeLineViewType(position,getItemCount());
        }

        public class BuildsViewHolder extends RecyclerView.ViewHolder {

            public TextView mStarttime;
            public TextView mSlug;
            public TextView mStatus;
            public TimelineView timelineView;

            public BuildsViewHolder(View itemView, int viewType) {
                super(itemView);
                mSlug = (TextView) itemView.findViewById(R.id.slugtv);
                mStarttime = (TextView) itemView.findViewById(R.id.starttimetv);
                mStatus = (TextView) itemView.findViewById(R.id.statustv);
                timelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
                timelineView.initLine(viewType);
            }
        }
    }

}
