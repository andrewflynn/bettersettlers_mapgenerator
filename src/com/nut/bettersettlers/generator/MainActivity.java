package com.nut.bettersettlers.generator;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CatanMapGeneratorService.start(this);

        setResult(RESULT_OK);
        finish();
    }
}
