package org.mineplugin.locusazzurro.icaruswings.render;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

@OnlyIn(Dist.CLIENT)
public class GoldenRamFleeceModel<T extends GoldenRamEntity> extends QuadrupedModel<T> {

    public GoldenRamFleeceModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.6F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 1.75F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        float lvt_1_1_ = 0.5F;
        this.leg0 = new ModelRenderer(this, 0, 16);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.leg0.setPos(-3.0F, 12.0F, 7.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.leg1.setPos(3.0F, 12.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.leg2.setPos(-3.0F, 12.0F, -5.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.leg3.setPos(3.0F, 12.0F, -5.0F);
    }

    public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
    }

    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
    }
}
