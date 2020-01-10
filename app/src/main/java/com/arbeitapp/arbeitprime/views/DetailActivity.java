package com.arbeitapp.arbeitprime.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arbeitapp.arbeitprime.R;
import com.arbeitapp.arbeitprime.utils.others.Utils;
import com.arbeitapp.arbeitprime.viewmodels.DetailsViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private String mUrl;

    @BindView(R.id.errorLayout) ConstraintLayout errorlayout;
    @BindView(R.id.layout) LinearLayout layout;
    @BindView(R.id.backdrop) ImageView imageView;
    @BindView(R.id.title_on_appbar) TextView title;
    @BindView(R.id.originalTitle) TextView originalTitle;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.releaseDate) TextView date;
    @BindView(R.id.rating) TextView rate;
    @BindView(R.id.overview) TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id", -1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Utils.isNetworkAvailable()){

            try {
                DetailsViewModel viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
                viewModel.init(id);

                viewModel.getMovie().observe(this, detail -> {

                    getSupportActionBar().setTitle(detail.getOriginalTitle());
                    String image = Utils.IMAGE_PREFIX + detail.getPosterPath();
                    String min = detail.getRuntime() + " min";
                    String rating = Double.toString(detail.getVoteAverage());

                    mUrl = image;

                    title.setText(detail.getTitle());
                    originalTitle.setText(detail.getOriginalTitle());
                    time.setText(min);
                    date.setText(detail.getReleaseDate());
                    rate.setText(rating);
                    desc.setText(detail.getOverview());

                    Glide.with(this).load(image)
                            .placeholder(Utils.getRandomDrawbleColor())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imageView);

                });
            } catch (RuntimeException e){
                errorPage();
            }

        } else {
             errorPage();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                shareMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void errorPage(){
        errorlayout.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
    }

    //@OnItemClick(R.id.share)
    public void shareMovie() {
        try {
            Intent mySharingIntent = new Intent(Intent.ACTION_SEND);
            mySharingIntent.setType("text/html");
            mySharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Ve esta Pelicula: ");
            mySharingIntent.putExtra(Intent.EXTRA_TEXT, mUrl);
            Intent shareIntent = Intent.createChooser(mySharingIntent, "Share vía");
            startActivity(shareIntent);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),
                    "No fue posible Compartir la Pelicula", Toast.LENGTH_SHORT).show();
        }
    }

}
