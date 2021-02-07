package ba.sum.fpmoz.mim.ui.fragments.classes;

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

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Student;
import ba.sum.fpmoz.mim.ui.adapters.StudentsInClassAdapter;


public class ListStudentsInClass extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    StudentsInClassAdapter adapter;
    RecyclerView studentListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View userListView = inflater.inflate(R.layout.activity_students_in_class_list, container, false);
        this.studentListView = userListView.findViewById(R.id.studentListView);

        this.db = FirebaseDatabase.getInstance();
        String key = getActivity().getIntent().getStringExtra("CLASS_ID");
        this.ref=this.db.getReference("razredi/").child(key).child("učenici");
        this.studentListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions
                .Builder<Student>()
                .setQuery(this.ref, Student.class).build();
        this.adapter = new StudentsInClassAdapter(options);
        this.studentListView.setAdapter(this.adapter);

        return studentListView;
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
