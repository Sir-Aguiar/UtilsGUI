package com.aguiar.utilsui.utils;

import org.bukkit.World;
import org.bukkit.block.Block;

public class RandomTP {
  private static final int MIN_X = -15000;
  private static final int MAX_X = 15000;
  private static final int MIN_Z = -15000;
  private static final int MAX_Z = 15000;
  private final World world;

  public RandomTP(World world) {
    this.world = world;
  }

  public int[] getRandomCoordinates() {
    boolean isLocationSafe = false;

    int x = 0;
    int y = 0;
    int z = 0;

    while (!isLocationSafe) {
      x = getRandomValue(MIN_X, MAX_X);
      z = getRandomValue(MIN_Z, MAX_Z);

      Block highestBlock = world.getHighestBlockAt(x, z);
      y = highestBlock.getY() + 1;
      isLocationSafe = !highestBlock.isLiquid();
    }

    return new int[]{x, y, z};
  }


  private int getRandomValue(int min, int max) {
    return min + (int) (Math.random() * ((max - min) + 1));
  }
}
