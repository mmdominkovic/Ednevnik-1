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
import ba.sum.fpmoz.imm.model.Subject;
import ba.sum.fpmoz.imm.ui.adapters.StudentClassesAdapter;

public class ListClasses extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    StudentClassesAdapter adapter;
    RecyclerView classListView;
    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View classListView = inflater.inflate(R.layout.activity_class_list_for_students, container, false);
        this.classListView=classListView.findViewById(R.id.classListView);
        this.db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        this.ref=this.db.getReference("učenici").child(userID).child("predmeti");
        this.classListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Subject> options =new FirebaseRecyclerOptions.Builder<Subject>().setQuery(this.ref, Subject.class).build();
        this.adapter = new StudentClassesAdapter(options);
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