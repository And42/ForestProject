package ForestItems.Implementations

import ForestItems.AdditionalItems.marginString
import ForestItems.Interfaces.IFood
import ForestItems.Interfaces.IHole
import ForestItems.Interfaces.TreePart
import java.util.*

/**
 * Корни
 */
abstract class Roots: TreePart() {
    val holes: List<IHole>
        get() = holesArr

    private val holesArr: ArrayList<IHole> = ArrayList()

    fun addHole(hole: IHole) {
        holesArr.add(hole)
    }

    fun removeHole(hole: IHole) {
        holesArr.remove(hole)
    }

    val food: List<IFood>
        get() = foodArr

    private val foodArr: ArrayList<IFood> = ArrayList()

    protected fun addFood(food: IFood) {
        foodArr.add(food)
    }

    fun removeFood(food: IFood) {
        foodArr.remove(food)
    }

    override fun getAsString(): String {
        return "${formatAnimals()}\n${formatHoles()}"
    }

    private fun formatAnimals(): String {
        if (animals.isEmpty())
            return "Животные: пусто"

        return "Животные:\n${animals.joinToString(separator = "") { it.getAsString().marginString() + "\n" }}"
    }

    private fun formatHoles(): String {
        if (holes.isEmpty())
            return "Норы: пусто"

        return "Норы:\n${holes.joinToString { it.getAsString().marginString() }}"
    }
}
