package heat.world.heat.draw;

import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.world.*;
import heat.world.heat.HeatBlock.*;

// same thing as in GenericCrafter
public class DrawHeat{
	protected static final Rand rand = new Rand();

	public void draw(HeatBlockBuild build){
		Draw.rect(build.block.region, build.x, build.y, build.block.rotate ? build.rotdeg() : 0);
	}
	public void drawLight(HeatBlockBuild build){}
	public void load(Block block){}
	public TextureRegion[] icons(Block block){
		return new TextureRegion[]{block.region};
	}
}
