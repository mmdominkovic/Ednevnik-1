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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.imm.MainActivity;
import ba.sum.fpmoz.imm.Ocijeni;
import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.TabbedClassesInfo;
import ba.sum.fpmoz.imm.TabbedOcjeneProfesor;
import ba.sum.fpmoz.imm.TabbedOcjeneStudent;
import ba.sum.fpmoz.imm.TabbedSubjectsInStudents;
import ba.sum.fpmoz.imm.model.Subject;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListSubjectsInStudent;

public class StudentClassesAdapter extends FirebaseRecyclerAdapter<Subject,  StudentClassesAdapter.SubjectClassViewHolder> {

    public  StudentClassesAdapter(@NonNull FirebaseRecyclerOptions<Subject> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectClassViewHolder holder, int position, @NonNull Subject model) {
        holder.subjectName.setText(model.getName());
        holder.id.setText(model.getNastavnik());
    }

    @NonNull
    @Override
    public SubjectClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subjects_in_students, parent, false);
        SubjectClassViewHolder viewHolder = new  StudentClassesAdapter.SubjectClassViewHolder(view);

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

    public class SubjectClassViewHolder extends RecyclerView.ViewHolder{
        TextView subjectName, id, idd;
        Button ocjeneBtn;
        private FirebaseAuth mAuth;


        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public SubjectClassViewHolder(@NonNull final View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectNameTxt);
            id = itemView.findViewById(R.id.idTxt);

            ocjeneBtn = itemView.findViewById(R.id.ocjeneBtn);
            mAuth = FirebaseAuth.getInstance();

            ocjeneBtn.setOnClickListener((v) -> {
                String key =  getRef(getAdapterPosition()).getKey();
                Intent i = new Intent(itemView.getContext(), TabbedOcjeneStudent.class);

                i.putExtra("SUBJECT_ID", key);
                itemView.getContext().startActivity(i);

            } );

        }
    }
}
