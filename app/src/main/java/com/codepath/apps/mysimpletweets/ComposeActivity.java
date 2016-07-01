package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);


    }
    public void onClick(View view) {
        Intent i = new Intent(this, TimelineActivity.class);
        startActivity(i);
    }

    public void onSubmit(View v) {
        TwitterClient client = TwitterApplication.getRestClient();
        EditText etTweets = (EditText)findViewById(R.id.etTweet);
        String status = etTweets.getText().toString();
        client.composeTweet(status, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Tweet tweet = Tweet.fromJSON(response);
                Intent data = new Intent();
                data.putExtra("tweet", tweet);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }


}
