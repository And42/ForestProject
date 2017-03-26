package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.Implementations.Food.MappleLeaf
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood
import ForestItems.Interfaces.IHollow

/**
 * Белка летяга
 */
class FlyingSquirrel(override val canFly: Boolean = false) : IAnimal {
    override fun createNew(): IAnimal {
        return FlyingSquirrel()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun eat(animal: IAnimal) { }

    override fun eat(food: IFood) {
        when (food) {
            is MappleLeaf -> health += 4
        }
    }

    override var health: Int = 6

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return home is IHollow?
    }

    override fun update(set: Set<Actions>): Actions {
        return set.elementAt(genRandom(set.size))
    }

    override fun getAsString(): String {
        return "Белка летяга"
    }

    override fun canEat(animal: IAnimal): Boolean {
        return false
    }

    override fun canEat(food: IFood): Boolean {
        return food is MappleLeaf
    }
}