package chandan.b2cloud_blog.webServices.model;

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

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    private String mContent;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.mPublishedDate = publishedDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
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
                this.setContent(blogObject.getString("content"));

                blogList.add(this);
            }
        }catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
