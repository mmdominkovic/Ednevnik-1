package ba.sum.fpmoz.imm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeNavigationStudentActivity extends AppCompatActivity {

    private Button logout1;
    FirebaseAuth mAuth;
    private Button chngpswd;
    private CardView usersCardId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);
        setTitle("Dobrodošli studentu");

        this.chngpswd=findViewById(R.id.chngpswd);
        this.logout1=findViewById(R.id.logout1);
        mAuth=FirebaseAuth.getInstance();

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });
        this.usersCardId = findViewById(R.id.usersCardId);
        this.usersCardId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TabbedStudentActivity.class);
                startActivity(i);
            }
        });
        this.chngpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ChangePassword.class);
                startActivity(i);
            }
        });
    }
    private void signOutUser() {
        Intent mainActivity=new Intent(HomeNavigationStudentActivity.this,MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }
}