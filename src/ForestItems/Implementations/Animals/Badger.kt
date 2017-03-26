package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.RootCrop
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood
import ForestItems.Interfaces.IHole

/**
 * Барсук
 */
class Badger(override val canFly: Boolean = false) : IAnimal {
    override fun createNew(): IAnimal {
        return Badger()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun eat(animal: IAnimal) { }

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return home is IHole?
    }

    override fun update(set: Set<Actions>): Actions {
        return set.elementAt(genRandom(set.size))
    }

    override fun getAsString(): String {
        return "Барсук"
    }

    override fun canEat(animal: IAnimal): Boolean {
        return false
    }

    override fun canEat(food: IFood): Boolean {
        return food is RootCrop
    }

    override fun eat(food: IFood) {
        when (food) {
            is RootCrop -> health += 7
        }
    }

    override var health: Int = 10
}