package aptitude.sasurie.leisurebreaker;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class RegisterActivity extends AppCompatActivity {

    private Button Register;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputReg_no;
    private EditText inputph_no;
    private EditText deptmt;
    private EditText years;

    private ProgressDialog pDialog;

    String name;
    String reg_no;
    String ph_no;
    String email;
    String password;
    String dept;
    String year;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputReg_no = (EditText) findViewById(R.id.reg_no);
        inputph_no = (EditText) findViewById(R.id.ph_no);
        Register = (Button) findViewById(R.id.register);
        deptmt=findViewById(R.id.editdept);
        years =findViewById(R.id.editText);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                name     = inputFullName.getText().toString().trim();
                reg_no   = inputReg_no.getText().toString().trim();
                ph_no    = inputph_no.getText().toString().trim();
                dept     = deptmt.getText().toString().trim();
                year     = years.getText().toString().trim();
                email    = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !dept.isEmpty()&& !year.isEmpty() && !reg_no.isEmpty() && !ph_no.isEmpty()) {
                    registerUser();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }


    private void registerUser() {
        class AddUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_SHORT).show();
                Toast.makeText(RegisterActivity.this,"Your Details are Submited Plrase Wait for authenticatikn Then you get Loged in",Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_REGNO,reg_no);
                params.put(Config.KEY_NAME,name);
                params.put(Config.KEY_PHNO,ph_no);
                params.put(Config.KEY_EMAIL,email);
                params.put(Config.KEY_PASSWORD,password);
                params.put(Config.KEY_DEPT,dept);
                params.put(Config.KEY_YEAR,year);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_REGISTER, params);
                return res;
            }
        }

        AddUser ae = new AddUser();
        ae.execute();
    }
}