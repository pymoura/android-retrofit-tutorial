package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    static final String TAG = MovieListActivity.class.getSimpleName();
    static final String BASE_URL ="https://api.themoviedb.org/3/";
    static Retrofit retrofit = null;
    final static String API_KEY = "ebbed34e03675736fd9114b566cde8aa";

    List<Movie> movieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        topConnect();
    }

    private void topConnect() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> topCall = movieApiService.getTopRatedMovies(API_KEY);
        topCall.enqueue(new Callback<TopRatedResponse>() {

            @Override
            public void onResponse(Call<TopRatedResponse> topCall, Response<TopRatedResponse> topCallResponse) {
                System.out.println(topCallResponse.body());
                movieList = topCallResponse.body().getResults();
                generateMovieList(movieList);
            }
            @Override
            public void onFailure(Call<TopRatedResponse> topCall, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    private void generateMovieList(List<Movie> mList){
        recyclerView = findViewById(R.id.rvMovieList);
        MovieListAdapter adapter = new MovieListAdapter(this, mList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
