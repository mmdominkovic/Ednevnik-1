package ba.sum.fpmoz.imm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.imm.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListStudentsInClass;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListSubjectsInClass;

public class TabbedClassesInfo extends AppCompatActivity {
    TabLayout layout;
    ViewPager pager;
    TabbedAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_classes_info);
        setTitle("");
        this.layout=findViewById(R.id.classesInfoTabLayout);
        this.pager=findViewById(R.id.classesInfoViewPager);
        this.adapter= new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListSubjectsInClass(), "Predmeti"
        );
        this.adapter.addFragment(
                new ListStudentsInClass(), "Učenici"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }
}
