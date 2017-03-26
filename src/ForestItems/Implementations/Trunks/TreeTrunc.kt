package ForestItems.Implementations.Trunks

import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.Worm
import ForestItems.Interfaces.*
import java.util.*

class TreeTrunc: Trunc() {
    override fun genFood() {
        val rand = genRandom(5)

        for (i in 1..rand)
            addFood(Worm())
    }
}
