package org.mineplugin.locusazzurro.icaruswings.client.render.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.SpearRenderer;
public class SpearRenderState extends EntityRenderState {
    public float yRot;
    public float xRot;
    public Identifier texture = SpearRenderer.FALLBACK;
    public boolean hasFoil;
}

