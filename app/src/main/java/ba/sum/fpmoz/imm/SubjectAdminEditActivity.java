package ba.sum.fpmoz.imm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.imm.model.Subject;

public class SubjectAdminEditActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText subjectNameEdt;
    Button subjectEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_admin_edit);

        this.subjectNameEdt = findViewById(R.id.subjectNameEdt);
        this.subjectEditBtn = findViewById(R.id.subjectAddBtn);

        final String key = getIntent().getStringExtra("SUBJECT_ID");

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("predmeti/").child(key);

        this.subjectEditBtn.setOnClickListener((v) -> {
            String name = subjectNameEdt.getText().toString();
            ref.child("name").setValue(name);

        });

        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Subject student = snapshot.getValue(Subject.class);
                    subjectNameEdt.setText(student.name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
