package ba.sum.fpmoz.mim;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.mim.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.mim.ui.fragments.classes.AddClassFragment;
import ba.sum.fpmoz.mim.ui.fragments.classes.ListClassFragment;

public class TabbedClassesActivity extends AppCompatActivity {
TabLayout layout;
ViewPager pager;
TabbedAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_classes);
        setTitle("Administracija razreda");
        this.layout=findViewById(R.id.classesTabLayout);
        this.pager=findViewById(R.id.classesViewPager);
        this.adapter= new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListClassFragment(), "Svi razredi"
        );
        this.adapter.addFragment(
                new AddClassFragment(), "Dodaj razred"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }
}
