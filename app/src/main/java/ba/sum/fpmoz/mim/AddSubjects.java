package ba.sum.fpmoz.mim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.mim.model.Subject;


public class AddSubjects extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText subjectNameInp;
    Button addSubjectBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subjects_to_class);

        final String key=getIntent().getStringExtra("CLASS_ID");
        this.db=FirebaseDatabase.getInstance();
        this.ref=this.db.getReference("razredi/").child(key).child("predmeti");

        this.subjectNameInp = findViewById(R.id.subjectNameInp);
        this.addSubjectBtn = findViewById(R.id.AddSubjectBtn);

        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_nastavnika = " ";
                String newSubjectKey = ref.push().getKey();
                String subjectName = subjectNameInp.getText().toString();
                ref.child(newSubjectKey).setValue(new Subject(newSubjectKey, subjectName, id_nastavnika ));
                subjectNameInp.setText("");
                Toast.makeText(AddSubjects.this,
                        "Uspješno ste dodali predmet",Toast.LENGTH_LONG).show();

            }
        });

    }

}
