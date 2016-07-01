package com.codepath.apps.mysimpletweets;

import com.codepath.apps.mysimpletweets.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by aresnick on 6/27/2016.
 */

// Parse the JSON + Store the data, encapsulate state logic or display logic
public class Tweet implements Serializable {
    // list out the attributes
    private String body;
    private long uid; // unique id for the tweet
    private User user; // store embedded user object
    private String createAt;

    public String getRelativeDate() {
        return relativeDate;
    }

    private String relativeDate;

    public User getUser() {
        return user;
    }

    public long getUid() {
        return uid;
    }

    public String getBody() {
        return body;
    }

    public String getCreateAt() {
        return createAt;
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);
        Calendar c = Calendar.getInstance();

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            //Calendar calendar = Calendar.getInstance();
            //Calendar now = Calendar.getInstance();
            //relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
            //        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            long currentDate = System.currentTimeMillis();
            long relDate = (currentDate - dateMillis)/1000;
            if (relDate < 60) {
                relativeDate = relDate + " s";
            } else if(relDate < 3600)
            {
                relativeDate = relDate/60 + " m";
            } else if(relDate/60 < 3600) {
                relativeDate = relDate/3600 + " h";
            }else if(relDate/3600 < 7){
                relativeDate = relDate/(3600*24) + " d";
            } else {
                relativeDate = rawJsonDate.substring(4, 10);
            }
            //String currentDate = sf.format(c.getTime());
            String[] separated = relativeDate.split(" ");
            String time = separated[0];

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
    // Deserialize the JSON and build Tweet objects
    // Tweet.fromJSON("{...}") => <Tweet>
    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        // Extract the values from the json, store them
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createAt = jsonObject.getString("created_at");
            tweet.relativeDate = tweet.getRelativeTimeAgo(jsonObject.getString("created_at"));
            //Toast.makeText(, jsonObject.getString("created_at"), Toast.LENGTH_LONG).show();
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return the tweet object
        return tweet;
    }


    // Tweet.fromJSONArray([ {...}, {...}] => List<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        // Iterate the json array and create tweets
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        //Return the finished list
        return tweets;
    }
}
