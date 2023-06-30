package ca.slackinjack.target;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public final class Target_RendererLivingEntity<T extends EntityLivingBase> extends Render<T> {

    protected ModelBase mainModel;
    protected FloatBuffer brightnessBuffer = GLAllocation.createDirectFloatBuffer(4);

    public Target_RendererLivingEntity(RenderManager renderManager, RenderPlayer renderPlayer) {
        this(renderManager, renderPlayer, new ModelPlayer(0.0F, false), 0.5F);
    }

    public Target_RendererLivingEntity(RenderManager renderManager, RenderPlayer t3p2u, ModelPlayer modelPlayerIn, float shadowSizeIn) {
        super(renderManager);
        this.mainModel = modelPlayerIn;
        this.shadowSize = shadowSizeIn;
    }

    protected float interpolateRotation(float par1, float par2, float par3) {
        float f;

        for (f = par2 - par1; f < -180.0F; f += 360.0F) {
            ;
        }

        while (f >= 180.0F) {
            f -= 360.0F;
        }

        return par1 + par3 * f;
    }

    public void doRenderOuter(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float scale = 1.0F;
        AbstractClientPlayer thePlayer = (AbstractClientPlayer) entity;
        if (!thePlayer.isUser() || this.renderManager.livingPlayer == entity) {
            double d0 = y;

            if (entity.isSneaking() && !(entity instanceof EntityPlayerSP)) {
                d0 = y - 0.125D;
            }

            this.setModelVisibilities((AbstractClientPlayer) thePlayer);
            entity.setAlwaysRenderNameTag(false);
            this.doRender(entity, x, (d0 - 0.1D), z, entityYaw, partialTicks, scale);
        }
    }

    private void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks, float scale) {
        EntityPlayer thePlayer = (EntityPlayer) entity;
        if (thePlayer != null) {
            if (!thePlayer.isUser() || this.renderManager.livingPlayer == entity) {
                double d0 = y;

                if (entity.isSneaking() && !(entity instanceof EntityPlayerSP)) {
                    d0 = y - 0.125D;
                }

                this.setModelVisibilities((AbstractClientPlayer) thePlayer);
                entity.setAlwaysRenderNameTag(false);
                GlStateManager.pushMatrix();
                GlStateManager.disableCull();
                this.mainModel.swingProgress = entity.getSwingProgress(partialTicks);
                this.mainModel.isRiding = entity.isRiding();
                this.mainModel.isChild = entity.isChild();

                try {
                    float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
                    float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
                    float f2 = f1 - f;

                    if (entity.isRiding() && entity.ridingEntity instanceof EntityLivingBase) {
                        EntityLivingBase entitylivingbase = (EntityLivingBase) entity.ridingEntity;
                        f = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
                        f2 = f1 - f;
                        float f3 = MathHelper.wrapAngleTo180_float(f2);

                        if (f3 < -85.0F) {
                            f3 = -85.0F;
                        }

                        if (f3 >= 85.0F) {
                            f3 = 85.0F;
                        }

                        f = f1 - f3;

                        if (f3 * f3 > 2500.0F) {
                            f += f3 * 0.2F;
                        }
                    }

                    float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
                    GlStateManager.translate((float) x, (float) d0, (float) z);
                    float f8 = (float) entity.ticksExisted + partialTicks;
                    this.rotateCorpse(entity, f8, f, partialTicks);
                    GlStateManager.enableRescaleNormal();
                    GlStateManager.scale(-scale, -scale, scale);
                    GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
                    float f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
                    float f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);

                    if (entity.isChild()) {
                        f6 *= 3.0F;
                    }

                    if (f5 > 1.0F) {
                        f5 = 1.0F;
                    }

                    GlStateManager.enableAlpha();
                    this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
                    this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, 0.0625F, entity);
                    this.renderModel(entity, f6, f5, f8, f2, f7, 0.0625F);
                    GlStateManager.depthMask(true);
                    GlStateManager.disableRescaleNormal();
                } catch (Exception e) {
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

    protected void renderModel(T entitylivingbaseIn, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
        boolean flag = !entitylivingbaseIn.isInvisible();
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer);

        if (flag || flag1) {
            if (!this.bindEntityTexture(entitylivingbaseIn)) {
                return;
            }

            if (flag1) {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
                GlStateManager.alphaFunc(516, 0.003921569F);
            }

            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

            if (flag1) {
                GlStateManager.disableBlend();
                GlStateManager.alphaFunc(516, 0.1F);
                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
            }
        }
    }

    protected void rotateCorpse(T bat, float p_77043_2_, float p_77043_3_, float partialTicks) {
        GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);

        if (bat.deathTime > 0) {
            float f = ((float) bat.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f = MathHelper.sqrt_float(f);

            if (f > 1.0F) {
                f = 1.0F;
            }

            GlStateManager.rotate(f * 90.0F, 0.0F, 0.0F, 1.0F);
        } else {
            String s = EnumChatFormatting.getTextWithoutFormattingCodes(bat.getName());

            if (s != null && (s.equals("Dinnerbone") || s.equals("Grumm")) && (!(bat instanceof EntityPlayer) || ((EntityPlayer) bat).isWearing(EnumPlayerModelParts.CAPE))) {
                GlStateManager.translate(0.0F, bat.height + 0.1F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }

    private void setModelVisibilities(AbstractClientPlayer clientPlayer) {
        ModelPlayer modelplayer = (ModelPlayer) this.mainModel;

        modelplayer.bipedHeadwear.showModel = false;
        modelplayer.bipedBodyWear.showModel = false;
        modelplayer.bipedLeftLegwear.showModel = false;
        modelplayer.bipedRightLegwear.showModel = false;
        modelplayer.bipedLeftArmwear.showModel = false;
        modelplayer.bipedRightArmwear.showModel = false;

        if (clientPlayer.isSpectator()) {
            modelplayer.setInvisible(false);
        } else {
            ItemStack itemstack = clientPlayer.inventory.getCurrentItem();
            modelplayer.setInvisible(true);
            modelplayer.heldItemLeft = 0;
            modelplayer.aimedBow = false;
            modelplayer.isSneak = clientPlayer.isSneaking();

            if (itemstack == null) {
                modelplayer.heldItemRight = 0;
            } else {
                modelplayer.heldItemRight = 1;

                if (clientPlayer.getItemInUseCount() > 0) {
                    EnumAction enumaction = itemstack.getItemUseAction();

                    if (enumaction == EnumAction.BLOCK) {
                        modelplayer.heldItemRight = 3;
                    } else if (enumaction == EnumAction.BOW) {
                        modelplayer.aimedBow = true;
                    }
                }
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return ((AbstractClientPlayer) entity).getLocationSkin();
    }
}
