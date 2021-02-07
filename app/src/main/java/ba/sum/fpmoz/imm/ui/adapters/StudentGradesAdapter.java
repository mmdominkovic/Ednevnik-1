package ba.sum.fpmoz.imm.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.model.Grade;

public class StudentGradesAdapter extends FirebaseRecyclerAdapter<Grade, StudentGradesAdapter.StudentGradeViewHolder> {

    public StudentGradesAdapter(@NonNull FirebaseRecyclerOptions<Grade> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentGradeViewHolder holder, int position, @NonNull Grade model) {
        holder.gradeValue.setText(model.getValue());
        holder.gradeDescription.setText(model.getDescription());
        holder.gradeLecture.setText(model.getLecture());
    }

    @NonNull
    @Override
    public StudentGradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ocjena_student, parent, false);
        StudentGradeViewHolder viewHolder = new StudentGradesAdapter.StudentGradeViewHolder(view);

        viewHolder.setOnClickListener(new Adapter.ClickListener() {
            @Override
            public void OnClickListener(View v, int position) {

            }

            @Override
            public void OnLongClickListener(View v, int position) {

            }
        });
        return viewHolder;
    }

    public class StudentGradeViewHolder extends RecyclerView.ViewHolder{
        TextView gradeValue;
        TextView gradeDescription;
        TextView gradeLecture;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public StudentGradeViewHolder(@NonNull final View itemView) {
            super(itemView);

            gradeValue = itemView.findViewById(R.id.ocjenaTxt);
            gradeDescription = itemView.findViewById(R.id.opisTxt);
            gradeLecture = itemView.findViewById(R.id.cjelinaTxt);

            itemView.setOnClickListener((v) -> clickListener.OnClickListener(v, getAdapterPosition()));
            itemView.setOnLongClickListener((v) -> {
                clickListener.OnClickListener(v, getAdapterPosition());
                return true;
            } );
        }
    }
}
