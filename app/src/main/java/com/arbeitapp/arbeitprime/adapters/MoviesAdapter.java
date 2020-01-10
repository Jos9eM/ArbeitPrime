package com.arbeitapp.arbeitprime.adapters;

import android.content.Context;
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
import com.arbeitapp.arbeitprime.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class MoviesAdapter extends PagerAdapter implements View.OnClickListener {

    private Context context;
    private List <Movie> movieList;
    private View view;
    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView titulo, descripcion;

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
        view = LayoutInflater.from(context).inflate(R.layout.item_caroussel, container, false);
        imageView = view.findViewById(R.id.imageCaroussel);
        titulo = view.findViewById(R.id.title);
        descripcion = view.findViewById(R.id.description);
        progressBar = view.findViewById(R.id.progress);

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

        container.addView(view);

        view.setOnClickListener(v -> {

        });

        return view;
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

    @Override
    public void onClick(View v) {

    }
}
