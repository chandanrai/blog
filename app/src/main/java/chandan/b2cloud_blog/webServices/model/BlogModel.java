package chandan.b2cloud_blog.webServices.model;

import android.text.Html;
import android.text.Spanned;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 *  Represents the Blog model
 */
public class BlogModel {
    private String mTitle;
    private String mPublishedDate;
    private String mAuthor;
    private Spanned mContent;

    public Spanned getContent() {
        return mContent;
    }

    public void setContent(Spanned content) {
       mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    /*
     *  Parse the response form server and makes a list of Blog model
     */
    public void parseResponse(JSONObject response, List<BlogModel> blogList) {
        try {
            JSONObject resp = response.getJSONObject("responseData");
            JSONObject feed = resp.getJSONObject("feed");
            JSONArray entries = feed.getJSONArray("entries");

            for (int i = 0; i < entries.length(); i++) {
                JSONObject blogObject = entries.getJSONObject(i);

                this.setAuthor(blogObject.getString("author"));
                this.setPublishedDate(blogObject.getString("publishedDate"));
                this.setTitle(blogObject.getString("title"));
                this.setContent(Html.fromHtml(blogObject.getString("content")));

                blogList.add(this);
            }
        }catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
