package ba.sum.fpmoz.imm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref, nas;
    EditText subjectNameEdt,subjectName1Edt;
    Button subjectAddBtn;
    FirebaseAuth mAuth;
    String email;
    String TAG=ChangePassword.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        this.subjectNameEdt = findViewById(R.id.subjectNameEdt);
        this.subjectName1Edt = findViewById(R.id.subjectName1Edt);
        this.subjectAddBtn = findViewById(R.id.subjectAddBtn);

        this.db = FirebaseDatabase.getInstance();
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        email=mAuth.getCurrentUser().getEmail();


        this.subjectAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = subjectNameEdt.getText().toString();
                String name1=subjectName1Edt.getText().toString();
                AuthCredential credential= EmailAuthProvider.getCredential(email,name);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            user.updatePassword(name1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d(TAG,"Password updated");
                                    }else{
                                        Log.d(TAG,"Error password not updated");
                                    }
                                }
                            });
                        }else{
                            Log.d(TAG,"Error auth failed");

                        }
                    }
                });
            }
        });
    }
}