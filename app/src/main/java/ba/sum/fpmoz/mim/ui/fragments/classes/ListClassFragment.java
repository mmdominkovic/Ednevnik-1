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
import ba.sum.fpmoz.mim.model.Class;
import ba.sum.fpmoz.mim.ui.adapters.ClassAdapter;


public class ListClassFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    ClassAdapter adapter;
    RecyclerView classListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View classListView = inflater.inflate(R.layout.activity_class_list, container, false);
      this.classListView=classListView.findViewById(R.id.classListView);
      this.db = FirebaseDatabase.getInstance();
      this.ref =this.db.getReference("ednevnik/razredi");
      this.classListView.setLayoutManager(new LinearLayoutManager(getContext()));

      FirebaseRecyclerOptions<Class> options =new FirebaseRecyclerOptions.Builder<Class>().setQuery(this.ref, Class.class).build();
      this.adapter = new ClassAdapter(options);
      this.classListView.setAdapter(this.adapter);
        return classListView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}