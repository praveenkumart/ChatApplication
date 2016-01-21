package com.emegra.mera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

/**
 * Created by Praveen on 7/19/2015.
 */
public class Login extends ActionBarActivity {
static  String name,num;
    TextView lblMessage;
    Controller aController;
    SharedPreferences sp;
    // Asyntask
    AsyncTask<Void, Void, Void> mRegisterTask;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sp = getSharedPreferences("mypref", Context.MODE_PRIVATE);
if(!sp.getString("name","no").equals("no"))
{
    startActivity(new Intent(getApplicationContext(),UserListView.class));
    finish();

}
            Button btn_save=(Button)findViewById(R.id.btn_save);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText edt_name = (EditText) findViewById(R.id.edt_name);
                    EditText edt_num = (EditText) findViewById(R.id.edt_num);

                    name = edt_name.getText().toString();
                    num = edt_num.getText().toString();

                    startActivity(new Intent(getApplicationContext(),Register.class));
                    finish();
                }
            });


    }


}
