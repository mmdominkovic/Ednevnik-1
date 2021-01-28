package ba.sum.fpmoz.mim.ui.fragments.subjects;

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
import ba.sum.fpmoz.mim.model.Subject;
import ba.sum.fpmoz.mim.ui.adapters.ClassAdapter;
import ba.sum.fpmoz.mim.ui.adapters.SubjectAdapter;

public class ListSubjectFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    SubjectAdapter adapter;
    RecyclerView subjectListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View subjectListView=inflater.inflate(R.layout.activity_subject_list,container,false);
        this.subjectListView=subjectListView.findViewById(R.id.subjectListView);
        this.db=FirebaseDatabase.getInstance();
        this.ref=this.db.getReference("predmeti");
        this.subjectListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Subject> options=new FirebaseRecyclerOptions.Builder<Subject>().setQuery(this.ref,Subject.class).build();
        this.adapter=new SubjectAdapter(options);
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
