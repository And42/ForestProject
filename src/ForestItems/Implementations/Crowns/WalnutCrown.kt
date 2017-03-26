package ForestItems.Implementations.Crowns

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.Cone
import ForestItems.Implementations.Food.Nut
import ForestItems.Interfaces.Crown

/**
 * Крона ореха
 */
class WalnutCrown: Crown() {
    override fun genFood() {
        val randNuts = genRandom(3)
        val randCones = genRandom(3)

        for (i in 1..randNuts)
            addFood(Nut())

        for (i in 1..randCones)
            addFood(Cone())
    }
}