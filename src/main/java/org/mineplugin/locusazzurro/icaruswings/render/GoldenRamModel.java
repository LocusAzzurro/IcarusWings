package org.mineplugin.locusazzurro.icaruswings.render;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GoldenRamModel<T extends GoldenRamEntity> extends QuadrupedModel<T> {
    private float headXRot;

    public GoldenRamModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
    }

    @ParametersAreNonnullByDefault
    public void prepareMobModel(T entity, float x, float y, float z) {
        super.prepareMobModel(entity, x, y, z);
        this.head.y = 6.0F + entity.getHeadEatPositionScale(z) * 9.0F;
        this.headXRot = entity.getHeadEatAngleScale(z);
    }

    @ParametersAreNonnullByDefault
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.xRot = this.headXRot;
    }

}
