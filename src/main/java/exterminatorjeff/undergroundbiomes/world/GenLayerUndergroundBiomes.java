/*
 */

package exterminatorjeff.undergroundbiomes.world;

import exterminatorjeff.undergroundbiomes.api.UBBiome;
import exterminatorjeff.undergroundbiomes.api.UndergroundBiomeSet;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerUndergroundBiomes extends GenLayer {
  /**
   * this sets all the biomes that are allowed to appear in the overworld
   */
  public UBBiome[] allowedBiomes;

  public GenLayerUndergroundBiomes(long par1, UndergroundBiomeSet biomeSet) {
    super(par1);
    allowedBiomes = biomeSet.allowedBiomes();
  }

  /**
   * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
   * amounts, or biomeList[] indices based on the particular GenLayer subclass.
   */
  public int[] getInts(int par1, int par2, int par3, int par4) {
    //int[] var5 = this.parent.getInts(par1, par2, par3, par4);
    int[] var6 = new int[par3 * par4];
    if (allowedBiomes.length < 2) throw new RuntimeException();


    for (int var7 = 0; var7 < par4; ++var7) {
      for (int var8 = 0; var8 < par3; ++var8) {
        this.initChunkSeed((long) (var8 + par1), (long) (var7 + par2));
        var6[var8 + var7 * par3] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].ID;
        if (var6[var8 + var7 * par3] < 0) {
          String result = "";
          for (int i = 0; i < this.allowedBiomes.length; i++) {
            result += " " + allowedBiomes[i].ID;
          }
          if (result.length() > 0) throw new RuntimeException(result);
        }
      }
    }
    return var6;
  }


}