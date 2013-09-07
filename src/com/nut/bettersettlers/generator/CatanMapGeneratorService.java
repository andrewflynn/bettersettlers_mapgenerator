package com.nut.bettersettlers.generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.nut.bettersettlers.generator.data.CatanMapGenerator;
import com.nut.bettersettlers.generator.data.Maps;

public class CatanMapGeneratorService extends IntentService {
    private static final String TAG = "CatanMapGenerator";

    public CatanMapGeneratorService() {
        super(CatanMapGeneratorService.class.getName());
    }

    public static void start(Context context) {
        context.startService(new Intent(context, CatanMapGeneratorService.class));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Start");

        Resources res = getResources();
        for (Map.Entry<String, Integer> map : Maps.MAPS.entrySet()) {
            Log.i(TAG, "Writing " + map.getKey());
            write(map.getKey() + ".java", CatanMapGenerator.generateFromJson(res.openRawResource(map.getValue())).toClassString(map.getKey()));
        }
    }

    private void write(String name, String str) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File Not Found", e);
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {}
            }
        }
    }
}
