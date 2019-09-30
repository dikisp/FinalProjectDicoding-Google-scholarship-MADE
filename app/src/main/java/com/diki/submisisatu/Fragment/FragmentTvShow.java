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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.diki.submisisatu.Adapter.TvShowAdapter;
import com.diki.submisisatu.Api.APIClient;
import com.diki.submisisatu.Api.MovieApi;
import com.diki.submisisatu.Api.Scraper;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.Model.Response;
import com.diki.submisisatu.Model.TV;
import com.diki.submisisatu.R;
import com.diki.submisisatu.repo.FavoriteMovieRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTvShow extends Fragment {
    private static final String API_KEY = BuildConfig.APIKEY;
    private static final String STATE_RESULT = "state_result";
    RecyclerView rvCategory;
    private TvShowAdapter listMovieAdapter;
    private ArrayList<TV> list;
    private ArrayList<TV> movieList = new ArrayList<>();
    private ProgressBar loading;
    private ProgressBar progressBar;
    private FavoriteMovieRepository db;

    private Button btnSearch;
    private EditText edtSearch, src;

    public FragmentTvShow() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvMainMovie = view.findViewById(R.id.lv_movies);
        progressBar = view.findViewById(R.id.Loading);
        progressBar.setVisibility(View.VISIBLE);
        list = new ArrayList<>();


        if (savedInstanceState != null) {
            movieList = savedInstanceState.getParcelableArrayList(STATE_RESULT);
//            movieList.addAll(Objects.requireNonNull(list));
            progressBar.setVisibility(View.GONE);
        } else {
            fetchDataMovie();
        }


        for (int i = 0; i < movieList.size(); i++) {
            movieList.get(i).setTvShow(true);
        }


        listMovieAdapter = new TvShowAdapter(getContext(), movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMainMovie.setLayoutManager(layoutManager);
        rvMainMovie.setAdapter(listMovieAdapter);


//        setView(false);

        edtSearch = view.findViewById(R.id.edtSearchText);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryData = edtSearch.getText().toString();
                Log.i("querySearch", queryData);
                searchData(queryData);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_movies, container, false);
    }


    private void fetchDataMovie() {
        MovieApi movieApi = APIClient.getClient().create(MovieApi.class);
        movieApi.findOnTheAirTv(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Scraper<TV>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Scraper<TV> movieScraper) {
                        initData(movieScraper.getResultTv());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void initData(List<TV> TV) {
        movieList.clear();
        movieList.addAll(TV);
        for (int i = 0; i < movieList.size(); i++) {
            movieList.get(i).setTvShow(true);
        }

        listMovieAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(STATE_RESULT, movieList);
    }

    //searchMovie

    void searchData(String queryData) {
        progressBar.setVisibility(View.VISIBLE);
        MovieApi movieService = APIClient.getClient()
                .create(MovieApi.class);
        movieService.searchTvShow(BuildConfig.APIKEY, queryData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<TV>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response<TV> tvResponse) {
                        initData(tvResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        progressBar.setVisibility(View.GONE);


    }


    void processData(List<TV> data) {
        movieList.clear();
        movieList.addAll(data);
        progressBar.setVisibility(View.GONE);
        listMovieAdapter.notifyDataSetChanged();
    }


}
