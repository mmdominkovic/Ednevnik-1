package ba.sum.fpmoz.imm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.imm.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.imm.ui.fragments.classes.ListOcjeneProfesor;


public class TabbedOcjeneProfesor extends AppCompatActivity {

    TabLayout layout;
    ViewPager pager;
    TabbedAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_ocjene_profesor);
        setTitle("");
        this.layout=findViewById(R.id.classesTabLayout);
        this.pager=findViewById(R.id.classesViewPager);
        this.adapter= new TabbedAdapter(getSupportFragmentManager(), 1);

        this.adapter.addFragment(
                new ListOcjeneProfesor(), "Ocjene"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }}