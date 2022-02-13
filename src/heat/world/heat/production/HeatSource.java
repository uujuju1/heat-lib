package heat.world.heat.production;

import mindustry.gen.*;
import mindustry.world.meta.*;
import heat.world.heat.*;
import heat.world.heat.draw.*;
import heat.world.heat.distribution.HeatConveyor.*;

public class HeatSource extends HeatBlock {
	public HeatSource(String name) {
		super(name);
		drawer = new DrawInsideHeat();
		heatTransmittance = 1f;
		buildVisibility = BuildVisibility.shown;
	}

	public class HeatSourceBuild extends HeatBlock.HeatBlockBuild {
		@Override
		public void updateTile() {
			super.updateTile();
			setHeat(maxHeat, this);
		}

		public void transmitHeat() {
			for (int i=0; i<4; i++) {
				Building next = this.nearby(i);
				if (next instanceof HeatConveyorBuild) {
					if (((HeatConveyorBuild) next).acceptHeat(((HeatConveyorBuild) next).block.maxHeat, this) && next.front() != this) {
						((HeatConveyorBuild) next).setHeat(((HeatConveyorBuild) next).block.maxHeat, this);
					}
				}
			}
		}
	}
}