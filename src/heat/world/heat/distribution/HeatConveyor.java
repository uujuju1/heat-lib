package heat.world.heat.distribution;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import heat.world.heat.*;
import heat.world.heat.draw.*;

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
			super.updateTile();
			if(front() instanceof HeatConveyorBuild && front().front() != this) {
				if (((HeatConveyorBuild) front()).acceptHeat(0f, this)) {
					((HeatConveyorBuild) front()).addHeat(heatModule().heat * heatConductivity, this);
					removeHeat(heatModule().heat * heatConductivity, front());
				}
			}
		}

		@Override
		public boolean acceptHeat(float heat, Building src) {
			if (back() instanceof HeatConveyorBuild && ((HeatConveyorBuild) back()).outputsHeat(0f, this)) return true;
			if (left() instanceof HeatConveyorBuild && ((HeatConveyorBuild) left()).outputsHeat(0f, this)) return true;
			if (right() instanceof HeatConveyorBuild && ((HeatConveyorBuild) right()).outputsHeat(0f, this)) return true;
			return false;
		}
	}
}