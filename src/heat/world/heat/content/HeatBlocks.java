package heat.content;

import mindustry.ctype.ContentList;
import heat.world.heat.distribution.*;

public class HeatBlocks implements ContentList {
	public static Block heatConveyor;

	@Override
	public void load() {
		heatConveyor = new HeatConveyor("heat-conveyor") {{
			size = 1;
			health = 50;
			minHeat = 25;
			maxHeat = 1000;
		}};
	}
}