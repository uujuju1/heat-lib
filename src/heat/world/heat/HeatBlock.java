package heat.world.heat;

import arc.*;
import arc.util.*;
import arc.util.io.*;
import arc.graphics.g2d.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import mindustry.world.Block;

public class HeatBlock extends Block {
	public TextureRegion heat;
	public float 
		// range of heat
		minHeat = 600,
		maxHeat = 600,

		// percentage of heat transmition
		heatTransmittance = 0.05f,

		// scale of delta time worth of heat lost
		coolDownScl = 0.01f;
	public boolean
		acceptsHeat = true,
		outputsHeat = true;

	public HeatBlock(String name) {
		super(name);
		solid = destructible = true;
		buildVisibility = BuildVisibility.shown;
	}

	@Override
	public void load() {
		super.load();
		heat = Core.atlas.find(name + "-heat");
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
		public boolean acceptHeat() {
			return acceptsHeat;
		}
		@Override
		public boolean outputHeat() {
			return outputsHeat;
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
			for(int i = 0, i < this.proximity.size, i++) {
				if (this.proximity.get(i) instanceof HeatBlockBuild) HeatBlockBuild next = (HeatBlockBuild) this.proximity.get(i);
				if (next.acceptHeat()) transferHeat(this, next, heatModule().heat * heatTransmittance); 
			}
		}

		@Override
		public void draw() {
			super.draw();
			drawHeat();
		}

		@Override
		public float heatf() {
			return heat.heat/maxHeat;
		}

		@Override
		public void drawHeat() {
			Draw.rect(heat, x, y, 0);
		}
	}
}