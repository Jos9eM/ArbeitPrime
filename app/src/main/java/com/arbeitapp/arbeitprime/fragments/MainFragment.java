package com.arbeitapp.arbeitprime.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arbeitapp.arbeitprime.R;
import com.arbeitapp.arbeitprime.adapters.MoviesAdapter;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.utils.others.ZoomOutPageTransformer;
import com.arbeitapp.arbeitprime.viewmodels.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    private List<Movie> movieList;
    private ZoomOutPageTransformer zoomOutPageTransformer = new ZoomOutPageTransformer();
    private MainViewModel mainViewModel;
    private MoviesAdapter adapter;

    public MainFragment(){ }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return new MainFragment();
    }

    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.dots) LinearLayout dots;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        ButterKnife.bind(this, view);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        adapter = new MoviesAdapter(getActivity(), movieList);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, zoomOutPageTransformer);
        viewPager.addOnPageChangeListener(pageListener);
        addDots(0);

        loadMovies();

        return view;
    }

    private void loadMovies() {
        mainViewModel.getTop10Movies().observe(getActivity(), listResource -> {
            movieList = listResource.data;
            adapter.setData(movieList);
        });
    }

    public void addDots(int position){
        TextView[] mdots = new TextView[10];
        dots.removeAllViews();

        for (int i = 0; i < mdots.length; i++){
            mdots[i] = new TextView(getActivity());
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.transparentWhite));

            dots.addView(mdots[i]);   }

        if (mdots.length > 0){
            mdots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark)); }
    }

    ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
