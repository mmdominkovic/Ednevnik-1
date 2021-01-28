package ba.sum.fpmoz.mim;

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

import ba.sum.fpmoz.mim.model.Student;

public class UserAdminEditActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    EditText studentNameEdt;
    EditText studentSurnameEdt;
    EditText studentUidEdt;
    Button studentEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin_edit);

        this.studentNameEdt = findViewById(R.id.studentNameEdt);
        this.studentSurnameEdt = findViewById(R.id.studentSurnameEdt);
        this.studentUidEdt = findViewById(R.id.studentUidEdt);
        this.studentEditBtn = findViewById(R.id.studentEditBtn);

        final String key = getIntent().getStringExtra("USER_ID");

        this.db = FirebaseDatabase.getInstance();
        this.ref = (DatabaseReference) this.db.getReference("učenici/").child(key);

        this.studentEditBtn.setOnClickListener((v) -> {
            Student s = new Student();
            s.name = studentNameEdt.getText().toString();
            s.uid = studentUidEdt.getText().toString();
            ref.setValue(s);

        });
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);
                assert student != null;
                studentNameEdt.setText(student.name);
                studentUidEdt.setText(student.uid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}