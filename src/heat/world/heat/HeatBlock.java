package heat.world.heat;

import arc.struct.Seq;
import arc.graphics.g2d.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import mindustry.world.Block;
import heat.world.heat.draw.DrawHeat;

public class HeatBlock extends Block {
	public DrawHeat drawer = new DrawHeat();
	public float minHeat, maxHeat;

	public HeatBlock(String name) {
		super(name);
		solid = destructible = true;
	}

	@Override
	public void load() {
		super.load();
		drawer.load(this);
	}

	@Override
	public TextureRegion[] icons() {
		return drawer.icons(this);
	}

	@Override
	public void setBars() {
		super.setBars();
		bars.add("heat", entity -> new Bar("bar.heat", Pal.turretHeat, () -> ((HeatBlockBuild) entity).heatf()));
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
			drawer.draw(this);
		}

		@Override
		public void drawLight() {
			drawer.drawLight(this);
		}

		@Override
		public float heatf() {
			return heat.heat/maxHeat;
		}
	}
}