package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.AdditionalItems.marginString
import ForestItems.Implementations.Food.Cone
import ForestItems.Implementations.Food.Nut
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood
import ForestItems.Interfaces.IHole

/**
 * Бурундук
 */
class Chipmunk(override val canFly: Boolean = false) : IAnimal {
    override fun createNew(): IAnimal {
        return Chipmunk()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun eat(animal: IAnimal) { }

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return home is IHole?
    }

    override fun update(set: Set<Actions>): Actions {
        if (health < 5 && set.contains(Actions.MoveDown))
            return Actions.MoveDown

        if (health > 20 && set.contains(Actions.Breed))
            return Actions.Breed

        if (set.contains(Actions.EatFood))
            return Actions.EatFood

        return set.elementAt(genRandom(set.size))
    }

    override fun getAsString(): String {
        return "Бурундук [Жизнь: $health]"
    }

    override fun canEat(animal: IAnimal): Boolean {
        return false
    }

    override fun canEat(food: IFood): Boolean {
        return food is Cone || food is Nut
    }

    override fun eat(food: IFood) {
        when (food) {
            is Cone -> health += 6
            is Nut -> health += 4
        }
    }

    override var health: Int = 7
}