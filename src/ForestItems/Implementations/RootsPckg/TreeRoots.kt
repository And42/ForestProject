package ForestItems.Implementations.RootsPckg

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.RootCrop
import ForestItems.Implementations.Roots

class TreeRoots: Roots() {
    override fun genFood() {
        val randCrops = genRandom(2)

        for (i in 1..randCrops)
            addFood(RootCrop())
    }
}