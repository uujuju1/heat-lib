package heat.world.heat.production;

import arc.util.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import heat.world.heat.*;

public class HeatVoid extends HeatBlock {
	public HeatVoid(String name) {
		super(name);
		outputsHeat = false;
	}

	public class HeatVoidBuild extends HeatBlockBuild {
		@Override
		public void updateTile() {
			setHeat(0, this);
		}
	}
}