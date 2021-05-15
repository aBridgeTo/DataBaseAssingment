package com.example.databasesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.databasesimple.adapters.Message;
import com.example.databasesimple.adapters.myDBAdapter;

public class MainActivity extends AppCompatActivity {

    EditText fstname, lstname, phone;
    TextView data;
    myDBAdapter helper;
    Button button;
    String firstName, lastName, Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fstname = findViewById(R.id.firstName);
        lstname = findViewById(R.id.lastName);
        phone = findViewById(R.id.phone);
        data = findViewById(R.id.data);
        button = findViewById(R.id.btn);
        helper = new myDBAdapter(this);


        if (helper.getData() != null) {
            data.setText(helper.getData());

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndUpdate();

            }
        });


    }

    private void checkAndUpdate() {

        firstName = fstname.getText().toString();
        lastName = lstname.getText().toString();
        Phone = phone.getText().toString();


        if (firstName.isEmpty() || lastName.isEmpty()) {

            if (Phone.isEmpty()) {
                Message.message(MainActivity.this, "Enter the details First");
            } else {
                Message.message(MainActivity.this, "Enter Name and Last name");
            }

        } else if (Phone.isEmpty()) {
            Message.message(MainActivity.this, "Enter the Phone");
        } else {

            if (helper.getData() != null) {
                helper.delete();
                long id = helper.insertData(firstName, lastName, Phone);
                if (id <= 0) {
                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    fstname.setText("");
                    lstname.setText("");
                    phone.setText("");
                } else {
                    Message.message(getApplicationContext(), "Insertion Successful");
                    fstname.setText("");
                    lstname.setText("");
                    phone.setText("");
                    Log.i("response", helper.getData());
                    data.setText(helper.getData());
                }
            } else {
                long id = helper.insertData(firstName, lastName, Phone);
                if (id <= 0) {
                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    fstname.setText("");
                    lstname.setText("");
                    phone.setText("");
                } else {
                    Message.message(getApplicationContext(), "Insertion Successful");
                    fstname.setText("");
                    lstname.setText("");
                    phone.setText("");
                    Log.i("response", helper.getData());
                    data.setText(helper.getData());
                }
            }

        }




    }


}