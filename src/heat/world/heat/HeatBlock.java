package heat.world.heat;

import mindustry.gen.*;
import mindustry.world.meta.*;
import mindustry.world.Block;

public class HeatBlock extends Block {
	// public Seq<DrawHeat> drawers = new Seq<>();
	public float minHeat, maxHeat;

	public HeatBlock(String name) {
		super(name);
	}

	public class HeatBlockBuild extends Building implements HeatBlockComp {
		public HeatModule heat = new HeatModule();

		@Override
		public void heatModule() {
			return heat;
		}

		@Override
		public void overheat() {
			if (heat.heat < minHeat) {
				setHeat(minHeat, this);
			}
			if (heat.heat > maxHeat) {
				kill();
			}
		}

		@Override
		public float heatf() {
			return heat.heat/maxHeat;
		}
	}
}