package heat.world.heat.production;

import arc.util.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import heat.world.heat.*;
import heat.world.heat.draw.*;
import heat.world.heat.distribution.HeatConveyor.*;

public class HeatSource extends HeatBlock {
	public HeatSource(String name) {
		super(name);
		drawer = new DrawInsideHeat();
		buildVisibility = BuildVisibility.shown;
	}

	public class HeatSourceBuild extends HeatBlock.HeatBlockBuild {
		@Override
		public void updateTile() {
			if (heat.heat <= maxHeat - 2f) addHeat(Time.delta, this);
			transmitHeat();
		}

		public void transmitHeat() {
			for (int i=0; i<4; i++) {
				Building next = this.nearby(i);
				if (next instanceof HeatConveyorBuild) {
					if (((HeatConveyorBuild) next).acceptHeat(((HeatConveyorBuild) next).heatRange()[1], this) && next.front() != this) {
						((HeatConveyorBuild) next).setHeat(((HeatConveyorBuild) next).heatRange()[1], this);
					}
				}
			}
		}

		@Override
		public boolean acceptHeat(float heat, Building src) {return false;}
	}
}