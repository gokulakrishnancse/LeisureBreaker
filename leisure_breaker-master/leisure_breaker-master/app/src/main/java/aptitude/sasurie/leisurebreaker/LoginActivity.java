package aptitude.sasurie.leisurebreaker;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    private EditText reg_no, password;
    private Button login;
    String reg_id, pass;
    Button signup;
    SharedPreferences  sharedpreferences;


    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        reg_no = findViewById(R.id.reg_no);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedpreferences.contains(Config.KEY_REGNO) && sharedpreferences.contains(Config.KEY_PASSWORD)){
            String channel = (sharedpreferences.getString(Config.KEY_REGNO, ""));

            Intent intent = new Intent(LoginActivity.this,TestViewActivity.class);
            intent.putExtra(Config.KEY_REGNO,channel);
            startActivity(intent);
            finish();   //finish current activity
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg_id = reg_no.getText().toString().trim();
                pass = password.getText().toString().trim();
                validate();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void auth() {
        class Validate extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, "Logging...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();


                if("Data".equals(s.toString().trim())){
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Config.KEY_REGNO,reg_id);
                    editor.putString(Config.KEY_PASSWORD,pass);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this,TestViewActivity.class);
                    intent.putExtra(Config.KEY_REGNO,reg_id);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_REGNO, reg_id);
                params.put(Config.KEY_PASSWORD, pass);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_LOGIN, params);
                return res;
            }
        }

        Validate ae = new Validate();
        ae.execute();
    }
    public  void validate(){
        if(reg_id.equals("") && pass.equals(""))
        {
            Toast.makeText(LoginActivity.this,"You Entered Empty Fields",Toast.LENGTH_LONG).show();
            reg_no.requestFocus();
        }
        else if(reg_id.equals(""))
        {
            Toast.makeText(LoginActivity.this,"Enter The Register Number",Toast.LENGTH_LONG).show();
            password.setText("");
            reg_no.requestFocus();
        }
        else if(pass.equals(""))
        {
            Toast.makeText(LoginActivity.this,"Enter The Password",Toast.LENGTH_LONG).show();
            password.requestFocus();
        }
        else
        {
            auth();
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to quit the app")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        finishAffinity();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }


    @Override
    public void onBackPressed() {

    }

}