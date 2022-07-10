package heat.world.heat.production;

import arc.util.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import heat.world.heat.*;

public class HeatSource extends HeatBlock {

	public HeatSource(String name) {
		super(name);
		acceptsHeat = false;
	}

	public class HeatSourceBuild extends HeatBlockBuild {
		@Override
		public void updateTile() {
			for (int i = 0; i < 4; i++) {
				HeatBlockBuild next;
				if (nearby(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) nearby(i);
					if (next.acceptHeat(((HeatBlock) next.block).maxHeat, this)) next.setHeat(((HeatBlock) next.block).maxHeat, this);
				}
			}
		}
	}
}