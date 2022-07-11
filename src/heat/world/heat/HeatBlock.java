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

		// percentage of heat lost
		heatCooldown = 0.01f;
	public boolean
		acceptsHeat = true,
		outputsHeat = true;

	public HeatBlock(String name) {
		super(name);
		solid = destructible = true;
		update = sync = true;
		buildVisibility = BuildVisibility.shown;
	}

	@Override
	public void load() {
		super.load();
		heat = Core.atlas.find(name + "-heat");
	}

	@Override
	public void setBars() {
		super.setBars();
		addBar("heat", entity -> new Bar(Core.bundle.get("bar.heat"), Pal.turretHeat, () -> ((HeatBlockBuild) entity).heatf()));
	}

	public class HeatBlockBuild extends Building implements HeatBlockComp {
		public HeatModule heatm = new HeatModule();

		@Override
		public HeatModule heatModule() {
			return heatm;
		}

		@Override
		public boolean acceptHeat(float heat, Building src) {
			return acceptsHeat;
		}
		@Override
		public boolean outputHeat(float heat, Building src) {
			return outputsHeat;
		}

		public void addHeat(float heat, @Nullable Building build) {heatModule().heat += heat;}
		public void addHeat(float heat) {addHeat(heat, null);}

		public void removeHeat(float heat, @Nullable Building build) {heatModule().heat -= heat;}
		public void removeHeat(float heat) {removeHeat(heat, null);}

		public void setHeat(float heat, @Nullable Building build) {heatModule().heat = heat;}
		public void setHeat(float heat) {setHeat(heat, null);}

		public void transferHeat(HeatBlockBuild from, HeatBlockBuild to, float amount) {
			from.removeHeat(amount, from);
			to.addHeat(amount, to);
		}

		@Override
		public void overheat() {
			if (heatm.heat < minHeat) {
				setHeat(minHeat, this);
			}
			if (heatm.heat > maxHeat) {
				kill();
			}
		}

		@Override
		public void updateTile() {
			overheat();
			for(int i = 0; i < this.proximity.size; i++) {
				HeatBlockBuild next;
				if (this.proximity.get(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) this.proximity.get(i);
					if (next.acceptHeat(heatModule().heat * heatTransmittance, this) && next.heatModule().heat < heatModule().heat) transferHeat(this, next, heatModule().heat * heatTransmittance);
				}
			}
			removeHeat(heatModule().heat * heatCooldown, this);
		}

		@Override
		public void draw() {
			super.draw();
			Draw.color(Pal.turretHeat);
			Draw.alpha(heatf());
			drawHeat();
		}

		@Override
		public float heatf() {
			return heatm.heat/maxHeat;
		}

		@Override
		public void drawHeat() {
			Draw.rect(heat, x, y, 0);
		}
	}
}