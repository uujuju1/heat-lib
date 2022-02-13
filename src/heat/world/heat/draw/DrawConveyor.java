package heat.world.heat.draw;

import arc.*;
import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.graphics.*;
import heat.world.heat.draw.*;
import heat.world.heat.HeatBlock.*;
import heat.world.heat.distribution.HeatConveyor.*;
// same as DrawInsideHeat but with a spriteSheet for heat conveyor
public class DrawConveyor extends DrawHeat {
	public TextureRegion base, heat, top, connection, closed;
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
		base = Core.atlas.find(block.name + "-base");
		heat = Core.atlas.find(block.name + "-heat");
		top = Core.atlas.find(block.name + "-top");
		connection = Core.atlas.find(block.name + "-connection");
		closed = Core.atlas.find(block.name + "-closed");
	}

	@Override
	public void draw(HeatBlockBuild build) {
		Draw.rect(base, build.x, build.y, 0f);
		Draw.color(heatColor);
		Draw.alpha(build.heatf());
		Draw.rect(heat, build.x, build.y, 0f);
		Draw.color();
		for(int i = 0; i < 4; i++) {
			Building next = build.nearby(i);
			Building front = build.front();
			if (next instanceof HeatConveyorBuild) {
				if (build.acceptHeat(0f, build) && next.front() == build) {
					Draw.rect(connection, build.x, build.y, i * 90f);
				} else Draw.rect(closed, build.x, build.y, i * 90f);
			} else {
				if (next instanceof HeatBlockBuild) {
					if ((((HeatBlockBuild) next).acceptHeat(0f, build) && build.front() == next) || (((HeatBlockBuild) next).outputsHeat(0f, build) && build.front() != next)) {
						Draw.rect(connection, build.x, build.y, i * 90f);
					} else {
						Draw.rect(closed, build.x, build.y, i * 90f);
					}
				} else {
					Draw.rect(closed, build.x, build.y, i * 90f);
				}
			}
			if (front instanceof HeatConveyorBuild) {
				if (((HeatConveyorBuild) front).acceptHeat(0f, build) && front.front() != build) {
					Draw.rect(connection, build.x, build.y, build.block.rotate ? build.rotdeg() : 0f);
				} else Draw.rect(closed, build.x, build.y, build.block.rotate ? build.rotdeg() : 0f);
			} else Draw.rect(closed, build.x, build.y, build.block.rotate ? build.rotdeg() : 0f);
		}
		Draw.rect(top, build.x, build.y, build.block.rotate ? build.rotdeg() : 0f);
	}
	
	@Override
	public void drawLight(HeatBlockBuild build) {
		Drawf.light(build.x, build.y, lightRadius, heatColor, build.heatf());
	}
}