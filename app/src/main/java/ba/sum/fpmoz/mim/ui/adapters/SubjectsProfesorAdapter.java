package ba.sum.fpmoz.mim.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Subject;

public class SubjectsProfesorAdapter extends FirebaseRecyclerAdapter<Subject, SubjectsProfesorAdapter.SubjectClassViewHolder> {

    public SubjectsProfesorAdapter(@NonNull FirebaseRecyclerOptions<Subject> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectClassViewHolder holder, int position, @NonNull Subject model) {
        holder.subjectName.setText(model.getName());
    }

    @NonNull
    @Override
    public SubjectClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subjects_in_students, parent, false);
        SubjectClassViewHolder viewHolder = new SubjectsProfesorAdapter.SubjectClassViewHolder(view);

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
        TextView subjectName;
        Button ocjeneBtn;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public SubjectClassViewHolder(@NonNull final View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectNameTxt);
            ocjeneBtn = itemView.findViewById(R.id.ocjeneBtn);

            itemView.setOnClickListener((v) -> clickListener.OnClickListener(v, getAdapterPosition()));
            itemView.setOnLongClickListener((v) -> {
                clickListener.OnClickListener(v, getAdapterPosition());
                return true;
            } );
        }
    }
}
