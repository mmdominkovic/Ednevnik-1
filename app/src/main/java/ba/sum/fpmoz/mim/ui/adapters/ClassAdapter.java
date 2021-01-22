package ba.sum.fpmoz.mim.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Class;
public class ClassAdapter extends FirebaseRecyclerAdapter<Class, ClassAdapter.ClassViewHolder> {

    public ClassAdapter(@NonNull FirebaseRecyclerOptions<Class>options){
    super(options);
}

    @Override
    protected void onBindViewHolder(@NonNull ClassAdapter.ClassViewHolder holder, int position, @NonNull Class model) {
        holder.classLevel.setText(model.level);
        holder.className.setText(model.name);
        holder.classSubject.setText(model.subject);
        holder.classTeacher.setText(model.classTeacher);
    }


    @NonNull
    @Override
    public ClassAdapter.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_class_admin,parent,false);
        ClassViewHolder viewHolder = new ClassViewHolder(view);

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
    public class ClassViewHolder extends RecyclerView.ViewHolder{
        TextView classLevel;
        TextView className;
        TextView classTeacher;
        TextView classSubject;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public ClassViewHolder(@NonNull View itemView){
            super(itemView);
            classLevel=itemView.findViewById(R.id.levelClassInp);
            className=itemView.findViewById(R.id.classNameInp);
            classSubject=itemView.findViewById(R.id.subjectInp);
            classTeacher=itemView.findViewById(R.id.classTeacherInp);

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
