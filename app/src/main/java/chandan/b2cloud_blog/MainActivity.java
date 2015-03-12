package chandan.b2cloud_blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chandan.b2cloud_blog.adapter.BlogListAdapter;
import chandan.b2cloud_blog.webServices.model.BlogModel;
import chandan.b2cloud_blog.webServices.parser.BlogParser;

/*
 *  Class handles the main activity pf the application
 */
public class MainActivity extends ActionBarActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String URL = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=50&q=http://b2cloud.com.au/rss";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private List<BlogModel> mBlogList = new ArrayList<BlogModel>();
    private ListView mListView;
    private View mHeader;
    private BlogListAdapter mAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RequestQueue mRequestQueue = Networking.getInstance(this).getRequestQueue();
        mListView = (ListView) findViewById(R.id.list);
        mAdapter = new BlogListAdapter(this, mBlogList);
        mHeader = getLayoutInflater().inflate(R.layout.image, null);
        mListView.addHeaderView(mHeader, null, false);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.getBackground().setAlpha(0);


        JsonObjectRequest requestBlog = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        BlogParser parser = new BlogParser();
                        parser.parseResponse(response, mBlogList);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        mRequestQueue.add(requestBlog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    /*
     *  Function get called on the the scroll event of the list view and
     *  based on the scroll sets translation of the image header
     */
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == 0) {
            View firstView = view.getChildAt(0);
            if (firstView != null) {
                mHeader.findViewById(R.id.image).setTranslationY(-firstView.getTop() / 2);

                float opacityHeight = firstView.getHeight() - mToolbar.getHeight();

                //Delta of hero image off screen
                float scrollDelta = opacityHeight - (firstView.getTop() + opacityHeight);
                float normalizationFraction = 255f / opacityHeight;
                int opacity = Math.round(Math.min(scrollDelta * normalizationFraction, 255));

                mToolbar.getBackground().setAlpha(opacity);
            }
        }
    }

    /*
     *  Display the detail view of an item of the list view
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        BlogModel blog = (BlogModel) mListView.getItemAtPosition(position);
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(TITLE, blog.getTitle());
        detailIntent.putExtra(CONTENT, blog.getContent().toString());
        startActivity(detailIntent);

    }
}
