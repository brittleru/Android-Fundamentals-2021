package com.example.portfolio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String CATEGORY1 = "top_rated";
    private static final String CATEGORY2 = "upcoming";
    private static final String CATEGORY3 = "now_playing";
    private static final String API_KEY = "b14b65aa5cf463e5247202248fe3db81";
    private static final String POSTER_PATH = "https://image.tmdb.org/t/p/original/";


    private static Retrofit retrofit;

    private RecyclerView moviesRecView;
    private RelativeLayout relativeLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_main, container, false);

//        TextView testView = (TextView) view.findViewById(R.id.test);

        assert getArguments() != null;
        String testString = getArguments().getString("title");


        // RecyclerView Functionality
        moviesRecView = (RecyclerView) view.findViewById(R.id.moviesRecView);
        ArrayList<MovieCardView> movieCardViews = new ArrayList<>();


        GetData service = getRetrofitInstance().create(GetData.class);
        Call<MovieResults.Example> callTop = service.getMovies(CATEGORY1, API_KEY);
        Call<MovieResults.Example> callUpcoming = service.getMovies(CATEGORY2, API_KEY);
        Call<MovieResults.Example> callNow = service.getMovies(CATEGORY3, API_KEY);


        switch (testString.toLowerCase()) {
            case "top-rated":
                callTop.enqueue(new Callback<MovieResults.Example>() {
                    @Override
                    public void onResponse(Call<MovieResults.Example> call, Response<MovieResults.Example> response) {
                        if (response.isSuccessful()) {
                            MovieResults.Example results = response.body();
                            assert results != null;
                            List<MovieResults.Result> resultList = results.getResults();

                            for (MovieResults.Result result : resultList) {
                                String path = POSTER_PATH + result.getPosterPath();
                                String vote = "Average Vote: " + result.getVoteAverage() + "\nVote Count: " + result.getVoteCount();
                                movieCardViews.add(new MovieCardView(result.getTitle(), vote, path, result.getOverview()));

                                MoviesRecViewAdapter adapter = new MoviesRecViewAdapter(getContext());
                                adapter.setMovieCardViews(movieCardViews);
                                moviesRecView.setAdapter(adapter);
                            }
                        } else {
                            Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResults.Example> call, Throwable t) {
                        Log.d("Response", "Error", t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                moviesRecView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                break;

            case "upcoming":
                callUpcoming.enqueue(new Callback<MovieResults.Example>() {
                    @Override
                    public void onResponse(Call<MovieResults.Example> call, Response<MovieResults.Example> response) {
                        if (response.isSuccessful()) {
                            MovieResults.Example results = response.body();
                            assert results != null;
                            List<MovieResults.Result> resultList = results.getResults();

                            for (MovieResults.Result result : resultList) {
                                String path = POSTER_PATH + result.getPosterPath();
                                movieCardViews.add(new MovieCardView(result.getTitle(), result.getReleaseDate(), path, result.getOverview()));

                                MoviesRecViewAdapter adapter = new MoviesRecViewAdapter(getContext());
                                adapter.setMovieCardViews(movieCardViews);
                                moviesRecView.setAdapter(adapter);
                            }
                        } else {
                            Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResults.Example> call, Throwable t) {
                        Log.d("Response", "Error", t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                moviesRecView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                break;

            case "now playing":
                // ========================= Add functionality here
//                testView.setText("3");
                callNow.enqueue(new Callback<MovieResults.Example>() {
                    @Override
                    public void onResponse(Call<MovieResults.Example> call, Response<MovieResults.Example> response) {
                        if (response.isSuccessful()) {
                            MovieResults.Example results = response.body();
                            assert results != null;
                            List<MovieResults.Result> resultList = results.getResults();

                            for (MovieResults.Result result : resultList) {
                                String path = POSTER_PATH + result.getPosterPath();
                                String vote = "Average Vote: " + result.getVoteAverage() +
                                        "\nVote Count: " + result.getVoteCount() +
                                        "\nPopularity: " + result.getPopularity();
                                movieCardViews.add(new MovieCardView(result.getTitle(), vote, path, result.getOverview()));

                                MoviesRecViewAdapter adapter = new MoviesRecViewAdapter(getContext());
                                adapter.setMovieCardViews(movieCardViews);
                                moviesRecView.setAdapter(adapter);
                            }
                        }
                        else {
                            Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResults.Example> call, Throwable t) {
                        Log.d("Response", "Error", t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                moviesRecView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


                break;

            default:
                break;
        }


        return view;
    }


    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}