package ba.sum.fpmoz.mim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class HomeNavigationProfesorActivity extends AppCompatActivity {
    private CardView usersCardId;
    private CardView subjectCardId;
    private CardView classCardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profesor);
        setTitle("Dobrodošli profesore" );

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
    }
}