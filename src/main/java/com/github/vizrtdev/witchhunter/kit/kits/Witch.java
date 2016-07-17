package com.github.vizrtdev.witchhunter.kit.kits;

import com.github.vizrtdev.witchhunter.kit.Kit;
import com.github.vizrtdev.witchhunter.kit.items.ThunderbolterItem;
import com.github.vizrtdev.witchhunter.kit.items.TornadoItem;
import com.github.vizrtdev.witchhunter.kit.items.WallItem;

public class Witch extends Kit {
    public Witch( ) {
        super( new TornadoItem(  ), new ThunderbolterItem(), new WallItem() );
    }
}
