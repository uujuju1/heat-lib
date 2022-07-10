package heat.content;

import mindustry.world.Block;
import heat.world.heat.production.*;
import heat.world.heat.distribution.*;

public class HeatBlocks {
	public static Block heatConveyor, heatSource, heatVoid;

	@Override
	public void load() {
		heatConveyor = new HeatConveyor("heat-conveyor") {{
			size = 1;
			health = 50;
			minHeat = 25f;
			maxHeat = 1000f;
		}};
		heatSource = new HeatSource("heat-source") {{
			size = 1;
			health = 50;
			minHeat = 25f;
			maxHeat = 1000000f;
		}};
		heatVoid = new HeatVoid("heat-void") {{
			size = 1;
			health = 50;
			minHeat = 25f;
			maxHeat = 1000000f;
		}};
	}
}