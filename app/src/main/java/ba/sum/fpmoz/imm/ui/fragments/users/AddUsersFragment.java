package ba.sum.fpmoz.imm.ui.fragments.users;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ba.sum.fpmoz.imm.R;
import ba.sum.fpmoz.imm.model.Student;
import ba.sum.fpmoz.imm.model.Subject;
import ba.sum.fpmoz.imm.model.Teacher;
import ba.sum.fpmoz.imm.model.User;

import static android.content.ContentValues.TAG;

public class AddUsersFragment extends Fragment{

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref, stu, nas, pred, raz, pred2, profraz;
    EditText studentNameInp;
    EditText studentEmailInp;
    EditText studentPasswordInp;
    CheckBox teacherChck;
    Button addStudentBtn;
    TextView messageTxt1;
    Spinner spinnerNasPred, spinnerStuRaz;
    TextView spinnerTxt, spinnerTxt2;
    String item, item2;
    List<String> predmetno;
    List<String> razredno;
    String selectedItem, selectedItem2;
    HashMap<String,String> predmetiUid=new HashMap<String,String>();
    HashMap<String,String> razrediUid=new HashMap<String,String>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        final View userAdminView = inflater.inflate(R.layout.activity_user_admin, container, false);
        this.spinnerTxt=userAdminView.findViewById(R.id.spinnerTxt);
        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("korisnici");
        this.nas = this.db.getReference("nastavnici");
        this.stu = this.db.getReference("učenici");
        this.pred=this.db.getReference("predmeti");
        this.raz=this.db.getReference("razredi");
        predmetno=new ArrayList<>();
        razredno=new ArrayList<>();

        pred.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String >predmeti=new ArrayList<String>();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String prvi=ds.child("name").getValue().toString();
                    String drugi=ds.child("uid").getValue().toString();
                    predmetno.add(prvi);
                    predmeti.add(prvi);
                    predmetiUid.put(prvi,drugi);
                }
                spinnerNasPred=userAdminView.findViewById(R.id.spinnerNasPred);
                final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,predmeti);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerNasPred.setAdapter(arrayAdapter);
                spinnerNasPred.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem=arrayAdapter.getItem(position);
                        spinnerTxt.setText(selectedItem);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                spinnerTxt.setText(selectedItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        raz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> razredi = new ArrayList<String>();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String prvi=ds.child("name").getValue().toString();
                    String drugi=ds.child("uid").getValue().toString();
                    razredno.add(prvi);
                    razredi.add(prvi );
                    razrediUid.put(prvi,drugi);
                }
                spinnerStuRaz=userAdminView.findViewById(R.id.spinnerStudRaz);
                final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, razredi);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStuRaz.setAdapter(arrayAdapter);
                spinnerStuRaz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem2=arrayAdapter.getItem(position);
                        spinnerTxt2.setText(selectedItem2);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinnerTxt2.setText(selectedItem2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        this.studentNameInp = userAdminView.findViewById(R.id.studentNameInp);
        this.studentEmailInp = userAdminView.findViewById(R.id.studentEmailInp);
        this.studentPasswordInp = userAdminView.findViewById(R.id.studentPasswordInp);
        this.addStudentBtn = userAdminView.findViewById(R.id.addStudentBtn);
        this.teacherChck = userAdminView.findViewById(R.id.teacherChck);
        this.messageTxt1 = userAdminView.findViewById(R.id.messageTxt1);
        this.spinnerTxt=userAdminView.findViewById(R.id.spinnerTxt);
        this.spinnerTxt2=userAdminView.findViewById(R.id.spinnerTxt2);


        teacherChck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked())
                {
                    spinnerTxt.setVisibility(View.VISIBLE);
                    spinnerNasPred.setVisibility(View.VISIBLE);

                    spinnerTxt2.setVisibility(View.GONE);
                    spinnerStuRaz.setVisibility(View.GONE);
                }
                else
                {
                    spinnerTxt.setVisibility(View.GONE);
                    spinnerNasPred.setVisibility(View.GONE);

                    spinnerTxt2.setVisibility(View.VISIBLE);
                    spinnerStuRaz.setVisibility(View.VISIBLE);

                }
            }
        });


        this.addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String displayName = studentNameInp.getText().toString();
                String email = studentEmailInp.getText().toString();
                String password = studentPasswordInp.getText().toString();
                String course = selectedItem;
                String uidpred=predmetiUid.get(selectedItem);
                String uidraz=razrediUid.get(selectedItem2);

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
                                    String role="nastavnik";
                                    User newUser = new User(user.getUid(), user.getEmail(), user.getDisplayName(), role);
                                    ref.child(user.getUid()).setValue(newUser);
                                    String id = ref.child(user.getUid()).setValue(newUser).toString();
                                    Teacher t = new Teacher(user.getUid(), user.getEmail(), user.getDisplayName(), course, uidpred);
                                    nas.child(user.getUid()).setValue(t);
                                    profraz = db.getReference("nastavnici").child(user.getUid()).child("moji_razredi");
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
                                    Student s = new Student(user.getUid(), user.getEmail(), user.getDisplayName(), "");
                                    stu.child(user.getUid()).setValue(s);
                                    raz.child("učenici");
                                    raz.child(uidraz).child("učenici").child(user.getUid()).setValue(s);

                                    pred2 = raz.child(uidraz).child("predmeti");
                                    pred2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                                                Log.v(TAG,""+ childDataSnapshot.getKey());
                                                Log.v(TAG,""+ childDataSnapshot.child("name").getValue());
                                                Log.v(TAG,""+ childDataSnapshot.child("nastavnik").getValue());
                                                Log.v(TAG,""+ childDataSnapshot.child("razred").getValue());

                                                String key = childDataSnapshot.getKey();
                                                String name = childDataSnapshot.child("name").getValue().toString();
                                                String nastavnik = childDataSnapshot.child("nastavnik").getValue().toString();
                                                String razred = childDataSnapshot.child("razred").getValue().toString();
                                                Subject su = new Subject(key, name, nastavnik, razred);
                                                stu.child(user.getUid()).child("predmeti").child(key).setValue(su);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

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
