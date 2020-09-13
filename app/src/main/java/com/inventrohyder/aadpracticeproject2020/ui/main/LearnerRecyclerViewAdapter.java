package com.inventrohyder.aadpracticeproject2020.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inventrohyder.aadpracticeproject2020.Learner;
import com.inventrohyder.aadpracticeproject2020.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class LearnerRecyclerViewAdapter extends RecyclerView.Adapter<LearnerRecyclerViewAdapter.ViewHolder> {

    List<Learner> mLearners;
    Context mContext;

    public LearnerRecyclerViewAdapter(List<Learner> learners, Context context) {
        mLearners = learners;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(this.mContext).inflate(R.layout.learner_item, parent, false);
        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Learner learner = mLearners.get(position);

        holder.mTxtView_learnerName.setText(learner.getName());
        holder.mTxtView_learnerCountry.setText(learner.getCountry());

        Picasso.get().load(learner.getBadgeUrl()).into(holder.mImgView_learnerBanner);

    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTxtView_learnerName;
        TextView mTxtView_learnerCountry;
        ImageView mImgView_learnerBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtView_learnerName = itemView.findViewById(R.id.learner_name);
            mTxtView_learnerCountry = itemView.findViewById(R.id.learner_description);
            mImgView_learnerBanner = itemView.findViewById(R.id.learner_image_banner);
        }
    }

}
