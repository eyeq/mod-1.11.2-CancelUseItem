package eyeq.canceluseitem.event;

import eyeq.canceluseitem.CancelUseItem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class CancelUseItemEventHandler {
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        EnumFacing face = event.getFace();
        switch(CancelUseItem.mode) {
        case NORMAL:
            break;
        case TOP_BOTTOM:
            if(face == EnumFacing.DOWN || face == EnumFacing.UP) {
                event.setCanceled(true);
            }
            break;
        case SIDE:
            if(face == EnumFacing.NORTH || face == EnumFacing.SOUTH || face == EnumFacing.WEST || face == EnumFacing.EAST) {
                event.setCanceled(true);
            }
            break;
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if(minecraft.currentScreen != null) {
            return;
        }
        if(CancelUseItem.cancelKey.isPressed()) {
            ITextComponent text = null;
            switch(CancelUseItem.mode) {
            case NORMAL:
                CancelUseItem.mode = CancelUseItem.Mode.TOP_BOTTOM;
                text = new TextComponentString("Cancel TopBottom Mode");
                break;
            case TOP_BOTTOM:
                CancelUseItem.mode = CancelUseItem.Mode.SIDE;
                text = new TextComponentString("Cancel Side Mode");
                break;
            case SIDE:
                CancelUseItem.mode = CancelUseItem.Mode.NORMAL;
                text = new TextComponentString("Not Cancel Mode");
                break;
            }
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
        }
    }
}
