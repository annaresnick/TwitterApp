package com.codepath.apps.mysimpletweets.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable{
    // list attributes
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String tagline;
    private int followersCount;
    private int followingCount;
    private String mediaUrl;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getName() {

        return name;
    }

    public String getProfileImageUrl() {

        return profileImageUrl;
    }

    public String getScreenName() {

        return screenName;
    }

    public long getUid() {

        return uid;
    }

    public String getTagline() {

        return tagline;
    }

    public int getFollowersCount() {

        return followersCount;
    }

    public int getFriendsCount() {

        return followingCount;
    }

    // deserialize the user json => User
    public static User fromJSON(JSONObject json){
        User u = new User();
        // Extract and fill the values
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.tagline = json.getString("description");
            u.followersCount = json.getInt("followers_count");
            u.followingCount = json.getInt("friends_count");
            u.mediaUrl = json.getString("media_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return a user
        return u;
    }
}
