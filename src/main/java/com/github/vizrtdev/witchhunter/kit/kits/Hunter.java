package com.github.vizrtdev.witchhunter.kit.kits;

import com.github.vizrtdev.witchhunter.kit.Kit;
import com.github.vizrtdev.witchhunter.kit.items.BowItem;
import com.github.vizrtdev.witchhunter.kit.items.RegenerationItem;
import com.github.vizrtdev.witchhunter.kit.items.WebItem;

public class Hunter extends Kit {
    public Hunter( ) {
        super( new BowItem(), new WebItem(), new RegenerationItem() );
    }
}
