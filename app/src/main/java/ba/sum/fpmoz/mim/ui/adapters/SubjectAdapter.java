package ba.sum.fpmoz.mim.ui.adapters;

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
import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.SubjectAdminEditActivity;
import ba.sum.fpmoz.mim.model.Subject;

public class SubjectAdapter extends FirebaseRecyclerAdapter<Subject, SubjectAdapter.SubjectViewHolder> {
    public SubjectAdapter(@NonNull FirebaseRecyclerOptions<Subject>options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position, @NonNull Subject model) {
        holder.subjectName.setText(model.getName());
    }


    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subject_admin,parent,false);
        SubjectViewHolder viewHolder = new SubjectViewHolder(view);

        viewHolder.setOnClickListener(new Adapter.ClickListener(){

            @Override
            public void OnClickListener(View v, int position) {

            }

            @Override
            public void OnLongClickListener(View v, int position) {

            }
        });
        return viewHolder;
    }
    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView subjectName;
        Button subjectEditBtn, subjectDeleteBtn;
        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public SubjectViewHolder(@NonNull View itemView){
            super(itemView);
            subjectName=itemView.findViewById(R.id.subjectNameTxt);
            subjectEditBtn=itemView.findViewById(R.id.subjectEditBtn);
            subjectDeleteBtn=itemView.findViewById(R.id.subjectDeleteBtn);

            subjectDeleteBtn.setOnClickListener(v -> getRef(getAdapterPosition()).removeValue());

            subjectEditBtn.setOnClickListener((v) -> {
                String key = getRef(getAdapterPosition()).getKey();
                Intent i= new Intent(itemView.getContext(), SubjectAdminEditActivity.class);
                i.putExtra("SUBJECT_ID", key);
                itemView.getContext().startActivity(i);
            } );

            itemView.setOnClickListener((v)->{
                clickListener.OnClickListener(v, getAdapterPosition());
            });
            itemView.setOnLongClickListener((v)->{
                clickListener.OnLongClickListener(v, getAdapterPosition());
                return true;
            });
        }


    }
}
