package com.nut.bettersettlers.generator.data;

import java.util.HashMap;
import java.util.Map;

import com.nut.bettersettlers.generator.R;

public final class Maps {
	private Maps() {}
	
	public static final Map<String, Integer> MAPS = new HashMap<String, Integer>();
	static {
		MAPS.put("Standard", R.raw.standard);
		MAPS.put("Large", R.raw.large);
		MAPS.put("XLarge", R.raw.xlarge);
		
		MAPS.put("HeadingForNewShores", R.raw.heading_for_new_shores);
		MAPS.put("HeadingForNewShoresExp", R.raw.heading_for_new_shores_exp);
	}
}
