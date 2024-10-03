package net.sjhub.simplebikes.registry.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.bikes.CaptureBikeType;
import net.sjhub.simplebikes.registry.entities.BikeEntity;

import javax.annotation.Nullable;

public class SimpleRenderer extends RenderLiving<BikeEntity> {

    public SimpleRenderer(RenderManager rendermanagerIn) {
        super(rendermanagerIn, BikeTypes.ACRO_BIKE.getModel(), 0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(BikeEntity entity) {
        return CaptureBikeType.getBikeTypesFallback(entity.getUniqueID()).getResource();
    }

    @Override
    public void doRender(BikeEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        BikeTypes bikeType = CaptureBikeType.getBikeTypesFallback(entity.getUniqueID());
        if (this.mainModel != bikeType.getModel()) {
            this.mainModel = bikeType.getModel();
        }

        if (this.shadowSize != bikeType.getHeight() / 2) {
            this.shadowSize = bikeType.getHeight() / 2;
        }

        double radianYaw = Math.toRadians(entityYaw);
        double offsetX = x + (bikeType.getOffsetX() * Math.cos(radianYaw) - bikeType.getOffsetZ() * Math.sin(radianYaw));
        double offsetY = y + bikeType.getOffsetY();
        double offsetZ = z + (bikeType.getOffsetX() * Math.sin(radianYaw) + bikeType.getOffsetZ() * Math.cos(radianYaw));
        super.doRender(entity, offsetX, offsetY, offsetZ, entityYaw, partialTicks);
    }
}
