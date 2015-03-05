package chandan.b2cloud_blog.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import chandan.b2cloud_blog.R;
import chandan.b2cloud_blog.webServices.model.BlogModel;


public class CustomListAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<BlogModel> blogs;
    private static String AUTHOR = "Author";
    private static String COLON = ": ";

    public CustomListAdapter(Activity activity, List<BlogModel> blogs) {
        this.activity = activity;
        this.blogs = blogs;
    }

    @Override
    public int getCount() {
        return blogs.size();
    }

    @Override
    public Object getItem(int location) {
        return blogs.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.card_view, null);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView publishedDate = (TextView) convertView.findViewById(R.id.publishedDate);

        BlogModel blog = blogs.get(position);
        title.setText(blog.getTitle());
        author.setText(AUTHOR+COLON+String.valueOf(blog.getAuthor()));
        publishedDate.setText(String.valueOf(blog.getPublishedDate()));
        return convertView;
    }
}
