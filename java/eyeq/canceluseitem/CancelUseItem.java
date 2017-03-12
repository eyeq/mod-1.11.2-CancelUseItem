package eyeq.canceluseitem;

import eyeq.canceluseitem.event.CancelUseItemEventHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import static eyeq.canceluseitem.CancelUseItem.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class CancelUseItem {
    public static final String MOD_ID = "eyeq_canceluseitem";

    @Mod.Instance(MOD_ID)
    public static CancelUseItem instance;

    public enum Mode {
        NORMAL,
        TOP_BOTTOM,
        SIDE,
        ;
    }

    public static KeyBinding cancelKey = new KeyBinding("key.cancel", Keyboard.KEY_I, "CancelUseItem");

    public static Mode mode = Mode.NORMAL;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new CancelUseItemEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(cancelKey);
    }
	
    public static void load(Configuration config) {
        config.load();

        String category = "Int";
        int key = config.get(category, "key", cancelKey.getKeyCode()).getInt();
        cancelKey.setKeyCode(key);

        if(config.hasChanged()) {
            config.save();
        }
    }
}
