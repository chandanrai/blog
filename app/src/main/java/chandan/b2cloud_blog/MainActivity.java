package chandan.b2cloud_blog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
public class MainActivity extends Activity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=50&q=http://b2cloud.com.au/rss";
    private ProgressDialog pDialog;
    private List<BlogModel> blogList= new ArrayList<BlogModel>();
    private ListView listView;
    private View mHeader;
    private BlogListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RequestQueue mRequestQueue = Networking.getInstance(this).getRequestQueue();
        listView = (ListView) findViewById(R.id.list);
        mAdapter = new BlogListAdapter(this, blogList);
        mHeader = getLayoutInflater().inflate(R.layout.image, null);
        listView.addHeaderView(mHeader);
        listView.setAdapter(mAdapter);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);

        JsonObjectRequest requestBlog = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        BlogParser blogParser = new BlogParser();
                        blogParser.parseResponse(response, blogList);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        if(firstVisibleItem == 0){
            View firstView  = view.getChildAt(0);
            if(firstView != null){
                mHeader.findViewById(R.id.image).setTranslationY(-firstView.getTop()/2);
            }

        }
    }

    /*
     *  Display the detail view of an item of the list view
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        BlogModel blog = (BlogModel) listView.getItemAtPosition(position);
        Intent detailIntent = new Intent(this,DetailActivity.class).putExtra(Intent.EXTRA_TEXT, blog.getContent());
        startActivity(detailIntent);
    }
}
