package net.sjhub.simplebikes.registry.bikes;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.sjhub.simplebikes.model.ModelAcrobike;
import net.sjhub.simplebikes.model.ModelMachbike;
import net.sjhub.simplebikes.simplebikes.Tags;

public enum BikeTypes {

    ACRO_BIKE(
            "acro_bike",
            new ModelAcrobike(),
            new ResourceLocation(Tags.MOD_ID, "textures/entity/acro_bike.png"),
            0,
            -0.15,
            0.3,
            1.6F,
            1.3F,
            0.0,
            0.5,
            -0.5,
            0.02,
            0.01

    ),
    MACH_BIKE(
            "mach_bike",
            new ModelMachbike(),
            new ResourceLocation(Tags.MOD_ID, "textures/entity/mach_bike.png"),
            0,
            -0.1,
            0.3,
            1.6F,
            1.5F,
            0.0,
            1.0,
            -0.2,
            0.05,
            0.01
    );

    private final String name;
    private final ModelBase model;
    private final ResourceLocation resource;
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;
    private final float width;
    private final float height;
    private final double defaultSpeed;
    private final double maxForwardSpeed;
    private final double maxReverseSpeed;
    private final double acceleration;
    private final double deceleration;

    BikeTypes
            (
                    String name,
                    ModelBase model,
                    ResourceLocation resource,
                    double offsetX,
                    double offsetY,
                    double offsetZ,
                    float width,
                    float height,
                    double defaultSpeed,
                    double maxForwardSpeed,
                    double maxReverseSpeed,
                    double acceleration,
                    double deceleration
            )
    {
        this.name = name;
        this.model = model;
        this.resource = resource;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.width = width;
        this.height = height;
        this.defaultSpeed = defaultSpeed;
        this.maxForwardSpeed = maxForwardSpeed;
        this.maxReverseSpeed = maxReverseSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }

    public String getName() {
        return name;
    }

    public ModelBase getModel() {
        return model;
    }

    public ResourceLocation getResource() {
        return resource;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public double getDefaultSpeed() {
        return defaultSpeed;
    }

    public double getMaxForwardSpeed() {
        return maxForwardSpeed;
    }

    public double getMaxReverseSpeed() {
        return maxReverseSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getDeceleration() {
        return deceleration;
    }
}
