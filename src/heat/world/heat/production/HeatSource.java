package heat.world.heat.production;

import arc.util.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import heat.world.heat.*;

public class HeatSource extends HeatBlock {
	public HeatSource(String name) {
		super(name);
		buildVisibility = BuildVisibility.shown;
		acceptHeat = false;
	}

	public class HeatSourceBuild extends HeatBlockBuild {
		@Override
		public void updateTile() {
			for (int i = 0; i < 4; i++) {
				HeatBlockBuild next;
				if (nearby(i) instanceof HeatBlockBuild) {
					next = (HeatBlockBuild) nearby(i);
					if (next.acceptHeat()) next.setHeat(((HeatBlock) next.block).maxHeat, this);
				}
			}
		}

		@Override
		public boolean acceptHeat(float heat, Building src) {return false;}
	}
}