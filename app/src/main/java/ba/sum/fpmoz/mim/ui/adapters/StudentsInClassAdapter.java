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
import ba.sum.fpmoz.mim.model.Student;


public class StudentsInClassAdapter extends FirebaseRecyclerAdapter<Student, StudentsInClassAdapter.StudentClassViewHolder> {

    public StudentsInClassAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentClassViewHolder holder, int position, @NonNull Student model) {
        holder.studentName.setText(model.getName());
    }

    @NonNull
    @Override
    public StudentClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_students_in_class, parent, false);
        StudentClassViewHolder viewHolder = new StudentsInClassAdapter.StudentClassViewHolder(view);

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
        Button studentDeleteBtn;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public StudentClassViewHolder(@NonNull final View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentNameTxt);
            studentDeleteBtn = itemView.findViewById(R.id.studentDeleteBtn);

            studentDeleteBtn.setOnClickListener(v -> getRef(getAdapterPosition()).removeValue());

            itemView.setOnClickListener((v) -> clickListener.OnClickListener(v, getAdapterPosition()));
            itemView.setOnLongClickListener((v) -> {
                clickListener.OnClickListener(v, getAdapterPosition());
                return true;
            } );
        }
    }
}
