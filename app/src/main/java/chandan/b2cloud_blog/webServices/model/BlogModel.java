package chandan.b2cloud_blog.webServices.model;

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
}
