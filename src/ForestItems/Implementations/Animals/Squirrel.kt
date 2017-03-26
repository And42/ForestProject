package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.Cone
import ForestItems.Implementations.Food.Nut
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood
import ForestItems.Interfaces.IHollow
import java.util.*

class Squirrel(override val canFly: Boolean = false) : IAnimal {
    override fun createNew(): IAnimal {
        return Squirrel()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun eat(animal: IAnimal) { }

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return home is IHollow?
    }

    override fun eat(food: IFood) {
        when (food) {
            is Cone -> health += 4
            is Nut -> health += 3
        }
    }

    override var health: Int = 7

    override fun canEat(animal: IAnimal): Boolean {
        return false
    }

    override fun canEat(food: IFood): Boolean {
        return food is Cone || food is Nut
    }

    private val random = Random()

    override fun update(set: Set<Actions>): Actions {
        return set.elementAt(random.nextInt(set.size))
    }

    override fun getAsString(): String {
        return "Белка"
    }
}
