package android.example.newsapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
        private static MySingleton instance;
        private RequestQueue requestQueue;
        private static Context context;

        private MySingleton(Context context){
            this.context=context;
        }

        private RequestQueue getRequestQueue(){
            if(requestQueue == null){
                requestQueue= Volley.newRequestQueue(context.getApplicationContext());
            }
            return requestQueue;
        }

        public static synchronized MySingleton getInstance(Context context){
            if(instance == null){
                instance=new MySingleton(context);
            }
            return instance;
        }
        public <T> void addToRequestQueue(Request<T> request){
            getRequestQueue().add(request);
        }
    }
