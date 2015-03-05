package chandan.b2cloud_blog.webServices.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import chandan.b2cloud_blog.webServices.model.BlogModel;


public class BlogParser {


    public void parseResponse(JSONObject response, List<BlogModel> blogList) {
        try {
            JSONObject resp = response.getJSONObject("responseData");
            JSONObject feed = resp.getJSONObject("feed");
            JSONArray entries = feed.getJSONArray("entries");

            for (int i = 0; i < entries.length(); i++) {
                JSONObject blogObject = entries.getJSONObject(i);

                BlogModel blog = new BlogModel();
                blog.setAuthor(blogObject.getString("author"));
                blog.setPublishedDate(blogObject.getString("publishedDate"));
                blog.setTitle(blogObject.getString("title"));
                blog.setContent(blogObject.getString("content"));

                blogList.add(blog);
            }
        }catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
