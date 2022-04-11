package org.mineplugin.locusazzurro.icaruswings.render.models;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

@OnlyIn(Dist.CLIENT)
public class GoldenRamFleeceModel<T extends GoldenRamEntity> extends QuadrupedModel<T> {
    private float headXRot;

    //todo 构造方法改了，你看看怎么修
    public GoldenRamFleeceModel(ModelPart root) {
        super(root, false, 0.0F, 8.0F, 4.0F, 2.0F, 24);
    }

    @Override
    public void prepareMobModel(T entity, float x, float y, float z) {
        super.prepareMobModel(entity, x, y, z);
        this.head.y = 6.0F + entity.getHeadEatPositionScale(z) * 9.0F;
        this.headXRot = entity.getHeadEatAngleScale(z);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.xRot = this.headXRot;
    }
}
