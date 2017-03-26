package ForestItems.Interfaces

import ForestItems.AdditionalItems.marginString
import java.util.*

abstract class Crown: TreePart() {
    val food: List<IFood>
        get() = foodArr

    protected val foodArr: ArrayList<IFood> = ArrayList()

    fun addFood(food: IFood) {
        foodArr.add(food)
    }

    fun removeFood(food: IFood) {
        foodArr.remove(food)
    }

    override fun getAsString(): String {
        if (animals.isEmpty())
            return "Животные: пусто"

        return "Животные:\n${animals.joinToString(separator = "") { it.getAsString().marginString() + "\n" }}"
    }
}