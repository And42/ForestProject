package ForestItems.Interfaces

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.Gender

interface IAnimal {
    fun createNew(): IAnimal

    fun isCorrectHome(home: IContainsAnimal?): Boolean

    fun update(set: Set<Actions>): Actions

    fun getAsString(): String

    fun canEat(animal: IAnimal): Boolean

    fun canEat(food: IFood): Boolean

    fun eat(animal: IAnimal)

    fun eat(food: IFood)

    var health: Int

    val gender: Gender

    val canFly: Boolean

    fun updateHealth() {
        health--
    }
}
