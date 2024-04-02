package org.mineplugin.locusazzurro.icaruswings.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class ProjectileUtils {

    public static final Predicate<LivingEntity> IS_HOSTILE = e -> e.getType().getCategory().equals(MobCategory.MONSTER);

    @Nullable
    public static LivingEntity rayTraceTarget(LivingEntity targeter, Predicate<LivingEntity> criteria, double step, int depth, double size){
        Vec3 eyePos = targeter.getEyePosition(1f);
        Vec3 lookVec = targeter.getViewVector(1f);
        Vec3 checkPos = eyePos;
        double hS = size / 2;
        for (int i = 0 ; i < depth; i++){
            AABB aabb = new AABB(hS,hS,hS,-hS,-hS,-hS).move(checkPos);
            List<LivingEntity> entities = targeter.level().getEntitiesOfClass(LivingEntity.class, aabb);
            for (LivingEntity entity : entities){
                if (entity != targeter && criteria.test(entity) && targeter.hasLineOfSight(entity)) return entity;
            }
            checkPos = checkPos.add(lookVec.scale(step));
        }
        return null;
    }

    @Nullable
    public static LivingEntity rayTraceTargetFixedDistance(LivingEntity targeter, Predicate<LivingEntity> criteria, double step, int distance, double size){
        return rayTraceTarget(targeter, criteria, step, (int)Math.ceil(distance/step), size);
    }

}
