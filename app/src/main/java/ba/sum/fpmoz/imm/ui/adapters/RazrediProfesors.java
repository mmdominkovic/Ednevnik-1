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
import ba.sum.fpmoz.imm.TabbedStudentsProfesor;
import ba.sum.fpmoz.imm.model.Class;
public class RazrediProfesors extends FirebaseRecyclerAdapter<Class, RazrediProfesors.ClassViewHolder> {

    public RazrediProfesors(@NonNull FirebaseRecyclerOptions<Class> options){
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull RazrediProfesors.ClassViewHolder holder, int position, @NonNull Class model) {
        holder.razred.setText(model.getName());
    }
    @NonNull
    @Override
    public RazrediProfesors.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_razred_profesor,parent,false);
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
        TextView razred;
        Button ucenici;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public ClassViewHolder(@NonNull View itemView){
            super(itemView);
            razred=itemView.findViewById(R.id.ocjenaTxt);
            ucenici=itemView.findViewById(R.id.uceniciBtn);

            itemView.setOnClickListener((v)->{
                clickListener.OnClickListener(v, getAdapterPosition());
            });

            itemView.setOnLongClickListener((v)->{
                clickListener.OnLongClickListener(v, getAdapterPosition());
                return true;
            });

            ucenici.setOnClickListener((v) -> {
                String key = getRef(getAdapterPosition()).getKey();
                Intent i= new Intent(itemView.getContext(), TabbedStudentsProfesor.class);
                i.putExtra("CLASS_ID", key);
                itemView.getContext().startActivity(i);
            } );
        }


    }
}
