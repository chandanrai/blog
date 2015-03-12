package chandan.b2cloud_blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;


/*
 *  Class handles the detail view of a list view
 */
public class DetailActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private String mContent = null;
    private String mTitle = null;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(this);
//        mToolbar.getBackground().setAlpha(0);

        Intent intent = this.getIntent();
        if(intent!= null && intent.hasExtra(TITLE) && intent.hasExtra(CONTENT)) {
            mTitle = intent.getStringExtra(TITLE);
            mContent = intent.getStringExtra(CONTENT);
            ((TextView)findViewById(R.id.detail)).setText(mContent);
            ((TextView)findViewById(R.id.title)).setText(Html.fromHtml(mTitle));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        super.onBackPressed();
    }
}
