package heat.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.draw.*;
import heat.world.heat.production.*;
import heat.world.heat.distribution.*;

import static mindustry.type.ItemStack.*;

public class HeatBlocks {
	public static Block heatConveyor, burner, heatedSmelter, heatSource, heatVoid;

	public void load() {
		heatConveyor = new HeatConveyor("heat-conveyor") {{
			size = 1;
			health = 50;
			minHeat = 25f;
			maxHeat = 350f;
		}};
		burner = new HeatCrafter("burner") {{
			size = 3;
			health = 200;
			minHeat = 25f;
			maxHeat = 350f;
			consumeSpeed = 150f;
			drawer = new DrawMulti(new DrawDefault(), new DrawArcSmelt() {{
				flameColor = midColor = Color.valueOf("F8C266");
			}});
			consumeItems(with(Items.coal, 1));
			heatOutput = 300f;
		}};
		heatedSmelter = new HeatedCrafter("heated-smelter") {{
			size = 3;
			health = 200;
			minHeat = 25f;
			maxHeat = 900f;
			craftTime = 60f;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawArcSmelt() {{
					flameColor = midColor = Color.valueOf("F8C266");
				}},
				new DrawDefault()
			);
			consumeItems(with(
				Items.coal, 3,
				Items.sand, 6
			));
			consumeHeat = 150;
			outputItem = new ItemStack(Items.silicon, 5);
			outputLiquid = new LiquidStack(Liquids.slag, 0.1f);
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