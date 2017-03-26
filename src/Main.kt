import ForestItems.Implementations.Animals.Chipmunk
import ForestItems.Implementations.Animals.Wolf
import ForestItems.Implementations.Forest
import ForestItems.Implementations.Trees.BirchTree
import ForestItems.Implementations.Trees.SpruceTree

fun main(args : Array<String>) {
    val forest = Forest()

    val first = SpruceTree.createExample(forest)
    val second = BirchTree.createExample(forest)

    first.roots.addAnimal(Wolf())
    first.roots.addAnimal(Wolf())

    for (i in 1..10) {
        first.roots.addAnimal(Chipmunk())
    }

    forest.addTree(first)
    forest.addTree(second)

    forest.connectTrees(first, second)

    while (true) {
        println(forest.getAsString())
        println()

        forest.update()

        Thread.sleep(1500)
    }
}