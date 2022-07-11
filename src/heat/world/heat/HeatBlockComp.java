package heat.world.heat;

import arc.graphics.Color;
import arc.util.Nullable;
import mindustry.gen.Building;
import heat.world.heat.*;

public interface HeatBlockComp {
	default HeatModule heatModule() {
		return null;
	}

	// manipulate heat
	default void addHeat(float heat, @Nullable Building build) {}
	default void addHeat(float heat) {addHeat(heat, null);}

	default void removeHeat(float heat, @Nullable Building build) {}
	default void removeHeat(float heat) {removeHeat(heat, null);}

	default void setHeat(float heat, @Nullable Building build) {}
	default void setHeat(float heat) {setHeat(heat, null);}

	// exchange heat
	default boolean acceptHeat(float heat, Building src) {
		return true;
	}
	default boolean outputHeat(float heat, Building src) {
		return true;
	}

	// for outside block reasons 
	default float[] heatRange() {return null;}

	// extra stuff
	default void drawHeat() {}
	default void overheat() {}
	default float heatf() {return 0f;}
	default float heatEfficiency() {return 1f;}
}