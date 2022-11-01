package org.mineplugin.locusazzurro.icaruswings.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import java.util.Random;
import java.util.stream.IntStream;

public class KayrosGenUtils {


    public final static BlockState[] palette = new BlockState[8];
    public static byte[] cubeTerrain = new byte[512];
    private static PerlinNoise noise = PerlinNoise.create(new XoroshiroRandomSource(114514), IntStream.of(0, 1, 2));

    static {
        palette[0] = Blocks.AIR.defaultBlockState();
        palette[1] = Blocks.STONE.defaultBlockState();
        palette[2] = Blocks.DIRT.defaultBlockState();
        palette[3] = Blocks.GRASS_BLOCK.defaultBlockState();
        palette[4] = Blocks.AIR.defaultBlockState();
        palette[5] = Blocks.AIR.defaultBlockState();
        palette[6] = Blocks.AIR.defaultBlockState();
        palette[7] = Blocks.AIR.defaultBlockState();

        for (int i = 0; i < 64; i++){
            cubeTerrain[i] = 1;
            cubeTerrain[i + 64] = 1;
            cubeTerrain[i + 64 * 2] = 1;
            cubeTerrain[i + 64 * 3] = 2;
            cubeTerrain[i + 64 * 4] = 2;
            cubeTerrain[i + 64 * 5] = 3;
        }
    }

    public static CompoundTag generateTerrainTag(double xo, double zo){
        return convertToTag(generateTerrain(generateHeightMap((int) xo, (int) zo)));
    }

    public static CompoundTag generateTerrainTagWithJitter(double xo, double zo, RandomSource random, double amount){
        return generateTerrainTag(xo + amount * (random.nextDouble() - 0.5), zo + amount * (random.nextDouble() - 0.5));
    }

    private static byte[] generateHeightMap(int xo, int zo){
        byte[] map = new byte[64];
        int x,z,v;
        for (int i = 0; i < map.length; i++){
            z = i / 8;
            x = i - z * 8;
            v = Mth.clamp(4 + (int) Math.round(noise.getValue((xo + x)/10f, 64, (zo + z)/10f, 2.0d, 2.0d, true) * 10), 0 ,7);
            map[i] = (byte) v;
        }
        return map;
    }

    private static byte[] generateTerrain(byte[] map){
        byte[] terrain = new byte[512];
        byte height,depth;
        for (int p = 0; p < map.length; p++){
            depth = 0;
            height = map[p];
            while (height >= 0){
                final int p1 = 64 * height + p;
                switch (depth) {
                    case 0 -> terrain[p1] = 3;
                    case 1,2 -> terrain[p1] = 2;
                    default -> terrain[p1] = 1;
                }
                height --;
                depth ++;
            }
        }
        return terrain;
    }

    public static CompoundTag convertToTag(byte[] terrain){
        var tag = new CompoundTag();
        tag.putByteArray("Landscape", terrain);
        return tag;
    }
}
