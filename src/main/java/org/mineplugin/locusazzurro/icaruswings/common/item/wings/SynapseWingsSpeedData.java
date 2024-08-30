package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

public record SynapseWingsSpeedData (double directSpeed, double inertialSpeed, double totalSpeed){

    public static final SynapseWingsSpeedData DEFAULT = new SynapseWingsSpeedData(0.1d, 0.5d, 0.5d);
    public static final SynapseWingsSpeedData ALPHA = new SynapseWingsSpeedData(0.1d, 0.5d, 0.5d);
    public static final SynapseWingsSpeedData BETA = new SynapseWingsSpeedData(0.08d, 1.2d, 0.5d);
    public static final SynapseWingsSpeedData DELTA = new SynapseWingsSpeedData(0.3d, 1.6d, 0.5d);
    public static final SynapseWingsSpeedData EPSILON = new SynapseWingsSpeedData(0.04d, 1.2d, 0.5d);
    public static final SynapseWingsSpeedData ZETA = new SynapseWingsSpeedData(0.08d, 1.1d, 0.5d);
    public static final SynapseWingsSpeedData THETA = new SynapseWingsSpeedData(0.1d, 0.5d, 0.5d);

}
