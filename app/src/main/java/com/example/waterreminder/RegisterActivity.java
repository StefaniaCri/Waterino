package com.example.waterreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waterreminder.db_tables.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup,signin;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);


        db = new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass =  repassword.getText().toString();

                if(user.equals("") || pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass))
                    {
                        Boolean checkuser = db.checkUsername(user);
                        if(checkuser == false)
                        {
                            Boolean insert = db.insertData(user,pass);
                            if(insert == true)
                            {
                                Toast.makeText(RegisterActivity.this,"Register success",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this,"Register failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this,"User already exist! Please sign in",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"Password not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }


}