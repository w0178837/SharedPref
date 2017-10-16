package com.example.w0178837.sharedpref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*; // for Shared Pref object

public class MainActivity extends AppCompatActivity {

    private SeekBar sbText;
    private EditText etText;
    private Button btnSave;
    private SharedPreferences prefs;
    private static final String TEXT_VALUE_KEY = "textvalue";
    private static final String FONT_SIZE_KEY = "fontsize";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button) findViewById(R.id.btnSave);
        sbText = (SeekBar) findViewById(R.id.sbText);
        etText = (EditText) findViewById(R.id.etText);

        prefs = getPreferences(MODE_PRIVATE);
        float fontSize = prefs.getFloat(FONT_SIZE_KEY, 8); // 8 is default
        sbText.setProgress((int) fontSize);
        etText.setText(prefs.getString(TEXT_VALUE_KEY, "")); // "" is default value
        etText.setTextSize(TypedValue.COMPLEX_UNIT_PX,sbText.getProgress());

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                // save the values string and fontsize
                editor.putFloat(FONT_SIZE_KEY, etText.getTextSize());
                editor.putString(TEXT_VALUE_KEY, etText.getText().toString());
                editor.commit();
            }
        });

        sbText.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                etText.setTextSize(TypedValue.COMPLEX_UNIT_PX, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {} // not used


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {} // not used
        });

    }
}
