package heat.world.heat.draw;

import arc.*;
import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.world.*;
import mindustry.graphics.*;
import heat.world.heat.HeatBlock.*;
import heat.world.heat.draw.*;

// example code for drawing simple -heat region
public class DrawInsideHeat extends DrawHeat {
	public float heatLightRadius = 40f;
	public Color heatColor;
	public TextureRegion heatRegion, baseRegion;
	protected static final Rand rand = new Rand();

	public DrawInsideHeat(Color heatColor) {
		this.heatColor = heatColor;
	}
	public DrawInsideHeat() {
		this.heatColor = Pal.turretHeat;
	}

	public void draw(HeatBlockBuild build){
		Draw.rect(baseRegion, build.x, build.y, 0);
		Draw.color(heatColor);
		Draw.alpha(build.heatf());
		Draw.rect(heatRegion, build.x, build.y, 0);
		Draw.rect(build.block.region, build.x, build.y, build.block.rotate ? build.rotdeg() : 0);
	}

	public void drawLight(HeatBlockBuild build){
		Drawf.light(build.x, build.y, heatLightRadius, heatColor, build.heatf());
	}

	public void load(Block block){
		heatRegion = Core.atlas.find(block.name + "-heat");
		baseRegion = Core.atlas.find(block.name + "-base");
	}
	public TextureRegion[] icons(Block block){
		return new TextureRegion[]{baseRegion, block.region};
	}
}
