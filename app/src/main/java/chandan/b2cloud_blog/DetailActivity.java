package chandan.b2cloud_blog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


/*
 *  Class handles the detail view of a list view
 */
public class DetailActivity extends Activity {

    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private String mContent = null;
    private String mTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = this.getIntent();
        if(intent!= null && intent.hasExtra(TITLE) && intent.hasExtra(CONTENT)) {
            mTitle = intent.getStringExtra(TITLE);
            mContent = intent.getStringExtra(CONTENT);
            ((TextView)findViewById(R.id.detail)).setText(Html.fromHtml(mContent));
            ((TextView)findViewById(R.id.title)).setText(Html.fromHtml(mTitle));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
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
}
