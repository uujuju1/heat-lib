package heat.world.heat.distribution;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import heat.world.heat.*;
import heat.world.heat.draw.*;

public class HeatConveyor extends HeatBlock {

	public HeatConveyor(String name) {
		super(name);
		rotate = true;
		drawer = new DrawConveyor();
		heatTransmittance = 0.1f;
	}

	public class HeatConveyorBuild extends HeatBlock.HeatBlockBuild {
		@Override
		public void updateTile() {
			super.updateTile();
			if(front() instanceof HeatConveyorBuild && front().front() != this) {
				if (((HeatConveyorBuild) front()).acceptHeat(0f, this)) {
					((HeatConveyorBuild) front()).addHeat(heatModule().heat * heatTransmittance, this);
					removeHeat(heatModule().heat * heatTransmittance, front());
				}
			}
		}

		@Override
		public boolean acceptHeat(float heat, Building src) {
			if (src instanceof HeatBlockBuild && front() != src) return true;
			return false;
		}
	}
}