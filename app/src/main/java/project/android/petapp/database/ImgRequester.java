package project.android.petapp.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import project.android.petapp.PetAppApplication;


/**
 * Class that handles image requests using Volley.
 */
public class ImgRequester {
    private static ImgRequester instance = null;
    private final Context context;
    private final RequestQueue requestQueue;
    private final ImageLoader imageLoader;
    private final int byteSizeMax;

    private ImgRequester() {
        context = PetAppApplication.getAppContext();
        this.requestQueue = Volley.newRequestQueue(context);
        this.requestQueue.start();
        this.byteSizeMax = calculateMaxByteSize();
        this.imageLoader =
                new ImageLoader(
                        requestQueue,
                        new ImageLoader.ImageCache() {
                            private final LruCache<String, Bitmap> lruCache =
                                    new LruCache<String, Bitmap>(byteSizeMax) {
                                        @Override
                                        protected int sizeOf(String url, Bitmap bitmap) {
                                            return bitmap.getByteCount();
                                        }
                                    };

                            @Override
                            public synchronized Bitmap getBitmap(String url) {
                                return lruCache.get(url);
                            }

                            @Override
                            public synchronized void putBitmap(String url, Bitmap bitmap) {
                                lruCache.put(url, bitmap);
                            }
                        });
    }

    /**
     * Get a static instance of ImgRequester
     */
    public static ImgRequester getInstance() {
        if (instance == null) {
            instance = new ImgRequester();
        }
        return instance;
    }

    /**
     * Sets the image on the given {@link NetworkImageView} to the image at the given URL
     *
     * @param networkImageView {@link NetworkImageView} to set image on
     * @param url              URL of the image
     */
    public void setImageFromUrl(NetworkImageView networkImageView, String url) {
        networkImageView.setImageUrl(url, imageLoader);
    }

    private int calculateMaxByteSize() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        return screenBytes * 3;
    }
}