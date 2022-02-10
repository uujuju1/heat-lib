package heat;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import heat.content.*;

public class HeatLib extends Mod{

	public HeatLib() {
		Log.info("amogus");
	}

	@Override
	public void loadContent(){
		new HeatBlocks().load();
	}
}
