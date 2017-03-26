package ForestItems.Implementations.Trees

import ForestItems.Implementations.Crowns.PineCrown
import ForestItems.Implementations.Roots
import ForestItems.Implementations.RootsPckg.PineRoots
import ForestItems.Implementations.Trunks.TreeTrunc
import ForestItems.Interfaces.Crown
import ForestItems.Interfaces.IForest
import ForestItems.Interfaces.ITree
import ForestItems.Interfaces.Trunc

/**
 * Сосна
 */
class PineTree(override val forest: IForest, override val crown: Crown, override val trunk: Trunc, override val roots: Roots) : ITree {
    companion object {
        fun createExample(forest: IForest): PineTree = PineTree(forest, PineCrown(), TreeTrunc(), PineRoots())
    }

    override var age: Int = 0
}
