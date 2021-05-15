package android.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
interface onItemClicked{
    public void onItemClick(News str) ;
}
public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder>{
    public ArrayList<News> items = new ArrayList<>();
    private android.example.newsapp.onItemClicked clicked;
    public NewsListAdapter( onItemClicked clicked){
        this.clicked= clicked;
    }

    public void Update(ArrayList<News> news){
        items.clear();
        items.addAll(news);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news , parent , false);
        final NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        clicked.onItemClick(items.get(newsViewHolder.getAdapterPosition()));
                                    }
                                });
        return newsViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = items.get(position);

        holder.tv.setText(news.getTitle());
        holder.author.setText(news.getAuthor());
        Glide.with(holder.itemView.getContext()).load(news.getImageUrl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder{

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    TextView tv  =(TextView)itemView.findViewById(R.id.title);
    ImageView iv =(ImageView)itemView.findViewById(R.id.image);
    TextView author =(TextView)itemView.findViewById(R.id.author);
}