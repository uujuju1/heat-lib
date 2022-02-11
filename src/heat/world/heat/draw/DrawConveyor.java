package heat.world.heat.draw;

import arc.*;
import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.world.*;
import mindustry.graphics.*;
import heat.util.*;
import heat.world.heat.draw.*;
import heat.world.heat.HeatBlock.*;
import heat.world.heat.distribution.HeatConveyor.*;
// same as DrawInsideHeat but with a spriteSheet for heat conveyor
public class DrawConveyor extends DrawHeat {
	public TextureRegion base, heat, top, open, closed;
	public float lightRadius = 40f;
	public Color heatColor;

	public DrawConveyor(Color heatColor) {
		this.heatColor = heatColor;
	}
	public DrawConveyor() {
		this.heatColor = Pal.turretHeat;
	}

	@Override
	public void load(Block block) {
		base = Core.atlas.find(name + "-base");
		heat = Core.atlas.find(name + "-heat");
		top = Core.atlas.find(name + "-top");
		open = Core.atlas.find(name + "-open");
		closed = Core.atlas.find(name + "-closed");
	}

	@Override
	public void draw(HeatBlockBuild build) {
		Draw.rect(base, build.x, build.y, 0f);
		Draw.color(heatColor);
		Draw.rect(heat, build.x, build.y, 0f);
		Draw.color();
		for(int i = 0; i < 4; i++) {
			HeatBlockBuild next = build.nearby(i);
			if (next.acceptHeat() || next.outputsHeat()) {
				Draw.rect(open, build.x, build.y, i * -90f);
			} else {
				Draw.rect(closed, build.x, build.y, i * -90f);
			}
		}
		Draw.rect(top, build.x, build.y, build.rotate ? build.rotdeg() : 0f);
	}

	@Override
	public void drawLight(HeatBlockBuild build) {
		Drawf.light(build.x, build.y, lightRadius, heatColor, build.heatf());
	}
}