package ForestItems.Implementations

import ForestItems.AdditionalItems.genRandom
import ForestItems.AdditionalItems.marginString
import ForestItems.Interfaces.*
import java.util.*

class Forest : ForestItems.Interfaces.IForest {
    override fun removeTree(tree: ITree) {
        val index = treesEditable.indexOf(tree)

        map.removeAt(index)

        for (row in map)
            row.removeAt(index)

        treesEditable.remove(tree)
    }

    override val trees: List<ITree>
        get() = treesEditable

    override fun addTree(tree: ITree) {
        val len = treesEditable.size

        treesEditable.add(tree)

        val arr = ArrayList<Int>()

        for (i in 1..len)
            arr.add(0)

        map.add(arr)

        map.forEach { it.add(0) }
    }

    override fun getAsString() = toString()

    private data class MoveAnimalData(val animal: IAnimal, val tree: ITree, val treePart: TreePart)

    private val treesEditable = ArrayList<ITree>()

    private val updates = ArrayList<MoveAnimalData>()

    private val map = ArrayList<ArrayList<Int>>()

    fun getConnectedTrees(tree: ITree): List<ITree> {
        val index = trees.indexOf(tree)

        if (index < 0)
            return ArrayList()

        val result = ArrayList<ITree>()

        for (i in 0 until trees.size) {
            if (i != index && map[index][i] == 1)
                result.add(trees[i])
        }

        return result
    }

    fun connectTrees(first: ITree, second: ITree) {
        val fIndex = trees.indexOf(first)
        val sIndex = trees.indexOf(second)

        if (fIndex >= 0 && sIndex >= 0) {
            map[fIndex][sIndex] = 1
            map[sIndex][fIndex] = 1
        }
    }

    fun areTreesConnected(first: ITree, second: ITree): Boolean {
        val fIndex = trees.indexOf(first)
        val sIndex = trees.indexOf(second)

        return fIndex >= 0 && sIndex >= 0 && map[fIndex][sIndex] == 1
    }

    override fun update() {
        updates.clear()

        trees.forEach(ITree::update)

        updates.forEach {
            when (it.treePart) {
                is Crown -> it.tree.crown.addAnimal(it.animal)
                is Trunc -> it.tree.trunk.addAnimal(it.animal)
                is Roots -> it.tree.roots.addAnimal(it.animal)
            }
        }
    }

    override fun canMoveAnimal(currentTree: ITree): Boolean {
        return getConnectedTrees(currentTree).isNotEmpty()
    }

    override fun moveAnimalToRandomTree(animal: IAnimal, currentTree: ITree, treePart: TreePart) {
        if (!canMoveAnimal(currentTree))
            return

        val connected = getConnectedTrees(currentTree)

        updates.add(MoveAnimalData(animal, connected[genRandom(connected.size)], treePart))
    }

    override fun toString(): String {
        if (trees.isEmpty())
            return "Деревья: пусто"

        return "Деревья:\n" + trees.joinToString("\n") { ">\n" + it.getAsString().marginString()}
    }
}
