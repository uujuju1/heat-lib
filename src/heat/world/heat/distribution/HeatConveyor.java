package heat.world.heat.distribution;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.graphics.*;
import heat.world.heat.*;

public class HeatConveyor extends HeatBlock {
	public TextureRegion connection, connectionHeat;

	public HeatConveyor(String name) {
		super(name);
		rotate = true;
		acceptsHeat = outputsHeat = true;
		heatTransmittance = 0.1f;
	}

	@Override
	public void load() {
		super.load();
		connection = Core.atlas.find(name + "-connection");
		connectionHeat = Core.atlas.find(name + "-connection-heat");
	}

	@Override
	public boolean canReplace(Block other) {
		return super.canReplace(other) || other instanceof HeatConveyor;
	}

	public class HeatConveyorBuild extends HeatBlockBuild {
		@Override
		public void draw() {
			Draw.rect(region, x, y, 0);
			for (int i = 0; i < 4; i++) {
				HeatBlockBuild next;
				if (nearby(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) nearby(i);
					if (next.acceptHeat(heatModule().heat * heatTransmittance, this)) Draw.rect(connection, x, y, i * 90);
				}
			}
			Draw.color(Pal.turretHeat);
			Draw.alpha(heatf());
			drawHeat();
			for (int i = 0; i < 4; i++) {
				HeatBlockBuild next;
				if (nearby(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) nearby(i);
					if (next.acceptHeat(heatModule().heat * heatTransmittance, this)) Draw.rect(connectionHeat, x, y, i * 90);
				}
			}
		}
	}
}