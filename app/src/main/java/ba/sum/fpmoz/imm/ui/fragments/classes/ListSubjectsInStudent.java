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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.model.Subject;
import ba.sum.fpmoz.imm.ui.adapters.SubjectsProfesorAdapter;

public class ListSubjectsInStudent extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    SubjectsProfesorAdapter adapter;
    RecyclerView subjectListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View subjectListView = inflater.inflate(R.layout.activity_subjects_in_students_list, container, false);
        this.subjectListView = subjectListView.findViewById(R.id.subjectListView);

        this.db = FirebaseDatabase.getInstance();
        String key = getActivity().getIntent().getStringExtra("STUDENT_ID");
        this.ref=this.db.getReference("učenici/").child(key).child("predmeti");
        this.subjectListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Subject> options = new FirebaseRecyclerOptions
                .Builder<Subject>()
                .setQuery(this.ref, Subject.class).build();
        this.adapter = new SubjectsProfesorAdapter(options);
        this.subjectListView.setAdapter(this.adapter);
        return subjectListView;
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
