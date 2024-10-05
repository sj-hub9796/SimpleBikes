package net.sjhub.simplebikes.registry.bikes.acrobike.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.sjhub.simplebikes.model.ModelAcrobike;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.bikes.acrobike.entities.EntityAcrobike;

import javax.annotation.Nullable;

public class RenderAcroBike extends RenderLiving<EntityAcrobike> {

    private static final ResourceLocation TEXTURES = BikeTypes.ACRO_BIKE.getTexture();
    private static final ModelBase MODEL = new ModelAcrobike();

    public RenderAcroBike(RenderManager renderManager) {
        super(renderManager, MODEL, 1F);
    }

    @Override
    public void doRender(EntityAcrobike entity, double x, double y, double z, float entityYaw, float partialTicks) {
        double radianYaw = Math.toRadians(entityYaw);
        double offsetX = x + (entity.getOffsetX() * Math.cos(radianYaw) - entity.getOffsetZ() * Math.sin(radianYaw));
        double offsetY = y + entity.getOffsetY();
        double offsetZ = z + (entity.getOffsetX() * Math.sin(radianYaw) + entity.getOffsetZ() * Math.cos(radianYaw));
        super.doRender(entity, offsetX, offsetY, offsetZ, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAcrobike entity) {
        return TEXTURES;
    }
}
