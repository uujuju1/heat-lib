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
	public TextureRegion top, heat, base;
	protected static final Rand rand = new Rand();

	public DrawInsideHeat(Color heatColor) {
		this.heatColor = heatColor;
	}
	public DrawInsideHeat() {
		this.heatColor = Pal.turretHeat;
	}

	@Override
	public void draw(HeatBlockBuild build){
		Draw.rect(baseRegion, build.x, build.y, 0);
		Draw.color(heatColor);
		Draw.alpha(build.heatf());
		Draw.rect(heatRegion, build.x, build.y, 0);
		Draw.rect(build.block.region, build.x, build.y, build.block.rotate ? build.rotdeg() : 0);
	}

	@Override
	public void drawLight(HeatBlockBuild build){
		Drawf.light(build.x, build.y, heatLightRadius, heatColor, build.heatf());
	}

	@Override
	public void load(Block block){
		base = Core.atlas.find(block.name + "-base");
		heat = Core.atlas.find(block.name + "-heat");
		top = Core.atlas.find(block.name + "-top");
	}
}
