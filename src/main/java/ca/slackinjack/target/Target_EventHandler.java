package ca.slackinjack.target;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

public class Target_EventHandler {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final ConcurrentMap<String, Integer> targetedPlayers = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<QueuedPlayer> targetQueue = new CopyOnWriteArrayList<>();
    private final Target_ChatFormatting chatFormatting;
    private boolean autoUpdater;
    private Target_RendererLivingEntity targetRenderer = null;

    public Target_EventHandler(Target targetIn) {
        this.chatFormatting = targetIn.getChatFormatting();
    }

    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        if (event.entityPlayer != null && event.entityPlayer.getName() != null && event.renderer != null) {
            if (this.targetRenderer == null) {
                this.targetRenderer = new Target_RendererLivingEntity(this.mc.getRenderManager(), event.renderer);
            }
            String playerName = event.entityPlayer.getName().toLowerCase();
            if (playerName.length() > 0) {
                if (!this.targetQueue.isEmpty()) {
                    for (QueuedPlayer fshl2 : this.targetQueue) {
                        String queuedPlayerName = fshl2.getPartialName().toLowerCase();
                        if (playerName.contains(queuedPlayerName)) {
                            this.addTarget(playerName, fshl2.ahjklsdcv());
                            this.targetQueue.remove(fshl2);
                            break;
                        }
                    }
                }
                if (this.autoUpdater) {
                    this.addTarget(playerName, -2);
                }
            }

            int colourToUse = -3;
            if (this.targetedPlayers.containsKey(playerName)) {
                colourToUse = this.targetedPlayers.get(playerName);
            }

            if (colourToUse != -3 && this.mc.thePlayer != null) {
                if (colourToUse < 0) {
                    colourToUse = Target_ColoursEnum.RED.getColourRGB();
                }
                try {
                    GL11.glPushMatrix();
                    GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glDepthMask(false);
                    GL11.glDisable(GL_DEPTH_TEST);
                    GL11.glEnable(GL_BLEND);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    Color c = new Color(colourToUse);
                    GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
                    this.targetRenderer.doRenderOuter((AbstractClientPlayer) event.entityPlayer, event.x, event.y, event.z, event.entityPlayer.rotationYaw, event.partialRenderTick);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glDisable(GL_BLEND);
                    GL11.glEnable(GL_DEPTH_TEST);
                    GL11.glDepthMask(true);
                    GL11.glPopMatrix();
                } catch (Exception e) {
                }
            }
        }
    }

    public void enableAutoUpdater() {
        this.autoUpdater = true;
    }

    public void addTarget(String queryIn, int colourIn) {
        boolean autoTarget = false;
        boolean isColourSpecified = (colourIn >= 0);
        if (colourIn == -2) {
            autoTarget = true;
        }
        queryIn = queryIn.toLowerCase();
        String matchedTargetPlayerName = this.getPlayerNameFromNPI(queryIn).toLowerCase();
        if (!matchedTargetPlayerName.isEmpty()) {
            boolean existingPlayer = false;
            if (!this.targetedPlayers.isEmpty()) {
                for (String existingTargetName : this.targetedPlayers.keySet()) {
                    existingTargetName = existingTargetName.toLowerCase();
                    if (existingTargetName.equals(matchedTargetPlayerName)) {
                        if (colourIn >= -1) {
                            colourIn = (colourIn == -1 ? Target_ColoursEnum.RED.getColourRGB() : colourIn);
                            this.targetedPlayers.put(matchedTargetPlayerName, colourIn);
                            this.chatFormatting.addChatMessage("Updated color for player '" + matchedTargetPlayerName + "'.", 0);
                        }
                        existingPlayer = true;
                        break;
                    }
                }
            }

            if (!existingPlayer) {
                this.targetedPlayers.put(matchedTargetPlayerName, colourIn);
                if (!autoTarget) {
                    this.chatFormatting.addChatMessage("Added player '" + matchedTargetPlayerName + "'.", 0);
                }
            }
        } else {
            boolean existingPlayer = false;
            if (!this.targetedPlayers.isEmpty()) {
                for (String targetedPlayerName : this.targetedPlayers.keySet()) {
                    targetedPlayerName = targetedPlayerName.toLowerCase();
                    if (targetedPlayerName.contains(queryIn)) {
                        if (colourIn >= -1) {
                            colourIn = (colourIn == -1 ? Target_ColoursEnum.RED.getColourRGB() : colourIn);
                            this.targetedPlayers.put(targetedPlayerName, colourIn);
                            this.chatFormatting.addChatMessage("Updated color for player '" + targetedPlayerName + "'.", 0);
                        }
                        existingPlayer = true;
                        break;
                    }
                }
            }
            if (!existingPlayer && !this.targetQueue.isEmpty()) {
                for (QueuedPlayer queuedPlayerName : this.targetQueue) {
                    String queuedPartialName = queuedPlayerName.getPartialName().toLowerCase();
                    if (queuedPartialName.contains(queryIn) || queryIn.contains(queuedPartialName)) {
                        if (colourIn >= -1) {
                            colourIn = (colourIn == -1 ? Target_ColoursEnum.RED.getColourRGB() : colourIn);
                            queuedPlayerName.setColour(colourIn);
                            this.chatFormatting.addChatMessage("Updated color for queued player '" + queuedPartialName + "'.", 0);
                        }
                        existingPlayer = true;
                        break;
                    }
                }
            }

            if (!existingPlayer) {
                this.targetQueue.add(new QueuedPlayer(queryIn, colourIn));
                if (!autoTarget) {
                    this.chatFormatting.addChatMessage("Added '" + queryIn + "' to the target queue.", 0);
                }
            }
        }

        if (!autoTarget && !isColourSpecified) {
            this.chatFormatting.addChatMessage("You did not specify a color - defaulting to red.", 2);
        }
    }

    public void removeTarget(String queryIn) {
        String removedPlayerName = "";
        queryIn = queryIn.toLowerCase();
        if (!this.targetQueue.isEmpty()) {
            for (QueuedPlayer queuedPlayer : this.targetQueue) {
                String queuedTargetName = queuedPlayer.getPartialName().toLowerCase();
                if (queryIn.contains(queuedTargetName) || queuedTargetName.contains(queryIn)) {
                    removedPlayerName = queuedPlayer.getPartialName();
                    this.targetQueue.remove(queuedPlayer);
                    break;
                }
            }
        }

        if (!this.targetedPlayers.isEmpty()) {
            for (String targetedPlayerName : this.targetedPlayers.keySet()) {
                targetedPlayerName = targetedPlayerName.toLowerCase();
                if (targetedPlayerName.contains(queryIn)) {
                    removedPlayerName = targetedPlayerName;
                    this.targetedPlayers.remove(targetedPlayerName);
                    break;
                }
            }
        }
        if (!removedPlayerName.isEmpty()) {
            this.chatFormatting.addChatMessage("Removed player '" + queryIn + "'.", 0);
        } else {
            this.chatFormatting.addChatMessage("Cannot find player '" + queryIn + "'.", 1);
        }
    }

    public boolean clearLists() {
        this.targetedPlayers.clear();
        this.targetQueue.clear();
        this.autoUpdater = false;
        return true;
    }

    public List<String> getTargettedNamesAsList() {
        List<String> nameList = new ArrayList<>();
        if (this.targetedPlayers != null || !(this.targetedPlayers.isEmpty())) {

            for (String playerName : this.targetedPlayers.keySet()) {
                nameList.add(playerName);
            }
        }
        return nameList;
    }

    public List<String> getQueuedNamesAsList() {
        List<String> nameList = new ArrayList();
        if (this.targetQueue != null || !(this.targetQueue.isEmpty())) {

            for (QueuedPlayer playerName : this.targetQueue) {
                nameList.add(playerName.getPartialName());
            }
        }
        return nameList;
    }

    public int findColourRGBFromName(String colourNameIn) {
        int result = -1;
        for (Target_ColoursEnum e : Target_ColoursEnum.values()) {
            if (e.getColourName().equals(colourNameIn)) {
                result = e.getColourRGB();
                break;
            }
        }
        return result;
    }

    public String getPlayerNameFromNPI(String stringIn) {
        String result = "";
        for (NetworkPlayerInfo npi : this.mc.getNetHandler().getPlayerInfoMap()) {
            if (npi.getGameProfile().getName().toLowerCase().contains(stringIn.toLowerCase())) {
                result = npi.getGameProfile().getName();
                break;
            }
        }
        return result;

    }

    private class QueuedPlayer {

        private final String playerName;
        private int playerColour;

        public QueuedPlayer(String playerNameIn, int playerColourIn) {
            this.playerName = playerNameIn;
            this.playerColour = playerColourIn;
        }

        public String getPartialName() {
            return this.playerName;
        }

        public int ahjklsdcv() {
            return this.playerColour;
        }

        public void setColour(int colourIn) {
            this.playerColour = colourIn;
        }
    }
}
