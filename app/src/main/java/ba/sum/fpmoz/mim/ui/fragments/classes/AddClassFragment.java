package ba.sum.fpmoz.mim.ui.fragments.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.mim.R;
import ba.sum.fpmoz.mim.model.Class;


public class AddClassFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText levelClassInp;
    EditText classNameInp;
    EditText classTeacherInp;
    EditText subjectInp;
    Button addClassBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
        final View classAdminView = inflater.inflate(R.layout.activity_class_admin,container,false);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("razredi");

        this.classNameInp = classAdminView.findViewById(R.id.classNameInp);
        this.classTeacherInp = classAdminView.findViewById(R.id.classTeacherInp);
        this.subjectInp=classAdminView.findViewById(R.id.subjectInp);
        this.addClassBtn = classAdminView.findViewById(R.id.AddClassBtn);

        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newClassKey = ref.push().getKey();
                String className = classNameInp.getText().toString();
                String classTeacher = classTeacherInp.getText().toString();
               String subject = subjectInp.getText().toString();
                ref.child(newClassKey).setValue(new Class(newClassKey, className, subject, classTeacher));
                levelClassInp.setText("");
                classNameInp.setText("");
                classTeacherInp.setText("");
                subjectInp.setText("");


                Toast.makeText(classAdminView.getContext(),
                        "Uspješno ste dodali razred",Toast.LENGTH_LONG).show();

            }
        });
        return classAdminView;

    }

}
