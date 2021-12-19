package org.mineplugin.locusazzurro.icaruswings.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;
import java.util.List;

public class ProjectileUtils {

    @Nullable
    public static LivingEntity rayTraceTarget(LivingEntity targeter, double step, int depth, double size){
        Vector3d eyePos = targeter.getEyePosition(1f);
        Vector3d lookVec = targeter.getViewVector(1f);
        Vector3d checkPos = eyePos;
        double hS = size / 2;
        for (int i = 0 ; i < depth; i++){
            AxisAlignedBB aabb = new AxisAlignedBB(hS,hS,hS,-hS,-hS,-hS).move(checkPos);
            List<LivingEntity> entities = targeter.level.getEntitiesOfClass(LivingEntity.class, aabb);
            for (LivingEntity entity : entities){
                if (targeter.canSee(entity) && entity != targeter) return entity;
            }
            checkPos = checkPos.add(lookVec.scale(step));
        }
        return null;
    }

    @Nullable
    public static LivingEntity rayTraceTargetFixedDistance(LivingEntity targeter, double step, int distance, double size){
        return rayTraceTarget(targeter, step, (int)Math.ceil(distance/step), size);
    }

}
