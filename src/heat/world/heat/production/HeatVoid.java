package heat.world.heat.production;

import arc.util.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import heat.world.heat.*;

public class HeatVoid extends HeatBlock {
	public HeatSource(String name) {
		super(name);
		buildVisibility = BuildVisibility.shown;
		outputHeat = false;
	}

	public class HeatVoidBuild extends HeatBlockBuild {
		@Override
		public void updateTile() {
			setHeat(0, this);
		}

		@Override
		public boolean acceptHeat(float heat, Building src) {return false;}
	}
}