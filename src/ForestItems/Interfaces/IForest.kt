package ForestItems.Interfaces

interface IForest {
    fun update()

    fun moveAnimalToRandomTree(animal: IAnimal, currentTree: ITree, treePart: TreePart)

    fun canMoveAnimal(currentTree: ITree): Boolean

    fun getAsString(): String

    fun addTree(tree: ITree)

    fun removeTree(tree: ITree)

    val trees: List<ITree>
}
