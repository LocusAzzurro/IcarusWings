package org.mineplugin.locusazzurro.icaruswings.render.models;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

@OnlyIn(Dist.CLIENT)
public class GoldenRamModel<T extends GoldenRamEntity> extends QuadrupedModel<T> {
    private float headXRot;

    //todo 构造方法改了，你看看怎么修
    public GoldenRamModel(ModelPart part) {
        super(part, false, 0, 0, 1, 1, 0);
    }

    @Override
    public void prepareMobModel(T entity, float x, float y, float z) {
        super.prepareMobModel(entity, x, y, z);
        this.head.y = 6.0F + entity.getHeadEatPositionScale(z) * 9.0F;
        this.headXRot = entity.getHeadEatAngleScale(z);
    }

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ModData.MOD_ID, "golden_ram_model"), "main");

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        return LayerDefinition.create(meshdefinition, 16, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.xRot = this.headXRot;
    }
}
