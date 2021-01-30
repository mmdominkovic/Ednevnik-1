package ba.sum.fpmoz.mim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.mim.ui.fragments.users.AddUsersFragment;
import ba.sum.fpmoz.mim.ui.fragments.users.ListStudentsForProfesors;
import ba.sum.fpmoz.mim.ui.fragments.users.ListTeachersFragment;
import ba.sum.fpmoz.mim.ui.fragments.users.ListUsersFragment;

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
                new ListStudentsForProfesors(), "Učenici"
        );

        this.adapter.addFragment(
                new ListStudentsForProfesors(), "Moji učenici"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);

    }
}