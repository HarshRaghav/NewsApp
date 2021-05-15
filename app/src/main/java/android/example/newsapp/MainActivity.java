package android.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements onItemClicked{
    private NewsListAdapter newsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        newsListAdapter = new NewsListAdapter(this);
        RecyclerView rv =(RecyclerView)findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(newsListAdapter);
    }
    public void fetchData(){
        ArrayList<News> newsArrayList = new ArrayList<>();

        String url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=0636375c1cca453d9e0a8a0745a0b7f2";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = response.optJSONArray("articles");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    String title = jsonObject.optString("title");
                    String urlToImage = jsonObject.optString("urlToImage");
                    String author = jsonObject.optString("author");
                    String url = jsonObject.optString("url");
                    newsArrayList.add(new News(title,author,urlToImage,url));
                    newsListAdapter.Update(newsArrayList);
                }
            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
            }
        }) {@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("User-Agent", "Mozilla/5.0");
            return headers;
        }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onItemClick(News str) {
        CustomTabsIntent.Builder CustTab= new CustomTabsIntent.Builder();
        CustomTabsIntent var = CustTab.build();
        var.launchUrl(this, Uri.parse(str.getUrl()));
    }
}
