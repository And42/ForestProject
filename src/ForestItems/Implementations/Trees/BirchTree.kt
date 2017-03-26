package ForestItems.Implementations.Trees

import ForestItems.Implementations.Crowns.TreeCrown
import ForestItems.Implementations.Roots
import ForestItems.Implementations.RootsPckg.TreeRoots
import ForestItems.Implementations.Trunks.TreeTrunc
import ForestItems.Interfaces.*

/**
 * Берёза
 */
class BirchTree(override val forest: IForest, override val crown: Crown, override val trunk: Trunc, override val roots: Roots) : ITree {
    companion object {
        fun createExample(forest: IForest): BirchTree = BirchTree(forest, TreeCrown(), TreeTrunc(), TreeRoots())
    }

    override var age: Int = 0
}
