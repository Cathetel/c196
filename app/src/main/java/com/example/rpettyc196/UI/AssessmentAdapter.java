package com.example.rpettyc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.R;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView;
        private AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textAssessment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessment.get(position);
                    Intent intent = new Intent(context, AssessmentDetail.class);
                    intent.putExtra("id", current.getAssessmentID());
                    intent.putExtra("name", current.getAssessmentName());
                    context.startActivity(intent);

                }
            });
        }
    }
    private List<Assessment> mAssessment;
    private final Context context;
    private final LayoutInflater mInflater;
    public AssessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessment!=null){
            Assessment current = mAssessment.get(position);
            String name = current.getAssessmentName();
            holder.assessmentItemView.setText(name);
        }
        else{
            holder.assessmentItemView.setText("No Term Name");
        }
    }
    public void setAssessment(List<Assessment> assessment){
        mAssessment = assessment;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAssessment != null) {
            return mAssessment.size();
        }
        else {return 0;}
    }
}
