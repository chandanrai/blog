package chandan.b2cloud_blog.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import chandan.b2cloud_blog.R;
import chandan.b2cloud_blog.webServices.model.BlogModel;

/*
 * Custom adapter view for inflating the list view
 */
public class BlogListAdapter extends BaseAdapter{

    private Activity mActivity;
    private LayoutInflater mInflater;
    private List<BlogModel> mBlogs;
    private static String AUTHOR = "By";
    private static String COLON = ": ";

    public BlogListAdapter(Activity mActivity, List<BlogModel> mBlogs) {
        this.mActivity = mActivity;
        this.mBlogs = mBlogs;
    }

    @Override
    public int getCount() {
        return mBlogs.size();
    }

    @Override
    public BlogModel getItem(int location) {
        return mBlogs.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * @return Returns the single list view
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (mInflater == null)
            mInflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = mInflater.inflate(R.layout.card_view, null);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView author = (TextView) view.findViewById(R.id.author);
        TextView publishedDate = (TextView) view.findViewById(R.id.publishedDate);
        TextView content = (TextView) view.findViewById(R.id.content);

        BlogModel blog = mBlogs.get(position);
        title.setText(blog.getTitle());
        author.setText(AUTHOR+COLON+String.valueOf(blog.getAuthor()));
        publishedDate.setText(String.valueOf(blog.getPublishedDate()));
        content.setText(Html.fromHtml(String.valueOf(blog.getContent())));
        return view;
    }
}
