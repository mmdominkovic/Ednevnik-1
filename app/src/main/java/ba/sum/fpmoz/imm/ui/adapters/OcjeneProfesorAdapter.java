package ba.sum.fpmoz.imm.ui.adapters;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.imm.Ocijeni;
import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.TabbedSubjectsInStudents;
import ba.sum.fpmoz.imm.model.Grade;

public class OcjeneProfesorAdapter extends FirebaseRecyclerAdapter<Grade, OcjeneProfesorAdapter.StudentGradeViewHolder> {

    public OcjeneProfesorAdapter(@NonNull FirebaseRecyclerOptions<Grade> options) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ocjena_profesor, parent, false);
        StudentGradeViewHolder viewHolder = new OcjeneProfesorAdapter.StudentGradeViewHolder(view);

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
        Button AddBtn;
        Button DeleteBtn;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public StudentGradeViewHolder(@NonNull final View itemView) {
            super(itemView);

            gradeValue = itemView.findViewById(R.id.ocjenaTxt);
            gradeDescription = itemView.findViewById(R.id.opisTxt);
            gradeLecture = itemView.findViewById(R.id.cjelinaTxt);
            AddBtn = itemView.findViewById(R.id.AddBtn);
            DeleteBtn = itemView.findViewById(R.id.DeleteBtn);

                AddBtn.setOnClickListener((v) -> {

                String key = getRef(getAdapterPosition()).getParent().getKey();
                String keyy = getRef(getAdapterPosition()).getParent().getParent().getKey();
                Intent i = new Intent(itemView.getContext(), Ocijeni.class);
                i.putExtra("SUBJECT_ID", key);
                i.putExtra("STUDENT_ID", keyy);

                itemView.getContext().startActivity(i);

            } );

            itemView.setOnClickListener((v) -> clickListener.OnClickListener(v, getAdapterPosition()));
            itemView.setOnLongClickListener((v) -> {
                clickListener.OnClickListener(v, getAdapterPosition());
                return true;
            } );
        }
    }
}
