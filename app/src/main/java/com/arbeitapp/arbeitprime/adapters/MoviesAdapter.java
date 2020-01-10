package com.arbeitapp.arbeitprime.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.arbeitapp.arbeitprime.R;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.utils.context.MyApp;
import com.arbeitapp.arbeitprime.utils.others.Utils;
import com.arbeitapp.arbeitprime.views.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MoviesAdapter extends PagerAdapter {

    private Context context;
    private String EXTRA_MESSAGE = "Id";
    private List <Movie> movieList;


    @BindView(R.id.title) TextView titulo;
    @BindView(R.id.description) TextView descripcion;
    @BindView(R.id.imageCaroussel) ImageView imageView;
    @BindView(R.id.progress) ProgressBar progressBar;


    public MoviesAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        if (movieList != null){
            return movieList.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_caroussel, container, false);

        ButterKnife.bind(this, view1);

        String[] imagesUrl =  new String[10];
        String[] titles =  new String[10];
        String[] descriptions =  new String[10];

        for (int i = 0; i < 10; i++){
            String cmp = movieList.get(i).getPosterPath();
            imagesUrl[i] = Utils.IMAGE_PREFIX + cmp;
            titles [i] = movieList.get(i).getTitle();
            descriptions [i] = movieList.get(i).getOverview();
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor())
                        .error(Utils.getRandomDrawbleColor())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop();

        Glide.with(context).load(imagesUrl[position]).apply(requestOptions)
                .listener(requestListener)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        titulo.setText(titles[position]);
        descripcion.setText(descriptions[position]);

        container.addView(view1);

        view1.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(EXTRA_MESSAGE, movieList.get(position).getId());
            context.startActivity(intent);
        });

        return view1;
    }

    private RequestListener<Drawable> requestListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;        }
    };

    public void setData(List<Movie> movies){
        this.movieList = movies;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
