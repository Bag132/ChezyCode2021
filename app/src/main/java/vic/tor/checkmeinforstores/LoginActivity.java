package vic.tor.checkmeinforstores;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

//TODO: Make buttons change drawables on touch
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailField = findViewById(R.id.email_input_field),
                passwordField = findViewById(R.id.password_input_field);
        final TextView invalidLoginText = findViewById(R.id.text_invalid_login);
        ConstraintLayout loginButton = findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                boolean hurry = true;

                if (!hurry) {
                    email = emailField.getText().toString().trim();
                    password = passwordField.getText().toString().trim();
                } else {
                    email = "victortaco132@gmail.com";
                    password = "pword1";
                }

                Log.d("Login", email);

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Login", "signInWithEmail:success");
                                    startActivity(new Intent(LoginActivity.this, StoreSetupActivity.class));
                                } else {
                                    Exception e = task.getException();
                                    Log.w("Login", "signInWithEmail:failure", e);
                                    if (!(e instanceof FirebaseAuthInvalidUserException)) {
                                        invalidLoginText.setText("Error logging in");
                                    } else {
                                        invalidLoginText.setText("Invalid email or password");
                                    }
                                    invalidLoginText.setVisibility(View.VISIBLE);
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, EntryActivity.class));
    }
}
