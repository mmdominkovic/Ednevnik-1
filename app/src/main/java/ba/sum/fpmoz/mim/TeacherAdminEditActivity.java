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

import ba.sum.fpmoz.mim.model.Teacher;

public class TeacherAdminEditActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    EditText teacherNameEdt;
    EditText teacherSurnameEdt;
    Button teacherEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_admin_edit);

        this.teacherNameEdt = findViewById(R.id.teacherNameEdt);
        this.teacherSurnameEdt = findViewById(R.id.teacherSurnameEdt);
        this.teacherEditBtn = findViewById(R.id.teacherEditBtn);

        final String key = getIntent().getStringExtra("TEACHER_ID");

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("nastavnici/").child(key);

        this.teacherEditBtn.setOnClickListener((v) -> {
            String name = teacherNameEdt.getText().toString();
            String email = teacherSurnameEdt.getText().toString();
            ref.child("name").setValue(name);
            ref.child("email").setValue(email);

        });
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Teacher student = snapshot.getValue(Teacher.class);
                    teacherNameEdt.setText(student.name);
                    teacherSurnameEdt.setText(student.email);}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}