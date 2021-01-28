package ba.sum.fpmoz.mim.ui.fragments.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Admin;
import ba.sum.fpmoz.mim.model.Student;
import ba.sum.fpmoz.mim.model.User;


public class AddUsersFragment extends Fragment {

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref, stu, nas;
    EditText studentNameInp;
    EditText studentEmailInp;
    EditText studentPasswordInp, teacherCourseInp, studentGradeInp;
    CheckBox teacherChck;
    Button addStudentBtn;
    TextView messageTxt1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        final View userAdminView = inflater.inflate(R.layout.activity_user_admin, container, false);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("korisnici");
        this.nas = this.db.getReference("nastavnici");
        this.stu = this.db.getReference("učenici");

        this.studentNameInp = userAdminView.findViewById(R.id.studentNameInp);
        this.studentEmailInp = userAdminView.findViewById(R.id.studentEmailInp);
        this.studentPasswordInp = userAdminView.findViewById(R.id.studentPasswordInp);
        this.addStudentBtn = userAdminView.findViewById(R.id.addStudentBtn);
        this.teacherCourseInp = userAdminView.findViewById(R.id.teacherCourseInp);
        this.studentGradeInp = userAdminView.findViewById(R.id.studentGradeInp);
        this.teacherChck = userAdminView.findViewById(R.id.teacherChck);
        this.messageTxt1 = userAdminView.findViewById(R.id.messageTxt1);

        teacherChck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked())
                {
                    teacherCourseInp.setVisibility(View.VISIBLE);
                    studentGradeInp.setVisibility(View.GONE);
                }
                else
                {
                    teacherCourseInp.setVisibility(View.GONE);
                    studentGradeInp.setVisibility(View.VISIBLE);
                }
            }
        });
        this.addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String displayName = studentNameInp.getText().toString();
                String email = studentEmailInp.getText().toString();
                String password = studentPasswordInp.getText().toString();
                String course = teacherCourseInp.getText().toString();
                String grade = studentGradeInp.getText().toString();

                if(teacherChck.isChecked()){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest
                                    .Builder()
                                    .setDisplayName(displayName)
                                    .build();
                            user.updateProfile(changeRequest).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()){
                                    studentEmailInp.setText("");
                                    studentPasswordInp.setText("");
                                    studentNameInp.setText("");
                                    messageTxt1.setText("Korisnički račun je uspješno napravljen.");
                                    String role="admin";
                                    User newUser = new User(user.getUid(), user.getEmail(), user.getDisplayName(), role);
                                    ref.child(user.getUid()).setValue(newUser);
                                    String id = ref.child(user.getUid()).setValue(newUser).toString();
                                    Admin a = new Admin(user.getUid(), user.getEmail(), user.getDisplayName());
                                   // nas.child(user.getUid()).setValue(a);
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Nastala je greška pri dodoavanju korisnika: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest
                                    .Builder()
                                    .setDisplayName(displayName)
                                    .build();
                            user.updateProfile(changeRequest).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()){
                                    studentEmailInp.setText("");
                                    studentPasswordInp.setText("");
                                    studentNameInp.setText("");
                                    messageTxt1.setText("Korisnički račun je uspješno napravljen.");
                                    String role = "učenik";
                                    User newUser = new User(user.getUid(), user.getEmail(), user.getDisplayName(), role);
                                    ref.child(user.getUid()).setValue(newUser);
                                    String id = ref.child(user.getUid()).setValue(newUser).toString();
                                    Student s = new Student(user.getUid(), user.getEmail(), user.getDisplayName(), grade);
                                    stu.child(user.getUid()).setValue(s);
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Nastala je greška pri dodavanju korisnika: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        return userAdminView;
}
}
