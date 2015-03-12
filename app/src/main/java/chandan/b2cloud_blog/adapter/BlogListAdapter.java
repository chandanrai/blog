package chandan.b2cloud_blog.adapter;

import android.content.Context;
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
public class BlogListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<BlogModel> mBlogList;
    private static String AUTHOR = "By";
    private static String COLON = ": ";

    public BlogListAdapter(Context context, List<BlogModel> mBlogs) {
        this.mContext = context;
        this.mBlogList = mBlogs;
    }

    @Override
    public int getCount() {
        return mBlogList.size();
    }

    @Override
    public BlogModel getItem(int location) {
        return mBlogList.get(location);
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

        if (view == null) {
            mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.card_view, parent, false);
        }

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView author = (TextView) view.findViewById(R.id.author);
        TextView publishedDate = (TextView) view.findViewById(R.id.publishedDate);
        TextView content = (TextView) view.findViewById(R.id.content);

        BlogModel blog = mBlogList.get(position);
        title.setText(blog.getTitle());
        author.setText(AUTHOR + COLON + blog.getAuthor());
        publishedDate.setText(getDateComponent(blog.getPublishedDate()));
        content.setText(blog.getContent());

        return view;
    }

    private String getDateComponent(String date) {
        return date.substring(5, 16);
    }
}
