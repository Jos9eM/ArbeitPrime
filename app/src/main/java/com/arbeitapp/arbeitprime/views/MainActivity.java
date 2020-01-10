package com.arbeitapp.arbeitprime.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.arbeitapp.arbeitprime.R;
import com.arbeitapp.arbeitprime.fragments.MainFragment;

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

    @Override
    public void onBackPressed() {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setMessage(getString(R.string.salirApp));
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                (dialog, which) -> {
                    dialog.dismiss();
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(homeIntent);  });
        alertDialog.show();
    }
}
