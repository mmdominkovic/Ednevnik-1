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

public class SubjectListAdapter extends FirebaseRecyclerAdapter<Subject, SubjectListAdapter.SubjectListViewHolder> {
    public SubjectListAdapter(@NonNull FirebaseRecyclerOptions<Subject>options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectListViewHolder holder, int position, @NonNull Subject model) {
        holder.subjectName.setText(model.getName());
    }


    @NonNull
    @Override
    public SubjectListAdapter.SubjectListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subject_to_class_admin,parent,false);
        SubjectListViewHolder viewHolder = new SubjectListViewHolder(view);

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
    public class SubjectListViewHolder extends RecyclerView.ViewHolder{
        TextView subjectName;
        Button subjectAddBtn, subjectDeleteBtn;
        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public SubjectListViewHolder(@NonNull View itemView){
            super(itemView);
            subjectName=itemView.findViewById(R.id.subjectNameTxt);
            subjectAddBtn=itemView.findViewById(R.id.subjectAddBtn);
            subjectDeleteBtn=itemView.findViewById(R.id.ocjeneBtn);

            subjectDeleteBtn.setOnClickListener(v -> getRef(getAdapterPosition()).removeValue());

            subjectAddBtn.setOnClickListener((v) -> {
                String key = getRef(getAdapterPosition()).getKey();
                String uidpred = key;
                
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
