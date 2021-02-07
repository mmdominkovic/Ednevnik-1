package ba.sum.fpmoz.imm;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.imm.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.imm.ui.fragments.subjects.AddSubjectFragment;
import ba.sum.fpmoz.imm.ui.fragments.subjects.ListSubjectFragment;

public class TabbedSubjectsActivity extends AppCompatActivity {
    TabLayout layout;
    ViewPager pager;
    TabbedAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_subjects);
        setTitle("");
        this.layout=findViewById(R.id.subjectsTabLayout);
        this.pager=findViewById(R.id.subjectsViewPager);
        this.adapter= new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListSubjectFragment(), "Svi predmeti"
        );
        this.adapter.addFragment(
                new AddSubjectFragment(), "➕"
        );
        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }
}
