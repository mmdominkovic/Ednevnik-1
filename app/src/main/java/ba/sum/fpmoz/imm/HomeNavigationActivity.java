package ba.sum.fpmoz.imm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeNavigationActivity extends AppCompatActivity {
    private CardView usersCardId;
    private CardView subjectCardId;
    private CardView classCardId;
    private Button logout;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        setTitle("Dobrodošli u administraciju" );

        this.logout=findViewById(R.id.logout);
        mAuth= FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });

        this.usersCardId =findViewById(R.id.usersCardId);
        this.usersCardId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TabbedUserAdminActivity.class);
                startActivity(i);
            }
        });

        this.subjectCardId=findViewById(R.id.subjectCardId);
        this.subjectCardId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabbedSubjectsActivity.class);
                startActivity(i);
            }
        });
        this.classCardId=findViewById(R.id.classCardId);
        this.classCardId.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), TabbedClassesActivity.class);
            startActivity(i);
        });

    }

    private void signOutUser() {
        Intent mainActivity=new Intent(HomeNavigationActivity.this,MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }
}