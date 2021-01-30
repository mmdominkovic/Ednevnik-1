package ba.sum.fpmoz.mim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class HomeNavigationActivity extends AppCompatActivity {
    private CardView usersCardId;
    private CardView subjectCardId;
    private CardView classCardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        setTitle("Dobrodošli u administraciju" );

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
}