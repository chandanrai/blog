package chandan.b2cloud_blog;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*
 *  Singleton class used to instantiate volley
 */
public class Networking {

    private static Networking mInstance;
    private RequestQueue mRequestQueue;

    private Networking(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /*
     *  @return Instance of the class
     */
    public static Networking getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Networking(context);
        }
        return mInstance;
    }

    /*
     *  @return Returns the requestQueue
     */
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
