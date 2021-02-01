package ba.sum.fpmoz.mim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.mim.model.Class;
import ba.sum.fpmoz.mim.model.Subject;

public class ClassAdminEditActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;

    EditText classNameEdt, subjectNameInp;
    Button classEditBtn, AddSubjectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_admin_edit);

        this.classNameEdt=findViewById(R.id.classNameEdt);
        this.classEditBtn=findViewById(R.id.classEditBtn);
        this.AddSubjectBtn=findViewById(R.id.AddSubjectBtn);
        this.subjectNameInp=findViewById(R.id.subjectNameInp);

        final String key=getIntent().getStringExtra("CLASS_ID");
        this.db=FirebaseDatabase.getInstance();
        this.ref=this.db.getReference("razredi/").child(key);


        this.classEditBtn.setOnClickListener((v) ->{
            Class c = new Class();
            c.name=classNameEdt.getText().toString();
            ref.setValue(c);
        } );

        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Class klass=snapshot.getValue(Class.class);
                assert klass!=null;
                classNameEdt.setText(klass.name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        this.AddSubjectBtn.setOnClickListener((v -> {
            Subject s = new Subject();
            s.name = subjectNameInp.getText().toString();
            String newSubjectKey = ref.push().getKey();
            ref.child("predmeti").setValue(new Subject(newSubjectKey, s.name, ""));
            Toast.makeText(ClassAdminEditActivity.this,
                    "Uspješno ste dodali predmet",Toast.LENGTH_LONG).show();
        }));
    }
}