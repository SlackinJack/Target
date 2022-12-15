package fbdius.bn3nid.beabv3;

import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public final class Al3g1e8d<grueis extends EntityLivingBase> extends Render<grueis> {

    private static final DynamicTexture gsdhg3 = new DynamicTexture(16, 16);
    protected ModelBase ut123ip;
    protected FloatBuffer jgfd8gfds = GLAllocation.createDirectFloatBuffer(4);

    public Al3g1e8d(RenderManager gfdsup, RenderPlayer sagu3) {
        this(gfdsup, sagu3, new ModelPlayer(0.0F, false), 0.5F);
    }

    public Al3g1e8d(RenderManager grui23, RenderPlayer t3p2u, ModelPlayer jhf89ds, float fds712) {
        super(grui23);
        this.ut123ip = jhf89ds;
        this.shadowSize = fds712;
    }

    protected float bhfj(float a2gih1, float u21y, float jhfg84) {
        float f;

        for (f = u21y - a2gih1; f < -180.0F; f += 360.0F) {
            ;
        }

        while (f >= 180.0F) {
            f -= 360.0F;
        }

        return a2gih1 + jhfg84 * f;
    }

    public void fdjshgu32gfz(grueis iuo132, double disoi, double hgn8sp, double jkds8agj, float hdsg, float nhni2) {
        float vhioe = 1.0F;
        AbstractClientPlayer vlca = (AbstractClientPlayer) iuo132;
        if (!vlca.isUser() || this.renderManager.livingPlayer == iuo132) {
            double d0 = hgn8sp;

            if (iuo132.isSneaking() && !(iuo132 instanceof EntityPlayerSP)) {
                d0 = hgn8sp - 0.125D;
            }

            this.bni83((AbstractClientPlayer) vlca);
            iuo132.setAlwaysRenderNameTag(false);
            this.fdhgsi(iuo132, disoi, (d0 - 0.1D), jkds8agj, hdsg, nhni2, vhioe);
        }
    }

    private void fdhgsi(grueis fdj, double zs, double a2hj, double bgfjdbn34, float gfjd, float vnk2, float gkf98er) {
        EntityPlayer htru3 = (EntityPlayer) fdj;
        if (htru3 != null) {
            if (!htru3.isUser() || this.renderManager.livingPlayer == fdj) {
                double bjn = a2hj;

                if (fdj.isSneaking() && !(fdj instanceof EntityPlayerSP)) {
                    bjn = a2hj - 0.125D;
                }

                this.bni83((AbstractClientPlayer) htru3);
                fdj.setAlwaysRenderNameTag(false);
                GlStateManager.pushMatrix();
                GlStateManager.disableCull();
                this.ut123ip.swingProgress = fdj.getSwingProgress(vnk2);
                this.ut123ip.isRiding = fdj.isRiding();
                this.ut123ip.isChild = fdj.isChild();

                try {
                    float dfg = this.bhfj(fdj.prevRenderYawOffset, fdj.renderYawOffset, vnk2);
                    float ff2hm = this.bhfj(fdj.prevRotationYawHead, fdj.rotationYawHead, vnk2);
                    float kxmel = ff2hm - dfg;

                    if (fdj.isRiding() && fdj.ridingEntity instanceof EntityLivingBase) {
                        EntityLivingBase hgf9832 = (EntityLivingBase) fdj.ridingEntity;
                        dfg = this.bhfj(hgf9832.prevRenderYawOffset, hgf9832.renderYawOffset, vnk2);
                        kxmel = ff2hm - dfg;
                        float nbnkf = MathHelper.wrapAngleTo180_float(kxmel);

                        if (nbnkf < -85.0F) {
                            nbnkf = -85.0F;
                        }

                        if (nbnkf >= 85.0F) {
                            nbnkf = 85.0F;
                        }

                        dfg = ff2hm - nbnkf;

                        if (nbnkf * nbnkf > 2500.0F) {
                            dfg += nbnkf * 0.2F;
                        }
                    }

                    float djosd = fdj.prevRotationPitch + (fdj.rotationPitch - fdj.prevRotationPitch) * vnk2;
                    GlStateManager.translate((float) zs, (float) bjn, (float) bgfjdbn34);
                    float bvue = (float) fdj.ticksExisted + vnk2;
                    this.bxi(fdj, bvue, dfg, vnk2);
                    GlStateManager.enableRescaleNormal();
                    GlStateManager.scale(-gkf98er, -gkf98er, gkf98er);
                    GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
                    float gfdsgr34t4 = fdj.prevLimbSwingAmount + (fdj.limbSwingAmount - fdj.prevLimbSwingAmount) * vnk2;
                    float gfug3 = fdj.limbSwing - fdj.limbSwingAmount * (1.0F - vnk2);

                    if (fdj.isChild()) {
                        gfug3 *= 3.0F;
                    }

                    if (gfdsgr34t4 > 1.0F) {
                        gfdsgr34t4 = 1.0F;
                    }

                    GlStateManager.enableAlpha();
                    this.ut123ip.setLivingAnimations(fdj, gfug3, gfdsgr34t4, vnk2);
                    this.ut123ip.setRotationAngles(gfug3, gfdsgr34t4, bvue, kxmel, djosd, 0.0625F, fdj);
                    this.fdsk(fdj, gfug3, gfdsgr34t4, bvue, kxmel, djosd, 0.0625F);
                    GlStateManager.depthMask(true);
                    GlStateManager.disableRescaleNormal();
                } catch (Exception g4h) {
                }

                GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
                GlStateManager.enableTexture2D();
                GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
                GlStateManager.enableCull();
                GlStateManager.popMatrix();
                //super.doRender(entity, x, d0, z, entityYaw, partialTicks);
            }
        }
    }

    protected void fdsk(grueis dfsjavdshbv, float sajda, float e21s, float jhsja, float fh38h, float adsh, float xca) {
        boolean na = !dfsjavdshbv.isInvisible();
        boolean zxlj = !na && !dfsjavdshbv.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer);

        if (na || zxlj) {
            if (!this.bindEntityTexture(dfsjavdshbv)) {
                return;
            }

            if (zxlj) {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
                GlStateManager.alphaFunc(516, 0.003921569F);
            }

            this.ut123ip.render(dfsjavdshbv, sajda, e21s, jhsja, fh38h, adsh, xca);

            if (zxlj) {
                GlStateManager.disableBlend();
                GlStateManager.alphaFunc(516, 0.1F);
                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
            }
        }
    }

    protected void bxi(grueis aos, float bf9j, float sj2, float j23) {
        GlStateManager.rotate(180.0F - sj2, 0.0F, 1.0F, 0.0F);

        if (aos.deathTime > 0) {
            float vchxj = ((float) aos.deathTime + j23 - 1.0F) / 20.0F * 1.6F;
            vchxj = MathHelper.sqrt_float(vchxj);

            if (vchxj > 1.0F) {
                vchxj = 1.0F;
            }

            GlStateManager.rotate(vchxj * 90.0F, 0.0F, 0.0F, 1.0F);
        } else {
            String cnm = EnumChatFormatting.getTextWithoutFormattingCodes(aos.getName());

            if (cnm != null && (cnm.equals("Dinnerbone") || cnm.equals("Grumm")) && (!(aos instanceof EntityPlayer) || ((EntityPlayer) aos).isWearing(EnumPlayerModelParts.CAPE))) {
                GlStateManager.translate(0.0F, aos.height + 0.1F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }

    private void bni83(AbstractClientPlayer ihr23ug) {
        ModelPlayer opd1hu2 = (ModelPlayer) this.ut123ip;

        opd1hu2.bipedHeadwear.showModel = false;
        opd1hu2.bipedBodyWear.showModel = false;
        opd1hu2.bipedLeftLegwear.showModel = false;
        opd1hu2.bipedRightLegwear.showModel = false;
        opd1hu2.bipedLeftArmwear.showModel = false;
        opd1hu2.bipedRightArmwear.showModel = false;

        if (ihr23ug.isSpectator()) {
            opd1hu2.setInvisible(false);
        } else {
            ItemStack bcvbhe = ihr23ug.inventory.getCurrentItem();
            opd1hu2.setInvisible(true);
            opd1hu2.heldItemLeft = 0;
            opd1hu2.aimedBow = false;
            opd1hu2.isSneak = ihr23ug.isSneaking();

            if (bcvbhe == null) {
                opd1hu2.heldItemRight = 0;
            } else {
                opd1hu2.heldItemRight = 1;

                if (ihr23ug.getItemInUseCount() > 0) {
                    EnumAction cvhd = bcvbhe.getItemUseAction();

                    if (cvhd == EnumAction.BLOCK) {
                        opd1hu2.heldItemRight = 3;
                    } else if (cvhd == EnumAction.BOW) {
                        opd1hu2.aimedBow = true;
                    }
                }
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(grueis cxba) {
        return ((AbstractClientPlayer) cxba).getLocationSkin();
    }
}
