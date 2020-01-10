package com.arbeitapp.arbeitprime.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arbeitapp.arbeitprime.R;
import com.arbeitapp.arbeitprime.adapters.MoviesAdapter;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.utils.Utils;
import com.arbeitapp.arbeitprime.utils.ZoomOutPageTransformer;
import com.arbeitapp.arbeitprime.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String IMAGE_URL = "https://image.tmdb.org/t/p/original";
    private String TAG = MainActivity.class.getSimpleName();

    private MainViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getMovies().observe(this, listObserver);


        if(Utils.isNetworkAvailable(getApplicationContext())){
            try {
                mViewModel.loadJson();
            } catch (RuntimeException e){
                Log.d(TAG, e.getMessage());   }
        } else {
            Toast.makeText(getApplicationContext(), "No hay internet", Toast.LENGTH_LONG).show();
        }
    }

    final Observer<List<Movie>> listObserver = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> results) {


        }
    };
}
