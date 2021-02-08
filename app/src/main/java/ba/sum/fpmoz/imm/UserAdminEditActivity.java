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

import ba.sum.fpmoz.imm.model.Student;

public class UserAdminEditActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    EditText studentNameEdt;
    EditText studentSurnameEdt;
    EditText studentUidEdt;
    Button studentEditBtn;
    String email1;
    String grade1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin_edit);

        this.studentNameEdt = findViewById(R.id.studentNameEdt);
        this.studentUidEdt = findViewById(R.id.studentUidEdt);
        this.studentEditBtn = findViewById(R.id.studentEditBtn);

        final String key = getIntent().getStringExtra("USER_ID");
        this.db = FirebaseDatabase.getInstance();
        this.ref = (DatabaseReference) this.db.getReference("učenici/").child(key);

        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Student student = snapshot.getValue(Student.class);
                    studentNameEdt.setText(student.name);
                    studentUidEdt.setText(student.email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });


        this.studentEditBtn.setOnClickListener((v) -> {
            email1=studentNameEdt.getText().toString();
            grade1=studentUidEdt.getText().toString();
            ref.child("name").setValue(email1);

        });

    }
}