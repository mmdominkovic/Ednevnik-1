package ba.sum.fpmoz.imm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeNavigationProfesorActivity extends AppCompatActivity {
    private CardView usersCardId;
    private CardView subjectCardId;
    private CardView classCardId;
    private Button logout1;
    FirebaseAuth mAuth;
    private Button chngpswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profesor);
        setTitle("Dobrodošli profesore");

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
                Intent i = new Intent(getApplicationContext(),TabbedProfesorUsersActivity.class);
                startActivity(i);
            }
        });
        this.classCardId=findViewById(R.id.classCardId);
        this.classCardId.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), TabbedProfesorClassesActivity.class);
            startActivity(i);
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
        Intent mainActivity=new Intent(HomeNavigationProfesorActivity.this,MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }
}
