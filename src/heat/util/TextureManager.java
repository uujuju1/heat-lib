package heat.util;

import arc.math.*;
import arc.graphics.g2d.*;

public class TextureManager {
	// borrowed from project unity because its just very userful
	public static TextureRegion[] getRegions(TextureRegion region, int w, int h, int tilesize){
		int size = w * h;
		TextureRegion[] regions = new TextureRegion[size];

		float tileW = (region.u2 - region.u) / w;
		float tileH = (region.v2 - region.v) / h;

		for(int i = 0; i < size; i++){
			float tileX = ((float)(i % w)) / w;
			float tileY = ((float)(i / w)) / h;
			TextureRegion reg = new TextureRegion(region);

			//start coordinate
			reg.u = Mathf.map(tileX, 0f, 1f, reg.u, reg.u2) + tileW * 0.01f;
			reg.v = Mathf.map(tileY, 0f, 1f, reg.v, reg.v2) + tileH * 0.01f;
			//end coordinate
			reg.u2 = reg.u + tileW * 0.98f;
			reg.v2 = reg.v + tileH * 0.98f;

			reg.width = reg.height = tilesize;

			regions[i] = reg;
		}
		return regions;
	}
}