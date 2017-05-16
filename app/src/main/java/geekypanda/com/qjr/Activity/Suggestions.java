package geekypanda.com.qjr.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 3/29/2017.
 */
public class Suggestions extends FragmentActivity {


    EditText suggestionname,suggestionemail,comments;
    Button submit;
    ImageView back;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestionlayout);
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
       // .addTestDevice("2E0FEF051360B560DBE272FA97E84B44")
        mAdView.loadAd(adRequest);
        setVariable();

    }

    private void setVariable() {
        suggestionname = (EditText)findViewById(R.id.suggestionname);
        suggestionemail = (EditText)findViewById(R.id.suggestionemail);
        comments = (EditText)findViewById(R.id.suggestioncomment);
        submit = (Button)findViewById(R.id.submit_feedback);
        back = (ImageView)findViewById(R.id.topback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( suggestionname.getText().toString().equals("")){
                    Toast.makeText(Suggestions.this, "Please Enter your name", Toast.LENGTH_LONG).show();
                    suggestionname.setError("Please Enter your name");
                }else if(suggestionemail.getText().toString().equals("")){
                    Toast.makeText(Suggestions.this, "Please Enter your email Id", Toast.LENGTH_LONG).show();
                    suggestionemail.setError("Please Enter your email Id");
                }else if (comments.getText().toString().equals("")){
                    Toast.makeText(Suggestions.this, "Please enter your valuable comments", Toast.LENGTH_LONG).show();
                    comments.setError("Please enter your valuable comments");
                }else{
                    submitSuggestion(suggestionname.getText().toString(),suggestionemail.getText().toString(),comments.getText().toString());
                   /* new AlertDialog.Builder(Suggestions.this)
                            .setTitle("Suggestion Submitted Successfully")
                            .setMessage("Thanks for providing your valuable Suggestions")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    suggestionname.setText("");
                                    suggestionemail.setText("");
                                    comment.setText("");
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();*/
                }
            }
        });
    }

private void submitSuggestion(String name,String mail_id, String comment){

    Map<String, String> params = new HashMap<>();

    params.put("name", name);
    params.put("mail_id", mail_id);
    params.put("comment", comment);
    AIE_C_URL urlConstants = new AIE_C_URL();
    String url = urlConstants.getHostname() + "/api/v1/suggestion";


    new AIE_U_REST_API().post(url, params, new AIE_U_REST_API.OnRestCallback() {

        @Override
        public void onRestResponse(boolean success, JSONObject jsonObject) {

            if (success) {

                try {

                    if (jsonObject.has("data")) {

                        if (jsonObject.has("code")) {
                           if (jsonObject.getInt("code") == 0) {
                               new AlertDialog.Builder(Suggestions.this)
                                       .setTitle("Suggestion Submitted Successfully")
                                       .setMessage("Thanks for providing your valuable Suggestions")
                                       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                           public void onClick(DialogInterface dialog, int which) {
                                               suggestionname.setText("");
                                               suggestionemail.setText("");
                                               comments.setText("");
                                               dialog.dismiss();
                                               onBackPressed();
                                           }
                                       })
                                       .setIcon(R.mipmap.quotejr)
                                       .show();
                           }
                        }

//
                    }
                } catch (Exception e) {
                      Toast.makeText(Suggestions.this, "Some Data error occured", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            } else{
                Toast.makeText(Suggestions.this, "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
            }

        }
    }, Request.Priority.HIGH);

}
}
