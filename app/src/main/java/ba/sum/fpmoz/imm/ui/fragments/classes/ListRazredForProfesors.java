package ba.sum.fpmoz.imm.ui.fragments.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.model.Class;
import ba.sum.fpmoz.imm.ui.adapters.RazrediProfesors;

public class ListRazredForProfesors extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    RazrediProfesors adapter;
    RecyclerView classListView;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View classListView = inflater.inflate(R.layout.activity_razred_list_for_profesors, container, false);
        this.classListView=classListView.findViewById(R.id.classListView);
        this.db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        this.ref=this.db.getReference("nastavnici").child(userID).child("moji_razredi");
        this.classListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Class> options =new FirebaseRecyclerOptions.Builder<Class>().setQuery(this.ref, Class.class).build();
        this.adapter = new RazrediProfesors(options);
        this.classListView.setAdapter(this.adapter);
        return classListView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}