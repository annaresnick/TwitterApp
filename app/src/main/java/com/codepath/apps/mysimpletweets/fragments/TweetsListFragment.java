package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.Tweet;
import com.codepath.apps.mysimpletweets.TweetsArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class TweetsListFragment extends Fragment {

    public ArrayList<Tweet> tweets;
    public TweetsArrayAdapter aTweets;
    public ListView lvTweets;
    private SwipeRefreshLayout swipeContainer;


    // inflation logic
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        // Find the listview
        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        // Connect adapter to list view
        lvTweets.setAdapter(aTweets);
        return v;
    }

    // creation lifecycle event
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the arraylist (data source)
        tweets = new ArrayList<>();
        // Construct the adapter from data source
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }
        /*
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        @Override
        public void onRefresh() {
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            fetchTimelineAsync(0);
        }
    });
    // Configure the refreshing colors
    swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
    android.R.color.holo_green_light,
    android.R.color.holo_orange_light,
    android.R.color.holo_red_light);

}




    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        client.getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
                // Remember to CLEAR OUT old items before appending in the new ones
                adapter.clear();
                // ...the data has come back, add new items to your adapter...
                adapter.addAll(...);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }

*/
    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }
}
