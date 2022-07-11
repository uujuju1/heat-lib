package heat.world.heat.production;

import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.world.draw.*;
import heat.world.heat.*;
// consumes stuff to get heat
public class HeatCrafter extends HeatBlock {
	public float 
	heatOutput = 30,

	consumeSpeed = 60,

	warmupSpeed = 0.019f;

	public DrawBlock drawer = new DrawDefault();

	public HeatCrafter(String name) {
		super(name);
		acceptsHeat = false;
	}

	@Override
	public void load() {
		super.load();
		drawer.load(this);
	}

	public class HeatCrafterBuild extends HeatBlockBuild {
		public float progress;
		public float totalProgress;
		public float warmup;

		@Override
		public void draw() {
			drawer.draw(this);
			Draw.color(Color.valueOf("F8C266"));
			Draw.alpha(heatf() * 0.5f);
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
			} else {
				warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
				progress = Mathf.approachDelta(progress, 0f, warmupSpeed);
			}
			setHeat(minHeat + (warmup * heatOutput));

			totalProgress += warmup * Time.delta;

			if(progress >= 1f){
				consume();
				progress = 0;
			}

			super.updateTile();
		}
	}
}