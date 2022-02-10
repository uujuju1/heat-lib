package heat.world.heat.distribution;

import heat.world.heat.*;

public class HeatConveyor extends HeatBlock {
	public TextureRegion regions, baseRegion, heatRegion;

	public HeatConveyor(String name) {
		super(name);
		rotate = true;
	}

	@Override
	public void load() {
		super.load();
		Core.atlas.find(name + "-states");
	}

	public class HeatConveyorBuild extends HeatBlock.HeatBlockBuild {
		@Override
		public void draw() {
			Draw.rect(baseRegion, x, y, 0);
			// Draw.color(heatColor);
			Draw.alpha(heatf());
			Draw.rect(heatRegion, x, y, 0);
			for (int i = 0; i < 10; i++) {
				Draw.rect(TextureManager.getRegions(regions, 5, 2)[i], x - 16 + (i*8), y - 4 + (i>5 ? 0 : 8), rotate ? this.rotdeg() : 0);
			}
		}
	}
}