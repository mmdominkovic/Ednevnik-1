package ba.sum.fpmoz.mim;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.mim.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.mim.ui.fragments.classes.AddClassFragment;
import ba.sum.fpmoz.mim.ui.fragments.classes.ListClassFragment;
import ba.sum.fpmoz.mim.ui.fragments.subjects.AddSubjectFragment;
import ba.sum.fpmoz.mim.ui.fragments.subjects.ListSubjectFragment;

public class TabbedSubjectAdminActivity extends AppCompatActivity {
    TabbedAdapter adapter;
    TabLayout layout;
    ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_subjects);
        setTitle("Administracija Predmeta");

        this.layout=findViewById(R.id.subjectsTabLayout);
        this.pager=findViewById(R.id.subjectsViewPager);
        this.adapter= new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListSubjectFragment(), "Prikaz Predmeta"
        );
        this.adapter.addFragment(
                new AddSubjectFragment(), "Dodavanje predmeta"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }
}
