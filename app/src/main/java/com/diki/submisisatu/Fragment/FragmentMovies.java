package com.diki.submisisatu.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.diki.submisisatu.Adapter.ListMovieAdapter;
import com.diki.submisisatu.Api.MovieApi;
import com.diki.submisisatu.Api.RetrofitApi;
import com.diki.submisisatu.Api.Scraper;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.Model.TV;
import com.diki.submisisatu.R;
import com.diki.submisisatu.repo.FavoriteMovieRepository;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovies extends Fragment {
    private ListMovieAdapter listMovieAdapter;
    private ArrayList<Movie> list  ;
    private static final String API_KEY = BuildConfig.APIKEY;
    private ArrayList<Movie>  movieList = new ArrayList<>();
    private static final String STATE_RESULT = "state_result";
    private ProgressBar progressBar;
    private FavoriteMovieRepository db;



    public FragmentMovies() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvMainMovie = view.findViewById(R.id.lv_movies);
        progressBar = view.findViewById(R.id.Loading);
        progressBar.setVisibility(View.VISIBLE);
        list =  new ArrayList<>();

        System.out.println("ANJIR" + movieList.size());
        if(savedInstanceState != null){
            movieList = savedInstanceState.getParcelableArrayList(STATE_RESULT);
//            movieList.addAll(Objects.requireNonNull(movieList));
            progressBar.setVisibility(View.GONE);
            System.out.println(movieList.size() + "ANJIR");
        }
        else {
            fetchDataMovie();
        }

        db = FavoriteMovieRepository.getInstance(getContext());

        listMovieAdapter = new ListMovieAdapter(getContext(),movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMainMovie.setLayoutManager(layoutManager);
        rvMainMovie.setAdapter(listMovieAdapter);
//        setView(false);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_movies, container, false);
    }


    private  void fetchDataMovie(){
        MovieApi movieApi = RetrofitApi.getClient().create(MovieApi.class);
        movieApi.findNowPlayingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Scraper<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Scraper<Movie> movieScraper) {
                        initData(movieScraper.getResultMovies());
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    private void initData(List<Movie> Movie){
        movieList.clear();
        movieList.addAll(Movie);

        for (int i=0;i<movieList.size();i++){
            movieList.get(i).setTvShow(false);
        }

        listMovieAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }




    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        System.out.println("SIZE: " + movieList.size());
        savedInstanceState.putParcelableArrayList(STATE_RESULT, movieList);
    }














}
