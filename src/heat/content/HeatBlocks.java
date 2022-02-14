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
			minHeat = 25f;
			maxHeat = 1000f;
		}};
		heatSource = new HeatSource("heat-source") {{
			size = 1;
			health = 50;
			minHeat = 25f;
			maxHeat = 1000000f;
		}};
	}
}