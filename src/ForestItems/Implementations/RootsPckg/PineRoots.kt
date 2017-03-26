package ForestItems.Implementations.RootsPckg

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.Cone
import ForestItems.Implementations.Food.Nut
import ForestItems.Implementations.Food.RootCrop
import ForestItems.Implementations.Roots

/**
 * Корни сосны
 */
class PineRoots: Roots() {
    override fun genFood() {
        val randCrops = genRandom(3)
        val randNuts = genRandom(3)
        val randCones = genRandom(3)

        for (i in 1..randCrops)
            addFood(RootCrop())

        for (i in 1..randNuts)
            addFood(Nut())

        for (i in 1..randCones)
            addFood(Cone())
    }
}