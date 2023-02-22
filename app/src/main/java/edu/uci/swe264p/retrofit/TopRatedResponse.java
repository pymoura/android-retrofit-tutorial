package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedResponse {
    @SerializedName("page")
    private final Integer page;
    @SerializedName("results")
    private final List<Movie> results;


    public TopRatedResponse(Integer page, List<Movie> results) {
        this.page = page;
        this.results = results;
    }

    public List<Movie> getResults(){
        return results;
    }
}
