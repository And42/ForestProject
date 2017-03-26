package ForestItems.Interfaces

import ForestItems.AdditionalItems.marginString
import java.util.*

abstract class Trunc: TreePart() {
    val hollows: List<IHollow>
        get() = hollowsArr

    protected val hollowsArr: ArrayList<IHollow> = ArrayList()

    val food: List<IFood>
        get() = foodArr

    protected val foodArr: ArrayList<IFood> = ArrayList()

    fun addHollow(hollow: IHollow) {
        hollowsArr.add(hollow)
    }

    fun removeHollow(hollow: IHollow) {
        hollowsArr.remove(hollow)
    }

    fun addFood(food: IFood) {
        foodArr.add(food)
    }

    fun removeFood(food: IFood) {
        foodArr.remove(food)
    }

    override fun getAsString(): String {
        return "${formatAnimals()}\n${formatHollows()}"
    }

    private fun formatAnimals(): String {
        if (animals.isEmpty())
            return "Животные: пусто"

        return "Животные:\n${animals.joinToString(separator = "") { it.getAsString().marginString() + "\n" }}"
    }

    private fun formatHollows(): String {
        if (hollows.isEmpty())
            return "Дупла: пусто"

        return "Дупла:\n${hollows.joinToString(separator = "") { it.getAsString().marginString() + "\n" }}"
    }
}
