package org.mineplugin.locusazzurro.icaruswings.client.render.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.math.Transformation;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3f;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.SpearItemStackTileEntityRenderer;

import java.util.List;

public final class SpearBakedModel {
    public static final Transformation HAND_TRANSFORMATION = new Transformation(null, null, new Vector3f(1.0F, -1.0F, -1.0F), null);
    private static final List<ItemDisplayContext> FLAT_CONTEXTS = List.of(
            ItemDisplayContext.GUI,
            ItemDisplayContext.GROUND,
            ItemDisplayContext.FIXED,
            ItemDisplayContext.ON_SHELF
    );

    private SpearBakedModel() {}

    public static ItemModel.Unbaked createItemModel(Identifier flatModel, Identifier inHandModel, Identifier throwingModel) {
        ItemModel.Unbaked inHandSpecial = ItemModelUtils.specialModel(inHandModel, SpearItemStackTileEntityRenderer.Unbaked.INSTANCE);
        ItemModel.Unbaked throwingSpecial = ItemModelUtils.specialModel(throwingModel, SpearItemStackTileEntityRenderer.Unbaked.INSTANCE);
        ItemModel.Unbaked handConditional = ItemModelUtils.conditional(
                HAND_TRANSFORMATION,
                ItemModelUtils.isUsingItem(),
                throwingSpecial,
                inHandSpecial
        );
        return ItemModelUtils.select(
                new DisplayContext(),
                handConditional,
                ItemModelUtils.when(FLAT_CONTEXTS, ItemModelUtils.plainModel(flatModel))
        );
    }

    public static JsonObject createThrowingBaseModel(Identifier particleTexture) {
        JsonObject root = new JsonObject();
        root.addProperty("gui_light", "front");

        JsonObject textures = new JsonObject();
        textures.addProperty("particle", particleTexture.toString());
        root.add("textures", textures);

        JsonObject display = new JsonObject();
        addTransform(display, "thirdperson_righthand", 0F, 90F, 180F, 8F, -17F, 9F, 1F, 1F, 1F);
        addTransform(display, "thirdperson_lefthand", 0F, 90F, 180F, 8F, -17F, -7F, 1F, 1F, 1F);
        addTransform(display, "firstperson_righthand", 0F, -90F, 25F, -3F, 17F, 1F, 1F, 1F, 1F);
        addTransform(display, "firstperson_lefthand", 0F, 90F, -25F, 13F, 17F, 1F, 1F, 1F, 1F);
        addTransform(display, "gui", 15F, -25F, -5F, 2F, 3F, 0F, 0.65F, 0.65F, 0.65F);
        addTransform(display, "fixed", 0F, 180F, 0F, -2F, 4F, -5F, 0.5F, 0.5F, 0.5F);
        addTransform(display, "ground", 0F, 0F, 0F, 4F, 4F, 2F, 0.25F, 0.25F, 0.25F);
        root.add("display", display);

        return root;
    }

    private static void addTransform(
            JsonObject display,
            String name,
            float rotX,
            float rotY,
            float rotZ,
            float transX,
            float transY,
            float transZ,
            float scaleX,
            float scaleY,
            float scaleZ
    ) {
        JsonObject transform = new JsonObject();
        transform.add("rotation", floatArray(rotX, rotY, rotZ));
        transform.add("translation", floatArray(transX, transY, transZ));
        transform.add("scale", floatArray(scaleX, scaleY, scaleZ));
        display.add(name, transform);
    }

    private static JsonArray floatArray(float x, float y, float z) {
        JsonArray arr = new JsonArray();
        arr.add(x);
        arr.add(y);
        arr.add(z);
        return arr;
    }
}
