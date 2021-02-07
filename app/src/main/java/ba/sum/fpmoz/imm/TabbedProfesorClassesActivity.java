package ba.sum.fpmoz.imm;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.imm.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListClassesForProfesors;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListRazredForProfesors;


public class TabbedProfesorClassesActivity extends AppCompatActivity {
    TabLayout layout;
    ViewPager pager;
    TabbedAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_profesor_classes);
        setTitle("");
        this.layout=findViewById(R.id.classesTabLayout);
        this.pager=findViewById(R.id.classesViewPager);
        this.adapter= new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListClassesForProfesors(), "Moji predmeti"
        );
        this.adapter.addFragment(
                new ListRazredForProfesors(), "Moji razredi"
        );
        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }
}
