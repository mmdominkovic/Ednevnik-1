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

import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.TabbedSubjectsInStudents;
import ba.sum.fpmoz.imm.model.Student;


public class StudentsProfesorAdapter extends FirebaseRecyclerAdapter<Student, StudentsProfesorAdapter.StudentClassViewHolder> {

    public StudentsProfesorAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentClassViewHolder holder, int position, @NonNull Student model) {
        holder.studentName.setText(model.getName());
    }

    @NonNull
    @Override
    public StudentClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_students_profesor, parent, false);
        StudentClassViewHolder viewHolder = new StudentsProfesorAdapter.StudentClassViewHolder(view);

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

    public class StudentClassViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        Button studentGradeBtn;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public StudentClassViewHolder(@NonNull final View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentNameTxt);
            studentGradeBtn = itemView.findViewById(R.id.studentGradeBtn);
            String name = itemView.findViewById(R.id.studentNameTxt).toString();
            studentGradeBtn.setOnClickListener((v) -> {

                String key = getRef(getAdapterPosition()).getKey();
                Intent i = new Intent(itemView.getContext(), TabbedSubjectsInStudents.class);
                i.putExtra("STUDENT_ID", key);
                i.putExtra("STUDENT_NAME", name);
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
