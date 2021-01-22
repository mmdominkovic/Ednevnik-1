package ba.sum.fpmoz.mim;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.mim.model.Student;
import ba.sum.fpmoz.mim.model.Teacher;


public class UserAdminActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText studentNameInp;
    EditText studentSurnameInp;
    EditText studentUidInp;
    EditText studentEmailInp;
    EditText studentPasswordInp, teacherCourseInp;
    CheckBox teacherChck;
    Button addStudentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_user_admin);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("ednevnik/ucenici");
        this.studentNameInp = findViewById(R.id.studentNameInp);
        this.studentSurnameInp = findViewById(R.id.studentSurnameInp);
        this.studentUidInp = findViewById(R.id.studentUid);
        this.studentEmailInp = findViewById(R.id.studentEmailInp);
        this.studentPasswordInp = findViewById(R.id.studentPasswordInp);
        this.teacherCourseInp = findViewById(R.id.teacherCourseInp);
        this.teacherChck = findViewById(R.id.teacherChck);
        this.addStudentBtn = findViewById(R.id.addStudentBtn);

        teacherChck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked())
                {
                    studentUidInp.setVisibility(View.GONE);
                    teacherCourseInp.setVisibility(View.VISIBLE);
                }
                else
                {
                    studentUidInp.setVisibility(View.VISIBLE);
                    teacherCourseInp.setVisibility(View.GONE);
                }

            }

        });

        this.addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = studentNameInp.getText().toString();
                String studentSurname = studentSurnameInp.getText().toString();
                String studentUid = studentUidInp.getText().toString();
                String studentEmail = studentEmailInp.getText().toString();
                String studentPassword = studentPasswordInp.getText().toString();
                String teacherCourse = teacherCourseInp.getText().toString();

                if(teacherChck.isChecked()) {
                    ref.push().setValue(
                            new Teacher(studentName, studentSurname, teacherCourse, studentEmail, studentPassword));
                }
                else {
                    ref.push().setValue(
                            new Student(studentUid, studentName, studentSurname, studentEmail, studentPassword));
                }
                mAuth.createUserWithEmailAndPassword(studentEmail,studentPassword).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    UserProfileChangeRequest changeRequest = new UserProfileChangeRequest
                                            .Builder()
                                            .setDisplayName(studentName)
                                            .build();

                                    user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                studentNameInp.setText("");
                                                studentSurnameInp.setText("");
                                                studentUidInp.setText("");
                                                studentEmailInp.setText("");
                                                studentPasswordInp.setText("");
                                                teacherCourseInp.setText("");

                                                Log.d("Poruka", "Profil je ažuriran");
                                            }
                                        }
                                    });
                                }
                            }
                        }
                );

                studentNameInp.setText("");
                studentSurnameInp.setText("");
                studentUidInp.setText("");
                studentEmailInp.setText("");
                studentPasswordInp.setText("");
                teacherCourseInp.setText("");
                Toast.makeText(
                        getApplicationContext(),
                        "Uspješno ste dodali korisnika.", Toast.LENGTH_LONG).show();
            }
        });


    }
}
