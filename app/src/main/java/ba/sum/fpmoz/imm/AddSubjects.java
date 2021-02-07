package ba.sum.fpmoz.imm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ba.sum.fpmoz.imm.model.Class;
import ba.sum.fpmoz.imm.model.Subject;


public class AddSubjects extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref, nas;
    EditText subjectNameInp;
    Button addSubjectBtn;
    Spinner spinnerNasPred;
    TextView spinnerTxt;
    List<String> predmetno;
    String selectedItem;
    HashMap<String,String> predmetiUid=new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subjects_to_class);

        final String key=getIntent().getStringExtra("CLASS_ID");
        final String razname = getIntent().getStringExtra("CLASS_NAME");
        this.db=FirebaseDatabase.getInstance();
        this.ref=this.db.getReference("razredi/").child(key).child("predmeti");
        this.nas=this.db.getReference("nastavnici");
        this.spinnerTxt=findViewById(R.id.spinnerTxt);
        predmetno=new ArrayList<>();

        this.subjectNameInp = findViewById(R.id.subjectNameInp);
        this.addSubjectBtn = findViewById(R.id.AddSubjectBtn);

        nas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String>predmeti=new ArrayList<String>();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String prvi=ds.child("name").getValue().toString();
                    String drugi=ds.child("uid").getValue().toString();
                    predmetno.add(prvi);
                    predmeti.add(prvi);
                    predmetiUid.put(prvi,drugi);
                }
                spinnerNasPred=findViewById(R.id.spinnerNasPred);
                final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(AddSubjects.this, android.R.layout.simple_spinner_dropdown_item,predmeti);
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

        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSubjectKey = ref.push().getKey();
                String subjectName = subjectNameInp.getText().toString();
                String uidpred=predmetiUid.get(selectedItem);

                subjectNameInp.setText("");
                Toast.makeText(AddSubjects.this,
                        "Uspješno ste dodali predmet",Toast.LENGTH_LONG).show();
                nas.child(uidpred).child("predmeti").child(newSubjectKey).setValue(new Subject(newSubjectKey, subjectName, uidpred, razname));
                nas.child(uidpred).child("moji_razredi").child(key).setValue(new Class(key, razname));
                ref.child(newSubjectKey).setValue(new Subject(newSubjectKey, subjectName, uidpred, razname));
            }
        });

    }

}
