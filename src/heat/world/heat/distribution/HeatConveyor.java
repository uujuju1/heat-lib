package heat.world.heat.distribution;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;
import heat.util.*;
import heat.world.heat.*;

public class HeatConveyor extends HeatBlock {
	// the percentage of heat that is passed to the other conveyor. The more hot, the more heat it gives
	public float heatConductivity = 0.1f;

	public HeatConveyor(String name) {
		super(name);
		rotate = true;
		drawer = new DrawConveyor(Pal.turretHeat);
	}

	public class HeatConveyorBuild extends HeatBlock.HeatBlockBuild {
		@Override
		public void updateTile() {
			if(front() instanceof HeatConveyorBuild && front().front() != this) {
				((HeatConveyorBuild) front()).addHeat(heatModule().heat * heatConductivity, this);
				removeHeat(heatModule().heat * heatConductivity, front());
			}
		}
	}
}