package heat.world.heat;

import arc.struct.Seq;
import mindustry.gen.*;
import mindustry.world.meta.*;
import mindustry.world.Block;
import heat.world.heat.draw.DrawHeat;

public class HeatBlock extends Block {
	public Seq<DrawHeat> drawers = new Seq<>();
	public float minHeat, maxHeat;

	public HeatBlock(String name) {
		super(name);
	}

	@Override
	public void load() {
		super.load();
		drawers.each(DrawHeat -> DrawHeat.load(this));
	}

	@Override
	public TextureRegion icons() {
		return drawers.each(DrawHeat -> DrawHeat.icons(this));
	}

	public class HeatBlockBuild extends Building implements HeatBlockComp {
		public HeatModule heat = new HeatModule();

		@Override
		public HeatModule heatModule() {
			return heat;
		}

		@Override
		public void overheat() {
			if (heat.heat < minHeat) {
				setHeat(minHeat, this);
			}
			if (heat.heat > maxHeat) {
				kill();
			}
		}

		@Override
		public void draw() {
			drawers.each(DrawHeat -> DrawHeat.draw(this));
		}

		@Override
		public void drawLight() {
			drawers.each(DrawHeat -> DrawHeat.drawLight(this));
		}

		@Override
		public float heatf() {
			return heat.heat/maxHeat;
		}
	}
}