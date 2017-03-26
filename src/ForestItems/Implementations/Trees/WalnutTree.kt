package ForestItems.Implementations.Trees

import ForestItems.Implementations.Crowns.WalnutCrown
import ForestItems.Implementations.Roots
import ForestItems.Implementations.RootsPckg.WalnutRoots
import ForestItems.Implementations.Trunks.TreeTrunc
import ForestItems.Interfaces.Crown
import ForestItems.Interfaces.IForest
import ForestItems.Interfaces.ITree
import ForestItems.Interfaces.Trunc

/**
 * Орех
 */
class WalnutTree(override val forest: IForest, override val crown: Crown, override val trunk: Trunc, override val roots: Roots) : ITree {
    companion object {
        fun createExample(forest: IForest): WalnutTree = WalnutTree(forest, WalnutCrown(), TreeTrunc(), WalnutRoots())
    }

    override var age: Int = 0
}
