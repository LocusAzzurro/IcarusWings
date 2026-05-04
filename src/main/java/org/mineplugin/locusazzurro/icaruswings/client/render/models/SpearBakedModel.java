package org.mineplugin.locusazzurro.icaruswings.client.render.models;

import com.google.gson.JsonObject;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.properties.SpearThrowingProperty;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.SpearItemStackTileEntityRenderer;

import java.util.List;

public final class SpearBakedModel {
    private static final SpearThrowingProperty SPEAR_THROWING_PROPERTY = SpearThrowingProperty.INSTANCE;
    private static final List<ItemDisplayContext> FIRST_PERSON_CONTEXTS = List.of(
            ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
            ItemDisplayContext.FIRST_PERSON_RIGHT_HAND
    );
    private static final List<ItemDisplayContext> FLAT_CONTEXTS = List.of(
            ItemDisplayContext.GUI,
            ItemDisplayContext.GROUND,
            ItemDisplayContext.FIXED,
            ItemDisplayContext.ON_SHELF
    );

    private SpearBakedModel() {}

    public static ItemModel.Unbaked createItemModel(Identifier flatModel, Identifier specialBaseModel) {
        ItemModel.Unbaked firstPersonSpecial = ItemModelUtils.specialModel(
                specialBaseModel,
                SpearItemStackTileEntityRenderer.FirstPersonUnbaked.INSTANCE
        );
        ItemModel.Unbaked thirdPersonSpecial = ItemModelUtils.specialModel(
                specialBaseModel,
                SpearItemStackTileEntityRenderer.ThirdPersonUnbaked.INSTANCE
        );
        ItemModel.Unbaked thirdPersonThrowingSpecial = ItemModelUtils.specialModel(
                specialBaseModel,
                SpearItemStackTileEntityRenderer.ThirdPersonThrowingUnbaked.INSTANCE
        );
        ItemModel.Unbaked thirdPersonConditional = ItemModelUtils.conditional(
                SPEAR_THROWING_PROPERTY,
                thirdPersonThrowingSpecial,
                thirdPersonSpecial
        );
        return ItemModelUtils.select(
                new DisplayContext(),
                thirdPersonConditional,
                ItemModelUtils.when(FLAT_CONTEXTS, ItemModelUtils.plainModel(flatModel)),
                ItemModelUtils.when(FIRST_PERSON_CONTEXTS, firstPersonSpecial)
        );
    }

    public static JsonObject createSpecialBaseModel(Identifier particleTexture) {
        JsonObject root = new JsonObject();
        root.addProperty("gui_light", "front");

        JsonObject textures = new JsonObject();
        textures.addProperty("particle", particleTexture.toString());
        root.add("textures", textures);

        return root;
    }
}
