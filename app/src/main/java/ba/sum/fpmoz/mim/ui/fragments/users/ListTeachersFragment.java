package ba.sum.fpmoz.mim.ui.fragments.users;

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
import ba.sum.fpmoz.mim.model.Teacher;
import ba.sum.fpmoz.mim.ui.adapters.TeacherAdapter;


public class ListTeachersFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    TeacherAdapter adapter;
    RecyclerView teacherListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View userListView = inflater.inflate(R.layout.activity_teacher_list, container, false);
        this.teacherListView = userListView.findViewById(R.id.teacherListView);
        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("nastavnici");
        this.teacherListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Teacher> options = new FirebaseRecyclerOptions
                .Builder<Teacher>()
                .setQuery(this.ref, Teacher.class).build();
        this.adapter = new TeacherAdapter(options);
        this.teacherListView.setAdapter(this.adapter);
        return userListView;
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
