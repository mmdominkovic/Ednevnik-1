package ba.sum.fpmoz.mim.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Class;
public class ClassAdapterForProfesors extends FirebaseRecyclerAdapter<Class, ClassAdapterForProfesors.ClassViewHolder> {

    public ClassAdapterForProfesors(@NonNull FirebaseRecyclerOptions<Class>options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ClassAdapterForProfesors.ClassViewHolder holder, int position, @NonNull Class model) {
        holder.className.setText(model.getName());
    }


    @NonNull
    @Override
    public ClassAdapterForProfesors.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_class_profesor,parent,false);
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
        TextView className;
        TextView classSubject;
        Button classDeleteBtn;
        Button classEditBtn;


        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public ClassViewHolder(@NonNull View itemView){
            super(itemView);
            className=itemView.findViewById(R.id.classNameTxt);
            classSubject=itemView.findViewById(R.id.classPredmetTxt);
            classEditBtn=itemView.findViewById(R.id.classEditBtn);
            classDeleteBtn=itemView.findViewById(R.id.classDeleteBtn);

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
