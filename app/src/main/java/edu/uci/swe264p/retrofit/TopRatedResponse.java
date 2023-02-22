package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Attributes and methods to construct TopRatedResponse objects.
 * The method getResults() returns a list of objects (Movie) - the attributes and methods
 * of objects are defined by Movie class.
 */
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
