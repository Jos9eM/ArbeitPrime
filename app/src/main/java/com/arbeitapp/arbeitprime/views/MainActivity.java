package com.arbeitapp.arbeitprime.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.arbeitapp.arbeitprime.fragments.MainFragment;
import com.arbeitapp.arbeitprime.models.Movie;
import com.arbeitapp.arbeitprime.utils.Utils;
import com.arbeitapp.arbeitprime.utils.ZoomOutPageTransformer;
import com.arbeitapp.arbeitprime.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        MainFragment mainFragment = new MainFragment();
        transaction.add(R.id.fragment, mainFragment);
        transaction.commit();
    }
}
