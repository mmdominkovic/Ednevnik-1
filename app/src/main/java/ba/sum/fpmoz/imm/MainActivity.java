package ba.sum.fpmoz.imm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private String loggedRole;
    private FirebaseAuth mAuth;
    private TextView messageTxt;
    private EditText emailInp;
    private EditText passwordInp;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        this.messageTxt = findViewById(R.id.messageTxt);
        this.emailInp = findViewById(R.id.emailInp);
        this.passwordInp = findViewById(R.id.passwordInp);
        this.loginBtn = findViewById(R.id.loginBtn);

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailInp.getText().toString();
                String password = passwordInp.getText().toString();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            messageTxt.setText("Uspješno ste se prijavili na sustav!");
                            login();
                        }
                        else {
                            System.out.println(task.getException());
                            messageTxt.setText("Nastao je problem sa prijavom");
                        }
                    }
                });

            }
        });
    }

    private void login() {
        messageTxt.setText("Uspješno ste se prijavili na sustav.");
        String userID = mAuth.getCurrentUser().getUid();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("korisnici/"+ userID);

        Intent P = new Intent(getApplicationContext(),HomeNavigationProfesorActivity.class);
        Intent A = new Intent(getApplicationContext(), HomeNavigationActivity.class);
        Intent S  = new Intent(getApplicationContext(), TabbedStudentActivity.class);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("role: ", snapshot.child("role").getValue().toString());
                if(snapshot.child("role").getValue().toString().equals("admin")){
                    startActivity(A);
                } else if (snapshot.child("role").getValue().toString().equals("nastavnik")){
                    startActivity(P);
                }
                else {
                    startActivity(S);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void errorMsg() {
        messageTxt.setText("Nastao je problem s prijavom.");
    }
}