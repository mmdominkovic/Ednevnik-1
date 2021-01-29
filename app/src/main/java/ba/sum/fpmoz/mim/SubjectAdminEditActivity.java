package ba.sum.fpmoz.mim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.mim.model.Subject;

public class SubjectAdminEditActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;

    EditText subjectNameEdt;
    Button subjectEditBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_admin_edit);
        this.subjectNameEdt=findViewById(R.id.subjectNameEdt);
        this.subjectEditBtn=findViewById(R.id.subjectEditBtn);

        final String key=getIntent().getStringExtra("SUBJECT_ID");
        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("predmeti/").child(key);

        this.subjectEditBtn.setOnClickListener((v)->{
            Subject s=new Subject();
            s.uid=key;
            s.name=subjectNameEdt.getText().toString();
            ref.setValue(s);
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Subject subject=snapshot.getValue(Subject.class);
                assert subject != null;
                subjectNameEdt.setText(subject.name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
