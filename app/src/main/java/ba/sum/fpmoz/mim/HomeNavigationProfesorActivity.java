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
        setContentView(R.layout.activity_home_navigation);
        setTitle("Dobrodošli, profesore.");
    }
}