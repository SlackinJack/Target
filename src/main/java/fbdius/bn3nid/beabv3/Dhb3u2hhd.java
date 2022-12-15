package fbdius.bn3nid.beabv3;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Dhb3u2hhd {

    private final Minecraft k21six = Minecraft.getMinecraft();

    public void fjksav(String grbeila, int r32iurbfd) {
        if (this.k21six.thePlayer != null) {
            EnumChatFormatting fd9vns = null;
            switch (r32iurbfd) {
                case 0:
                    fd9vns = EnumChatFormatting.GREEN;
                    break;
                case 1:
                    fd9vns = EnumChatFormatting.RED;
                    break;
                case 2:
                    fd9vns = EnumChatFormatting.GOLD;
                    break;
            }
            if (fd9vns != null) {
                this.k21six.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "[T] " + fd9vns + grbeila));
            }
        }
    }
}
