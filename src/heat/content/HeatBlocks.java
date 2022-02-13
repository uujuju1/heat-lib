package heat.content;

import mindustry.ctype.ContentList;
import mindustry.world.Block;
import heat.world.heat.production.*;
import heat.world.heat.distribution.*;

public class HeatBlocks implements ContentList {
	public static Block heatConveyor, heatSource;

	@Override
	public void load() {
		heatConveyor = new HeatConveyor("heat-conveyor") {{
			size = 1;
			health = 50;
			minHeat = 25;
			maxHeat = 1000;
		}};
		heatSource = new HeatSource("heat-souce") {{
			size = 1;
			health = 50
			minHeat = 25;
			maxHeat = 1000000;
		}};
	}
}