package ba.sum.fpmoz.imm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.imm.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListClassesForProfesors;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListRazredForProfesors;

public class TabbedProfesorUsersActivity extends AppCompatActivity {

    TabbedAdapter adapter;
    TabLayout layout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_user_admin);
        this.layout = findViewById(R.id.tabLayout);
        this.pager = findViewById(R.id.viewPager);

        this.adapter = new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListRazredForProfesors(), "Moji razredi"
        );
        this.adapter.addFragment(
                new ListClassesForProfesors(), "Moji predmeti"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);

    }
}