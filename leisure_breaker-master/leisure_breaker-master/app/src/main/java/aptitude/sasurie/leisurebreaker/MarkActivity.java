package aptitude.sasurie.leisurebreaker;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.HashMap;

import static aptitude.sasurie.leisurebreaker.LoginActivity.MyPREFERENCES;

public class MarkActivity extends AppCompatActivity implements RewardedVideoAdListener {
    String testid,regno,mark;
    private TextView textView,textView9;
    SharedPreferences sharedpreferences;
    private AdView mAdView;
    private RewardedVideoAd mRewardedVideoAd;
    AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        Intent intent = getIntent();
        MobileAds.initialize(this,
                "ca-app-pub-2052757055681240~3637998856");
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().addTestDevice("F304B865CE4B7E4100378ED91BA6FB17").build();
        mAdView.loadAd(adRequest);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedpreferences.contains(Config.KEY_REGNO) && sharedpreferences.contains(Config.KEY_PASSWORD)){

        }else {
            if(sharedpreferences.contains(Config.KEY_REGNO) && sharedpreferences.contains(Config.KEY_PASSWORD)){

                Intent loginIntent = new Intent(MarkActivity.this,LoginActivity.class);
                startActivity(loginIntent);

                finish();   //finish current activity
            }

        }


        testid = intent.getStringExtra(Config.TEST_ID);
        regno=intent.getStringExtra(Config.KEY_REGNO);
        mark=intent.getStringExtra(Config.TEST_MARK);

        textView=findViewById(R.id.textmark);
        textView.setText(mark);
        textView9=findViewById(R.id.textView9);
        registerMark();
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MarkActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
    private void registerMark() {
        class AddUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MarkActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MarkActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_REGNO,regno);
                params.put(Config.KEY_TESTID,testid);
                params.put(Config.TEST_MARK,mark);
                //params.put(Config.KEY_EMP_PHNO,phno);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_RESULT, params);
                return res;
            }
        }

        AddUser ae = new AddUser();
        ae.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.e("onreward Video loaded","true");
        mRewardedVideoAd.loadAd("ca-app-pub-2052757055681240/2245303748", new AdRequest.Builder().addTestDevice("F304B865CE4B7E4100378ED91BA6FB17").build());
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }


    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.e("ad failed","--status"+i);
    }

    @Override
    public void onRewardedVideoCompleted() {

    }


}
