package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.Worm
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood
import ForestItems.Interfaces.IHollow

/**
 * Дятел
 */
class Woodpecker(override val canFly: Boolean = true) : IAnimal {
    override fun createNew(): IAnimal {
        return Woodpecker()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun eat(animal: IAnimal) {}

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return home is IHollow?
    }

    override fun update(set: Set<Actions>): Actions {
        return set.elementAt(genRandom(set.size))
    }

    override fun getAsString(): String {
        return "Дятел"
    }

    override fun canEat(animal: IAnimal): Boolean {
        return false
    }

    override fun canEat(food: IFood): Boolean {
        return food is Worm
    }

    override fun eat(food: IFood) {
        when (food) {
            is Worm -> health += 5
        }
    }

    override var health: Int = 6
}