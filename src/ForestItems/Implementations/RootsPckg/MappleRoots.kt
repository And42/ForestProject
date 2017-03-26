package ForestItems.Implementations.RootsPckg

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.MappleLeaf
import ForestItems.Implementations.Food.RootCrop
import ForestItems.Implementations.Roots

/**
 * Корни клёна
 */
class MappleRoots: Roots() {
    override fun genFood() {
        val randCrops = genRandom(3)
        val randLeafs = genRandom(3)

        for (i in 1..randCrops)
            addFood(RootCrop())

        for (i in 1..randLeafs)
            addFood(MappleLeaf())
    }
}