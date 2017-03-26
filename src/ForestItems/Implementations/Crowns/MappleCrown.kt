package ForestItems.Implementations.Crowns

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.MappleLeaf
import ForestItems.Interfaces.Crown

/**
 * Крона клёна
 */
class MappleCrown: Crown() {
    override fun genFood() {
        val randLeafs = genRandom(3)

        for (i in 1..randLeafs)
            addFood(MappleLeaf())
    }
}