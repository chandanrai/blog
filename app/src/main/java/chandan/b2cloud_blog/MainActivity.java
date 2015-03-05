package chandan.b2cloud_blog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chandan.b2cloud_blog.adapter.CustomListAdapter;
import chandan.b2cloud_blog.webServices.model.BlogModel;
import chandan.b2cloud_blog.webServices.parser.BlogParser;


public class MainActivity extends Activity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=50&q=http://b2cloud.com.au/rss";
    private ProgressDialog pDialog;
    private List<BlogModel> blogList= new ArrayList<BlogModel>();
    private ListView listView;
    private CustomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        mAdapter = new CustomListAdapter(this, blogList);
        listView.setAdapter(mAdapter);

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
                hidePDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(requestBlog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
