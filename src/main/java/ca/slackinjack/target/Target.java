package ca.slackinjack.target;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "Target", acceptedMinecraftVersions = "1.8.9")
public class Target {

    private static Target INSTANCE;
    private final Target_ChatFormatting chatFormatting = new Target_ChatFormatting();
    private final Target_CommandHandler commandHandler;
    private final Target_EventHandler eventHandler;

    public Target() {
        INSTANCE = this;
        this.commandHandler = new Target_CommandHandler(this);
        this.eventHandler = new Target_EventHandler(this);
    }

    public static Target getInstance() {
        return INSTANCE;
    }

    @EventHandler
    public void onFMLPreInitializationEvent(FMLPreInitializationEvent e) {
        ClientCommandHandler.instance.registerCommand(this.commandHandler);
    }
    
    @EventHandler
    public void onFMLInitializationEvent(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this.eventHandler);
    }

    public Target_ChatFormatting getChatFormatting() {
        return this.chatFormatting;
    }
    
    public Target_EventHandler getEventHandler() {
        return this.eventHandler;
    }
}
