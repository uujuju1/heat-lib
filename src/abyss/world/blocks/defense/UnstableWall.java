package abyss.world.blocks.defense;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.entities.*;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.*;

public class UnstableWall extends Wall {
	public int damage = 69;
	public float damageRadius = 420f;
	public Effect explodeEffect = Fx.none;

	public UnstableWall(String name) {
		super(name);
	}

	@Override
	public void setStats() {
		super.setStats();
		stats.add(Stat.damage, damage);
		stats.add(Stat.range, damageRadius/8, StatUnit.blocks);
	}

	public class UnstableWallBuild extends WallBuild {
		@Override
		public void onDestroyed() {
			Damage.damage(this.team, x, y, damageRadius, damage);
			explodeEffect.at(x, y);
			super.onDestroyed();
		}
	}
}