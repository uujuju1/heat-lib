package heat.world.heat.production;

import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.world.draw.*;
import heat.world.heat.*;
// consumes stuff to get heat
public class HeatCrafter extends HeatBlock {
	public float outputHeat = 30,
	consumeSpeed = 60;

	public DrawBlock drawer = new DrawDefault();

	public HeatCrafter(String name) {
		super(name);
		acceptsHeat = false;
	}

	public class HeatCrafterBuild extends HeatBlockBuild {
		public float progress;
		public float totalProgress;
		public float warmup;

		@Override
		public void draw() {
			drawer.draw(this);
			Draw.color(Color.valueOF("F8C266"));
			Draw.alpha(heatf());
			drawHeat();
		}

		// yoinked from GenericCrafter cause Drawer reasons
		public float warmupTarget(){
			return 1f;
		}
		@Override
		public float warmup(){
			return warmup;
		}
		
		@Override
		public void updateTile() {
			if(efficiency > 0){
				progress += getProgressIncrease(consumeSpeed);
				warmup = Mathf.approachDelta(warmup, 1, warmupSpeed);
				setHeat(minheat + (warmup *outputHeat));
			} else {
				warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
				progress = Mathf.approachDelta(progress, 0f, warmupSpeed);
			}

			totalProgress += warmup * Time.delta;

			if(progress >= 1f){
				consume();
			}
		}
	}
}