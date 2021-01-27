package ba.sum.fpmoz.mim.ui.fragments.users;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Admin;
import ba.sum.fpmoz.mim.model.Student;
import ba.sum.fpmoz.mim.model.Teacher;


public class AddUsersFragment extends Fragment {

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText studentNameInp;
    EditText studentSurnameInp;
    EditText studentUidInp;
    EditText studentEmailInp;
    EditText studentRoleInp;
    EditText studentPasswordInp, teacherCourseInp;
    CheckBox teacherChck;
    Button addStudentBtn;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        final View userAdminView = inflater.inflate(R.layout.activity_user_admin, container, false);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("ednevnik/korisnici1");

        this.studentNameInp = userAdminView.findViewById(R.id.studentNameInp);
        this.studentSurnameInp = userAdminView.findViewById(R.id.studentSurnameInp);
        this.studentUidInp = userAdminView.findViewById(R.id.studentUid);
        this.studentEmailInp = userAdminView.findViewById(R.id.studentEmailInp);
        this.studentPasswordInp = userAdminView.findViewById(R.id.studentPasswordInp);
        this.studentRoleInp=userAdminView.findViewById(R.id.studentRoleInp);
        this.addStudentBtn = userAdminView.findViewById(R.id.addStudentBtn);
        this.teacherCourseInp = userAdminView.findViewById(R.id.teacherCourseInp);
        this.teacherChck = userAdminView.findViewById(R.id.teacherChck);

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
                String studentRole=studentRoleInp.getText().toString();
                String studentPassword = studentPasswordInp.getText().toString();
                String teacherCourse = teacherCourseInp.getText().toString();
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
                                    if(teacherChck.isChecked()){
                                        Teacher novi=new Teacher(studentName, studentSurname, teacherCourse, studentEmail, studentPassword,studentRole);
                                                ref.child(user.getUid())
                                                .setValue(novi);
                                    }else if(studentRole.equals("admin")){
                                        Admin novi=new Admin(studentName,studentSurname,studentEmail,studentPassword,studentRole);
                                                ref.child(user.getUid())
                                                .setValue(novi);
                                    }
                                    else {
                                        Student novi=new Student(studentUid, studentName, studentSurname, studentEmail, studentPassword,studentRole);
                                                ref.child(user.getUid())
                                                .setValue(novi);
                                    }

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
                                                studentRoleInp.setText("");

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
                studentRoleInp.setText("");
                Toast.makeText(
                        userAdminView.getContext(),
                        "Uspješno ste dodali korisnika.", Toast.LENGTH_LONG).show();
            }
        });

        return userAdminView;
    }
}
