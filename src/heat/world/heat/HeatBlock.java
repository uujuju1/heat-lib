package heat.world.heat;

import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import mindustry.world.Block;
import heat.world.heat.draw.DrawHeat;

public class HeatBlock extends Block {
	public DrawHeat drawer = new DrawHeat();

	// range of heat that block can hold
	// less than, min block sets heat to minHeat
	// more than, max block explodes
	public float minHeat, maxHeat;
	// percentage of heat transmition
	public float heatTransmittance;
	// scale of delta time worth of heat lost
	public float coolDownScl = 0.1f;

	public HeatBlock(String name) {
		super(name);
		solid = destructible = true;
		buildVisibility = BuildVisibility.shown;
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
		bars.add("heat", entity -> new Bar(Core.bundle.get("bar.heat") + ":" + ((HeatBlockBuild) entity).heatModule().heat, Pal.turretHeat, () -> ((HeatBlockBuild) entity).heatf()));
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
				setHeat(minHeat + 1, this);
			}
			if (heat.heat > maxHeat) {
				kill();
			}
		}

		@Override
		public void updateTile() {
			overheat();
			if (heat.heat > minHeat) {
				removeHeat(Time.delta * coolDownScl, this);
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

		// for outside block reasons
		@Override
		public float[] heatRange() {
			return new float[]{minHeat, maxHeat};
		}

		@Override
		public void write(Writes w){
			super.write(w);
			w.f(heat.heat);
		}

		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			heat.heat = read.f();
		}
	}
}