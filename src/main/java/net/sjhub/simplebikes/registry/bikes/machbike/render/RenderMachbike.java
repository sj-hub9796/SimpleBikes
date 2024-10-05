package net.sjhub.simplebikes.registry.bikes.machbike.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.sjhub.simplebikes.model.ModelMachbike;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.bikes.machbike.entities.EntityMachbike;

import javax.annotation.Nullable;

public class RenderMachbike extends RenderLiving<EntityMachbike> {

    private static final ResourceLocation TEXTURES = BikeTypes.MACH_BIKE.getTexture();
    private static final ModelBase MODEL = new ModelMachbike();

    public RenderMachbike(RenderManager renderManager) {
        super(renderManager, MODEL, 1F);
    }

    @Override
    public void doRender(EntityMachbike entity, double x, double y, double z, float entityYaw, float partialTicks) {
        double radianYaw = Math.toRadians(entityYaw);
        double offsetX = x + (entity.getOffsetX() * Math.cos(radianYaw) - entity.getOffsetZ() * Math.sin(radianYaw));
        double offsetY = y + entity.getOffsetY();
        double offsetZ = z + (entity.getOffsetX() * Math.sin(radianYaw) + entity.getOffsetZ() * Math.cos(radianYaw));
        super.doRender(entity, offsetX, offsetY, offsetZ, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMachbike entity) {
        return TEXTURES;
    }
}
