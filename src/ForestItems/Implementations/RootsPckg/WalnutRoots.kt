package ForestItems.Implementations.RootsPckg

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.Cone
import ForestItems.Implementations.Food.Nut
import ForestItems.Implementations.Food.RootCrop
import ForestItems.Implementations.Roots

/**
 * Корни ореха
 */
class WalnutRoots: Roots() {
    override fun genFood() {
        val randCrops = genRandom(2)
        val randNuts = genRandom(2)
        val randCones = genRandom(2)

        for (i in 1..randCrops)
            addFood(RootCrop())

        for (i in 1..randNuts)
            addFood(Nut())

        for (i in 1..randCones)
            addFood(Cone())
    }
}