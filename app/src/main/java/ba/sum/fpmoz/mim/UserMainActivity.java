package ba.sum.fpmoz.mim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.mim.model.User;
import ba.sum.fpmoz.mim.model.Teacher;

public class UserMainActivity extends AppCompatActivity {
    private TextView loggedUserTextView;
    private TextView loggedUserRoleTextView;
    private User loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        this.loggedUserRoleTextView = findViewById(R.id.loggedUserRoleTxt);

        this.loggedUserTextView = findViewById(R.id.loggedUserTxt);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("/edenvnik/korisnici").getRef().child(user.getUid());

        String mail=user.getEmail();
        String[] parts=mail.split("@");
        String part1=parts[1];
        String part2=parts[0];
        if(user.getDisplayName()!=null){
            loggedUserTextView.setText("Dobrodošli: " + user.getDisplayName());
        }else{
            loggedUserTextView.setText("Dobrodošli: " + part2);
        }
        
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                loggedUser = snapshot.getValue(User.class);
                if(part1.equals("fpmoz.sum.ba")){
                    loggedUserRoleTextView.setText("Prijavljeni ste kao profesor" );}
                else{
                    loggedUserRoleTextView.setText("prijavljeni ste kao učenik");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}

            });

        }
}