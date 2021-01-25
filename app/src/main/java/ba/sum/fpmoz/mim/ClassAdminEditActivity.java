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

import ba.sum.fpmoz.mim.model.Class;

public class ClassAdminEditActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText classLevelEdt;
    EditText classNameEdt;
    EditText classSubjectEdt;
    EditText classTeacherEdt;
    Button classEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_admin_edit);

        this.classLevelEdt=findViewById(R.id.classLevelEdt);
        this.classNameEdt=findViewById(R.id.classNameEdt);
        this.classSubjectEdt=findViewById(R.id.classSubjectEdt);
        this.classTeacherEdt=findViewById(R.id.classTeacherEdt);
        this.classEditBtn=findViewById(R.id.classEditBtn);

        final String key=getIntent().getStringExtra("CLASS_ID");
        this.db=FirebaseDatabase.getInstance();
        this.ref=this.db.getReference("ednevnik/razredi/").child(key);

        this.classEditBtn.setOnClickListener((v) ->{
            Class c=new Class();
            c.level=classLevelEdt.getText().toString();
            c.name=classNameEdt.getText().toString();
            c.subject=classSubjectEdt.getText().toString();
            c.classTeacher=classTeacherEdt.getText().toString();
            ref.setValue(c);


        } );
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Class klass=snapshot.getValue(Class.class);
                assert klass!=null;
                classLevelEdt.setText(klass.level);
                classNameEdt.setText(klass.name);
                classSubjectEdt.setText(klass.subject);
                classTeacherEdt.setText(klass.classTeacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
