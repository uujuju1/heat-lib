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
		acceptsHeat = outputsHeat = true;
		heatTransmittance = 0.1f;
	}

	@Override
	public void load() {
		super.load();
		connection = Core.atlas.find(name + "-connection");
		connectionHeat = Core.atlas.find(name + "-connection-heat");
	}

	public class HeatConveyorBuild extends HeatBlockBuild {
		@Override
		public void draw() {
			Draw.rect(region, x, y, 0);
			for (int i = 0; i < 4; i++) {
				HeatBlockBuild next;
				if (nearby(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) nearby(i);
					if (next.acceptHeat(heatModule().heat * heatTransmittance, this) || next.outputHeat(heatModule().heat * heatTransmittance, this)) Draw.rect(connection, x, y, i * 90);
				}
			}
			Draw.color(Color.valueOf("F8C266"));
			Draw.alpha(heatf() * 0.5f);
			drawHeat();
			for (int i = 0; i < 4; i++) {
				HeatBlockBuild next;
				if (nearby(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) nearby(i);
					if (next.acceptHeat(heatModule().heat * heatTransmittance, this) || next.outputHeat(heatModule().heat * heatTransmittance, this)) Draw.rect(connectionHeat, x, y, i * 90);
				}
			}
		}
	}
}