package com.nut.bettersettlers.generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.nut.bettersettlers.generator.data.CatanMapGenerator;

public class CatanMapGeneratorService extends IntentService {
	private static final String TAG = "CatanMapGenerator";
	
	private static final Map<String, Integer> MAPS = new HashMap<String, Integer>();
	static {
		MAPS.put("Standard", R.raw.standard);
		MAPS.put("Large", R.raw.large);
		MAPS.put("XLarge", R.raw.xlarge);
		
		MAPS.put("HeadingForNewShores", R.raw.heading_for_new_shores);
		MAPS.put("TheFourIslands", R.raw.the_four_islands);
		MAPS.put("TheFogIsland", R.raw.the_fog_island);
		MAPS.put("ThroughTheDesert", R.raw.through_the_desert);
		MAPS.put("TheForgottenTribe", R.raw.the_forgotten_tribe);
		MAPS.put("ClothForCatan", R.raw.cloth_for_catan);
		MAPS.put("ThePirateIslands", R.raw.the_pirate_islands);
		MAPS.put("TheWondersOfCatan", R.raw.the_wonders_of_catan);
		MAPS.put("NewWorld", R.raw.new_world);
		
		MAPS.put("HeadingForNewShoresExp", R.raw.heading_for_new_shores_exp);
		MAPS.put("TheFourIslandsExp", R.raw.the_four_islands_exp);
		MAPS.put("TheFogIslandExp", R.raw.the_fog_island_exp);
		MAPS.put("ThroughTheDesertExp", R.raw.through_the_desert_exp);
		MAPS.put("TheForgottenTribeExp", R.raw.the_forgotten_tribe_exp);
		MAPS.put("ClothForCatanExp", R.raw.cloth_for_catan_exp);
		MAPS.put("ThePirateIslandsExp", R.raw.the_pirate_islands_exp);
		MAPS.put("TheWondersOfCatanExp", R.raw.the_wonders_of_catan_exp);
		MAPS.put("NewWorldExp", R.raw.new_world_exp);
	}
	
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
		for (Map.Entry<String, Integer> map : MAPS.entrySet()) {
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
