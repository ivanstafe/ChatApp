package ies.tsds.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    EditText t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.login);
        b2=findViewById(R.id.signup);
        t1=findViewById(R.id.ed);
        t2=findViewById(R.id.ed2);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signInWithEmailAndPassword(t1.getText().toString(), t2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Bienvenido nuevamente", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(MainActivity.this,msg.class);
                            startActivity(a);
                            finish();

                        }else{

                            Toast.makeText(MainActivity.this, "Error al ingresar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task<AuthResult> usuario_se_creo_exitosamente = auth.createUserWithEmailAndPassword(t1.getText().toString(), t2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(MainActivity.this, msg.class);
                            startActivity(a);
                            finish();
                        } else {

                            Toast.makeText(MainActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}