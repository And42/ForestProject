package ForestItems.Implementations.Trees

import ForestItems.Implementations.Crowns.MappleCrown
import ForestItems.Implementations.Roots
import ForestItems.Implementations.RootsPckg.MappleRoots
import ForestItems.Implementations.Trunks.TreeTrunc
import ForestItems.Interfaces.Crown
import ForestItems.Interfaces.IForest
import ForestItems.Interfaces.ITree
import ForestItems.Interfaces.Trunc

/**
 * Клён
 */
class MappleTree(override val forest: IForest, override val crown: Crown, override val trunk: Trunc, override val roots: Roots) : ITree {
    companion object {
        fun createExample(forest: IForest): MappleTree = MappleTree(forest, MappleCrown(), TreeTrunc(), MappleRoots())
    }

    override var age: Int = 0
}