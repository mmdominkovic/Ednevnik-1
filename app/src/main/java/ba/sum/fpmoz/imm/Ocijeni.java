package ba.sum.fpmoz.imm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.imm.model.Grade;

public class Ocijeni extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;
    EditText ocjena, opis, cjelina;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocijeni);

        this.ocjena= findViewById(R.id.ocjenaTxt);
        this.opis=findViewById(R.id.opisTxt);
        this.cjelina = findViewById(R.id.lekcijaTxt);
        this.addBtn = findViewById(R.id.addBtn);

        final String key=getIntent().getStringExtra("SUBJECT_ID");
        final String keyy=getIntent().getStringExtra("STUDENT_ID");
        this.db=FirebaseDatabase.getInstance();
        this.ref=this.db.getReference("učenici/").child(keyy).child("predmeti").child(key).child("ocjene");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newKey = ref.push().getKey();
                String grade = ocjena.getText().toString();
                String desc = opis.getText().toString();
                String lecture = cjelina.getText().toString();

                ocjena.setText("");
                opis.setText("");
                cjelina.setText("");
                Toast.makeText(Ocijeni.this,
                        "Uspješno ste dodali ocjenu",Toast.LENGTH_LONG).show();
                ref.child(newKey).setValue(new Grade(newKey, grade, desc, lecture));
            }
        });
    }
}