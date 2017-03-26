package ForestItems.Interfaces

import java.util.*

abstract class TreePart {
    fun addAnimal(animal: IAnimal) {
        animalsArr.add(animal)
    }

    fun removeAnimal(animal: IAnimal) {
        animalsArr.remove(animal)
    }

    fun removeAllAnimals() {
        animalsArr.clear()
    }

    val animals: List<IAnimal>
        get() = animalsArr

    protected val animalsArr: ArrayList<IAnimal> = ArrayList()

    abstract fun getAsString(): String

    abstract fun genFood()
}
