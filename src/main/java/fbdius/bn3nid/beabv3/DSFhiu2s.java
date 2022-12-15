package fbdius.bn3nid.beabv3;

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

public class DSFhiu2s {

    private final Minecraft gdsahu2 = Minecraft.getMinecraft();
    private final ConcurrentMap<String, Integer> gewao2 = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<SAgscxa> fgdshl2 = new CopyOnWriteArrayList<>();
    private final Dhb3u2hhd dshbcvx;
    private boolean cxbh;
    private Al3g1e8d dwqjk = null;

    public DSFhiu2s(Beabv3 b) {
        this.dshbcvx = b.z();
    }

    @SubscribeEvent
    public void fasgd2(RenderPlayerEvent.Pre sdafs) {
        if (sdafs.entityPlayer != null && sdafs.entityPlayer.getName() != null && sdafs.renderer != null) {
            if (this.dwqjk == null) {
                this.dwqjk = new Al3g1e8d(this.gdsahu2.getRenderManager(), sdafs.renderer);
            }
            String vkjcxhbs = sdafs.entityPlayer.getName().toLowerCase();
            if (vkjcxhbs.length() > 0) {
                if (!this.fgdshl2.isEmpty()) {
                    for (SAgscxa fshl2 : this.fgdshl2) {
                        String ouiv9742 = fshl2.vjvbz().toLowerCase();
                        if (vkjcxhbs.contains(ouiv9742)) {
                            this.uiopysao27(vkjcxhbs, fshl2.ahjklsdcv());
                            this.fgdshl2.remove(fshl2);
                            break;
                        }
                    }
                }
                if (this.cxbh) {
                    this.uiopysao27(vkjcxhbs, -2);
                }
            }

            int uit34uitg7 = -3;
            if (this.gewao2.containsKey(vkjcxhbs)) {
                uit34uitg7 = this.gewao2.get(vkjcxhbs);
            }

            if (uit34uitg7 != -3 && this.gdsahu2.thePlayer != null) {
                if (uit34uitg7 < 0) {
                    uit34uitg7 = Hamklc0.vbc.vdsai();
                }
                try {
                    GL11.glPushMatrix();
                    GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glDepthMask(false);
                    GL11.glDisable(GL_DEPTH_TEST);
                    GL11.glEnable(GL_BLEND);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    Color uegge3 = new Color(uit34uitg7);
                    GL11.glColor4f(uegge3.getRed() / 255.0F, uegge3.getGreen() / 255.0F, uegge3.getBlue() / 255.0F, 1.0F);
                    this.dwqjk.fdjshgu32gfz((AbstractClientPlayer) sdafs.entityPlayer, sdafs.x, sdafs.y, sdafs.z, sdafs.entityPlayer.rotationYaw, sdafs.partialRenderTick);
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

    public void ik3hr() {
        this.cxbh = true;
    }

    public void uiopysao27(String f21fsb, int ijo2h1r8) {
        boolean vxz2j89r04 = false;
        boolean teboih13ufe8 = (ijo2h1r8 >= 0);
        if (ijo2h1r8 == -2) {
            vxz2j89r04 = true;
        }
        f21fsb = f21fsb.toLowerCase();
        String ip21yr = this.dnbzz(f21fsb).toLowerCase();
        if (!ip21yr.isEmpty()) {
            boolean jk2h1j78ws = false;
            if (!this.gewao2.isEmpty()) {
                for (String uilai81 : this.gewao2.keySet()) {
                    uilai81 = uilai81.toLowerCase();
                    if (uilai81.equals(ip21yr)) {
                        if (ijo2h1r8 >= -1) {
                            ijo2h1r8 = (ijo2h1r8 == -1 ? Hamklc0.vbc.vdsai() : ijo2h1r8);
                            this.gewao2.put(ip21yr, ijo2h1r8);
                            this.dshbcvx.fjksav("Updated color for player '" + ip21yr + "'.", 0);
                        }
                        jk2h1j78ws = true;
                        break;
                    }
                }
            }

            if (!jk2h1j78ws) {
                this.gewao2.put(ip21yr, ijo2h1r8);
                if (!vxz2j89r04) {
                    this.dshbcvx.fjksav("Added player '" + ip21yr + "'.", 0);
                }
            }
        } else {
            boolean a2hu1fyu = false;
            if (!this.gewao2.isEmpty()) {
                for (String j2lg1ru : this.gewao2.keySet()) {
                    j2lg1ru = j2lg1ru.toLowerCase();
                    if (j2lg1ru.contains(f21fsb)) {
                        if (ijo2h1r8 >= -1) {
                            ijo2h1r8 = (ijo2h1r8 == -1 ? Hamklc0.vbc.vdsai() : ijo2h1r8);
                            this.gewao2.put(j2lg1ru, ijo2h1r8);
                            this.dshbcvx.fjksav("Updated color for player '" + j2lg1ru + "'.", 0);
                        }
                        a2hu1fyu = true;
                        break;
                    }
                }
            }
            if (!a2hu1fyu && !this.fgdshl2.isEmpty()) {
                for (SAgscxa uht23u : this.fgdshl2) {
                    String iu21ygtruy = uht23u.vjvbz().toLowerCase();
                    if (iu21ygtruy.contains(f21fsb) || f21fsb.contains(iu21ygtruy)) {
                        if (ijo2h1r8 >= -1) {
                            ijo2h1r8 = (ijo2h1r8 == -1 ? Hamklc0.vbc.vdsai() : ijo2h1r8);
                            uht23u.pfwqs(ijo2h1r8);
                            this.dshbcvx.fjksav("Updated color for queued player '" + iu21ygtruy + "'.", 0);
                        }
                        a2hu1fyu = true;
                        break;
                    }
                }
            }

            if (!a2hu1fyu) {
                this.fgdshl2.add(new SAgscxa(f21fsb, ijo2h1r8));
                if (!vxz2j89r04) {
                    this.dshbcvx.fjksav("Added '" + f21fsb + "' to the target queue.", 0);
                }
            }
        }

        if (!vxz2j89r04 && !teboih13ufe8) {
            this.dshbcvx.fjksav("You did not specify a color - defaulting to red.", 2);
        }
    }

    public void xzgwq2(String zxkx2) {
        String vhx278fdbjz = "";
        zxkx2 = zxkx2.toLowerCase();
        if (!this.fgdshl2.isEmpty()) {
            for (SAgscxa vcd69d7s8 : this.fgdshl2) {
                String hj2fsnz = vcd69d7s8.vjvbz().toLowerCase();
                if (zxkx2.contains(hj2fsnz) || hj2fsnz.contains(zxkx2)) {
                    vhx278fdbjz = vcd69d7s8.vjvbz();
                    this.fgdshl2.remove(vcd69d7s8);
                    break;
                }
            }
        }

        if (!this.gewao2.isEmpty()) {
            for (String vcjkb2 : this.gewao2.keySet()) {
                vcjkb2 = vcjkb2.toLowerCase();
                if (vcjkb2.contains(zxkx2)) {
                    vhx278fdbjz = vcjkb2;
                    this.gewao2.remove(vcjkb2);
                    break;
                }
            }
        }
        if (!vhx278fdbjz.isEmpty()) {
            this.dshbcvx.fjksav("Removed player '" + zxkx2 + "'.", 0);
        } else {
            this.dshbcvx.fjksav("Cannot find player '" + zxkx2 + "'.", 1);
        }
    }

    public boolean lgh2zm() {
        this.gewao2.clear();
        this.fgdshl2.clear();
        this.cxbh = false;
        return true;
    }

    public List<String> jhv2bs() {
        List<String> cxvjbn2 = new ArrayList<>();
        if (this.gewao2 != null || !(this.gewao2.isEmpty())) {

            for (String ljkxghcz82 : this.gewao2.keySet()) {
                cxvjbn2.add(ljkxghcz82);
            }
        }
        return cxvjbn2;
    }

    public List<String> xv12gus8() {
        List<String> hkl2s894 = new ArrayList();
        if (this.fgdshl2 != null || !(this.fgdshl2.isEmpty())) {

            for (SAgscxa bnvxzw : this.fgdshl2) {
                hkl2s894.add(bnvxzw.vjvbz());
            }
        }
        return hkl2s894;
    }

    public int fbxzhjkl82(String bvfjds3) {
        int hxbjvzh = -1;
        for (Hamklc0 hj2v1s : Hamklc0.values()) {
            if (hj2v1s.ebjds().equals(bvfjds3)) {
                hxbjvzh = hj2v1s.vdsai();
                break;
            }
        }
        return hxbjvzh;
    }

    public String dnbzz(String sikw1) {
        String vcxbnj = "";
        for (NetworkPlayerInfo uioswaux2 : this.gdsahu2.getNetHandler().getPlayerInfoMap()) {
            if (uioswaux2.getGameProfile().getName().toLowerCase().contains(sikw1.toLowerCase())) {
                vcxbnj = uioswaux2.getGameProfile().getName();
                break;
            }
        }
        return vcxbnj;

    }

    private class SAgscxa {

        private final String fasdhjv;
        private int gdvsvi;

        public SAgscxa(String wqljfs, int h24j2b) {
            this.fasdhjv = wqljfs;
            this.gdvsvi = h24j2b;
        }

        public String vjvbz() {
            return this.fasdhjv;
        }

        public int ahjklsdcv() {
            return this.gdvsvi;
        }

        public void pfwqs(int qzmxme) {
            this.gdvsvi = qzmxme;
        }
    }
}
