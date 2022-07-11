package heat.world.heat.production;

import arc.math.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.world.draw.*;
import heat.world.heat.*;
// mostly copied genericCrafter but that uses heat too
public class HeatedCrafter extends HeatBlock {
	public @Nullable ItemStack outputItem;
	public @Nullable ItemStack[] outputItems;

	public @Nullable LiquidStack outputLiquid;
	public @Nullable LiquidStack[] outputLiquids;

	public int[] liquidOutputDirections = {-1};

	public boolean 
	dumpExtraLiquid = true,
	ignoreLiquidFullness = false;

	public float 
	craftTime = 60,
	updateEffectChance = 0.04f,
	warmupSpeed = 0.019f,
	consumeHeat = 30f;

	public Effect
	craftEffect = Fx.none,
	updateEffect = Fx.none;

	public boolean legacyReadWarmup = false;

	public DrawBlock drawer = new DrawDefault();

	public HeatedCrafter(String name) {
		super(name);
		outputsHeat = false;
	}

	public class HeatedCrafterBuild extends HeatBlockBuild {
		public float progress;
		public float totalProgress;
		public float warmup;

		public float warmupTarget(){
			return 1f;
		}
		@Override
		public float warmup(){
			return warmup;
		}

		public void dumpOutputs(){
			if(outputItems != null && timer(timerDump, dumpTime / timeScale)){
				for(ItemStack output : outputItems){
					dump(output.item);
				}
			}

			if(outputLiquids != null){
				for(int i = 0; i < outputLiquids.length; i++){
					int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;

					dumpLiquid(outputLiquids[i].liquid, 2f, dir);
				}
			}
		}

		public void craft(){
			consume();

			if(outputItems != null){
				for(var output : outputItems){
					for(int i = 0; i < output.amount; i++){
						offload(output.item);
					}
				}
			}

			if(wasVisible){
				craftEffect.at(x, y);
			}
			progress %= 1f;
		}

		@Override
		public boolean shouldConsume(){
			if(outputItems != null){
				for(var output : outputItems){
					if(items.get(output.item) + output.amount > itemCapacity){
						return false;
					}
				}
			}
			if(outputLiquids != null && !ignoreLiquidFullness){
				boolean allFull = true;
				for(var output : outputLiquids){
					if(liquids.get(output.liquid) >= liquidCapacity - 0.001f){
						if(!dumpExtraLiquid){
							return false;
						}
					}else{
						allFull = false;
					}
				}

				if(allFull){
					return false;
				}
			}

			return enabled;
		}

		@Override
		public void updateTile(){
			if(efficiency > 0 && heatModule().heat > consumeHeat){

				progress += getProgressIncrease(craftTime);
				warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

				if(outputLiquids != null){
      		float inc = getProgressIncrease(1f);
      		for(var output : outputLiquids){
						handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
      		}
				}

				if(wasVisible && Mathf.chanceDelta(updateEffectChance)){
					updateEffect.at(x + Mathf.range(size * 4f), y + Mathf.range(size * 4));
				}
			} else {
				warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
			}

			totalProgress += warmup * Time.delta;

			if(progress >= 1f){
				craft();
			}

			dumpOutputs();

			super.updateTile();
		}
	}
}