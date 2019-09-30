package com.diki.submisisatu.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diki.submisisatu.Fragment.Favorite.FragmentFavoriteMovie;
import com.diki.submisisatu.Fragment.Favorite.FragmentTvFavorite;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorite extends Fragment {
    View view;
    Adapter adapter;
    private ArrayList<Movie> list  ;
    private static final String STATE_RESULT = "state_result";

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FragmentFavoriteMovie(), "Movie");
        adapter.addFragment(new FragmentTvFavorite(), "TV Show");
        viewPager.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        final ViewPager viewPager = ButterKnife.findById(view, R.id.main_pager);
        setupViewPager(viewPager);
        ButterKnife.bind(this, view);
        TabLayout tabLayout = ButterKnife.findById(view, R.id.main_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(STATE_RESULT,list);
    }
}





