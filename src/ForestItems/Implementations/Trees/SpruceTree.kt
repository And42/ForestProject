package ForestItems.Implementations.Trees

import ForestItems.Implementations.Crowns.SpruceCrown
import ForestItems.Implementations.Roots
import ForestItems.Implementations.RootsPckg.SpruceRoots
import ForestItems.Implementations.Trunks.TreeTrunc
import ForestItems.Interfaces.Crown
import ForestItems.Interfaces.IForest
import ForestItems.Interfaces.ITree
import ForestItems.Interfaces.Trunc

/**
 * Ель
 */
class SpruceTree(override val forest: IForest, override val crown: Crown, override val trunk: Trunc, override val roots: Roots) : ITree {
    companion object {
        fun createExample(forest: IForest): SpruceTree = SpruceTree(forest, SpruceCrown(), TreeTrunc(), SpruceRoots())
    }

    override var age: Int = 0
}
