package dev.draylar.descent;

import dev.draylar.descent.registry.EDEntities;
import dev.draylar.descent.registry.EDItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class EchoingDepths implements ModInitializer {

    @Override
    public void onInitialize() {
        EDItems.register();
        EDEntities.register();
    }

    public static Identifier id(String id) {
        return new Identifier("echoingdepths", id);
    }
}
