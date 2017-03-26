package ForestItems.Implementations.Animals

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender
import ForestItems.AdditionalItems.genRandom
import ForestItems.AdditionalItems.marginString
import ForestItems.Interfaces.IAnimal
import ForestItems.Interfaces.IContainsAnimal
import ForestItems.Interfaces.IFood
import java.util.*

class Wolf(override val canFly: Boolean = false) : IAnimal {
    override fun createNew(): IAnimal {
        return Wolf()
    }

    override val gender: Gender = if (genRandom(2) == 1) Gender.Male else Gender.Female

    override fun isCorrectHome(home: IContainsAnimal?): Boolean {
        return false
    }

    override fun canEat(animal: IAnimal): Boolean {
        return animal !is Wolf
    }

    override fun canEat(food: IFood): Boolean {
        return false
    }

    override fun eat(animal: IAnimal) {
        when (animal) {
            is Chipmunk -> health += 7
            is FlyingSquirrel -> health += 4
            is Squirrel -> health += 5
            is Woodpecker -> health += 6
        }
    }

    override fun eat(food: IFood) { }

    override var health: Int = 10

    override fun update(availableActions: Set<Actions>): Actions {
        val procAct = availableActions.filter { it != Actions.MoveUp && it != Actions.Breed }

        if (procAct.contains(Actions.EatAnimal) && health < 20)
            return Actions.EatAnimal

        if (availableActions.contains(Actions.Breed) && health > 20)
            return Actions.Breed

        return procAct[genRandom(procAct.size)]
    }

    override fun getAsString(): String {
        return "Волк: [Жизнь: $health]"
    }
}