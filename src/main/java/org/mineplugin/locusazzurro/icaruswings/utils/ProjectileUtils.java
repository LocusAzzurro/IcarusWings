package org.mineplugin.locusazzurro.icaruswings.utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class ProjectileUtils {

    @Nullable
    public static LivingEntity rayTraceTarget(LivingEntity targeter, double step, int depth, double size){
        Vec3 eyePos = targeter.getEyePosition(1f);
        Vec3 lookVec = targeter.getViewVector(1f);
        Vec3 checkPos = eyePos;
        double hS = size / 2;
        for (int i = 0 ; i < depth; i++){
            AABB aabb = new AABB(hS,hS,hS,-hS,-hS,-hS).move(checkPos);
            List<LivingEntity> entities = targeter.level.getEntitiesOfClass(LivingEntity.class, aabb);
            for (net.minecraft.world.entity.LivingEntity entity : entities){
                if (targeter.canSee(entity) && entity != targeter) return entity;
            }
            checkPos = checkPos.add(lookVec.scale(step));
        }
        return null;
    }

    @Nullable
    public static net.minecraft.world.entity.LivingEntity rayTraceTargetFixedDistance(LivingEntity targeter, double step, int distance, double size){
        return rayTraceTarget(targeter, step, (int)Math.ceil(distance/step), size);
    }

}
