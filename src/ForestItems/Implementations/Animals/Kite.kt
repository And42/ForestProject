package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood

/**
 * Коршун
 */
class Kite(override val canFly: Boolean = true) : IAnimal {
    override fun createNew(): IAnimal {
        return Kite()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun eat(animal: IAnimal) {
        when (animal) {
            is Chipmunk -> health += 7
            is FlyingSquirrel -> health += 4
            is Squirrel -> health += 5
            is Woodpecker -> health += 6
        }
    }

    override fun canEat(animal: IAnimal): Boolean {
        return animal !is Kite && animal !is Badger
    }

    override fun canEat(food: IFood): Boolean {
        return false
    }

    override fun eat(food: IFood) { }

    override var health: Int = 10

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return false
    }

    override fun update(set: Set<Actions>): Actions {
        val avail = set.filter { it != Actions.MoveDown }

        return avail[genRandom(avail.size)]
    }

    override fun getAsString(): String {
        return "Коршун"
    }
}