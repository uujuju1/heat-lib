package heat.content;

import mindustry.content.*;
import mindustry.world.Block;
import heat.world.heat.production.*;
import heat.world.heat.distribution.*;

import static mindustry.type.ItemStack.*;

public class HeatBlocks {
	public static Block heatConveyor, burner, heatSource, heatVoid;

	public void load() {
		heatConveyor = new HeatConveyor("heat-conveyor") {{
			size = 1;
			health = 50;
			minHeat = 25f;
			maxHeat = 1000f;
		}};
		burner = new HeatCrafter("burner") {{
			size = 3;
			health = 200;
			minHeat = 25f;
			maxHeat = 300f;
			consumeSpeed = 60;
			consumeItems(with(Items.coal, 1));
			outputHeat = 250f;
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