package com.diki.submisisatu.Fragment.Favorite;


import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.diki.submisisatu.Adapter.FavoriteAdapter;
import com.diki.submisisatu.Database.FavoriteHelper;
import com.diki.submisisatu.DetailMovieActivity;
import com.diki.submisisatu.Model.FavoriteMovie;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.R;
import com.diki.submisisatu.repo.FavoriteMovieRepository;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;

import static com.diki.submisisatu.Database.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavoriteMovie extends Fragment {

    private static final String EXTRA_STATE = "EXTRA_STATE";
    private final List<FavoriteMovie> movieList = new ArrayList<>();
    private static final String STATE_RESULT = "state_result";
    private ArrayList<FavoriteMovie> list  ;
    @BindView(R.id.lv_favorite)
    RecyclerView rvMovie;
    @BindView(R.id.null_layout)
    LinearLayout nullLayout;
    private LoadFavoriteCallback callback;
    private FavoriteAdapter adapter;
    private FavoriteHelper helper;

    private FavoriteMovieRepository db;
    public FragmentFavoriteMovie() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_favorite, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupView(view);

//        if (savedInstanceState == null) {
//            new LoadFavoriteAsync(getContext(), callback).execute();
//        } else {
//            ArrayList<FavoriteMovie> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
//            if (list != null) {
//                adapter.setListFavorite(list);
//            }
//        }
    }

    private void setupView(View view) {
        callback = new LoadFavoriteCallback() {
            @Override
            public void preExecute() {

            }

            @Override
            public void postExecute(Cursor cursor) {
            }
        };
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, getContext(), callback);
//        Objects.requireNonNull(getActivity()).getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);

        rvMovie = view.findViewById(R.id.lv_favorite);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setHasFixedSize(true);


        helper = FavoriteHelper.getInstance(getContext());
        helper.open();


//        adapter = new FavoriteAdapter(getActivity(), new FavoriteAdapter.PostListListener() {
//            @Override
//            public void onPostClick(int id) {
//                Intent intent = new Intent(FragmentFavoriteMovie.this.getContext(), DetailMovieActivity.class);
//                intent.putExtra(DetailMovieActivity.MID, id);
//                FragmentFavoriteMovie.this.startActivity(intent);
//            }
//        });

        db = FavoriteMovieRepository.getInstance(getContext());

        List<FavoriteMovieDB> favoriteMovieDBS = new ArrayList<>();

        for (int i=0;i<db.getFavoriteMovies().size();i++){
            if (!db.getFavoriteMovies().get(i).isTvShow()){
                favoriteMovieDBS.add(db.getFavoriteMovies().get(i));
            }
        }

        adapter = new FavoriteAdapter(getActivity(), favoriteMovieDBS);

        rvMovie.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_RESULT,list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    interface LoadFavoriteCallback {
        void preExecute();

        void postExecute(Cursor cursor);
    }

    private static class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private LoadFavoriteAsync(Context context, LoadFavoriteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
        }
    }

    static class DataObserver extends ContentObserver {

        final Context context;
        final LoadFavoriteCallback callback;

        DataObserver(Handler handler, Context context, LoadFavoriteCallback callback) {
            super(handler);
            this.context = context;
            this.callback = callback;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadFavoriteAsync(context, callback).execute();

        }
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putParcelableArrayList(STATE_RESULT,list);
//    }

}
