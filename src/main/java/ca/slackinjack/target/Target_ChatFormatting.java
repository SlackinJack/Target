package ca.slackinjack.target;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Target_ChatFormatting {

    private final Minecraft mc = Minecraft.getMinecraft();

    public void addChatMessage(String stringIn, int colourIn) {
        if (this.mc.thePlayer != null) {
            EnumChatFormatting e = null;
            switch (colourIn) {
                case 0:
                    e = EnumChatFormatting.GREEN;
                    break;
                case 1:
                    e = EnumChatFormatting.RED;
                    break;
                case 2:
                    e = EnumChatFormatting.GOLD;
                    break;
            }
            if (e != null) {
                this.mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "[T] " + e + stringIn));
            }
        }
    }
}
