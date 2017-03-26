package ForestItems.Interfaces

import ForestItems.AdditionalItems.Actions
import ForestItems.AdditionalItems.marginString
import ForestItems.Implementations.Roots
import java.util.*

interface ITree {
    val forest: IForest

    val age: Int

    val height: Int
        get() = age

    val crown: Crown
    val trunk: Trunc
    val roots: Roots

    fun update() {
        crown.genFood()
        trunk.genFood()
        roots.genFood()

        val crownAnimals = ArrayList<IAnimal>()
        val trunkAnimals = ArrayList<IAnimal>()
        val rootsAnimals = ArrayList<IAnimal>()

        val trunkFilledHollows = trunk.hollows.filter { it.animal != null }
        val rootFilledHoles = roots.holes.filter { it.animal != null }

        fun createOptions(vararg options: Actions?): HashSet<Actions> {
            val set = HashSet<Actions>()

            options.forEach {
                if (it != null)
                    set.add(it)
            }

            return set
        }

        trunkFilledHollows.forEach {
            it.animal!!.updateHealth()

            if (it.animal!!.health <= 0)
                it.animal = null

            when (it.animal?.update(createOptions(Actions.Stay, Actions.LeaveHome))) {
                Actions.LeaveHome -> {
                    it.animal = null
                    trunkAnimals.add(it.animal as IAnimal)
                }
            }
        }

        rootFilledHoles.forEach {
            it.animal!!.updateHealth()
            if (it.animal!!.health <= 0)
                it.animal = null

            when (it.animal?.update(createOptions(Actions.Stay, Actions.LeaveHome))) {
                Actions.LeaveHome -> {
                    rootsAnimals.add(it.animal!!)
                    it.animal = null
                }
            }
        }

        var availableHollows = trunk.hollows.count { it.animal != null }
        var availableHoles = roots.holes.count { it.animal != null }

        val hollow = trunk.hollows.firstOrNull()
        val hole = roots.holes.firstOrNull()

        var i = 0
        while (i < crown.animals.size) {
            val currentAnimal = crown.animals[i]

            currentAnimal.updateHealth()

            if (currentAnimal.health <= 0) {
                i++
                continue
            }

            val options = createOptions(Actions.Stay, Actions.MoveDown)

            if (currentAnimal.canFly)
                options.add(Actions.MoveToRandomTree)

            if (crown.animals.any { an -> currentAnimal !== an && currentAnimal.canEat(an) })
                options.add(Actions.EatAnimal)

            if (crown.food.any {food -> currentAnimal.canEat(food)})
                options.add(Actions.EatFood)

            if (crown.animals.any { it !== currentAnimal
                    && it.javaClass.typeName == currentAnimal.javaClass.typeName
                    && it.gender != currentAnimal.gender})
                options.add(Actions.Breed)

            when (currentAnimal.update(options)) {
                Actions.MoveDown -> trunkAnimals.add(currentAnimal)
                Actions.MoveToRandomTree -> {
                    if (forest.canMoveAnimal(this) && currentAnimal.canFly)
                        forest.moveAnimalToRandomTree(currentAnimal, this, crown)
                    else
                        crownAnimals.add(currentAnimal)
                }
                Actions.EatFood -> {
                    val fd = crown.food.firstOrNull { f -> currentAnimal.canEat(f) }

                    if (fd != null) {
                        currentAnimal.eat(fd)
                        crown.removeFood(fd)
                    }

                    crownAnimals.add(currentAnimal)
                }
                Actions.EatAnimal -> {
                    val ind = crown.animals.indexOfFirst { currentAnimal !== it && currentAnimal.canEat(it) }

                    if (ind > -1) {
                        currentAnimal.eat(crown.animals[ind])

                        if (ind < i)
                            i--

                        crown.removeAnimal(crown.animals[ind])
                    }

                    crownAnimals.add(currentAnimal)
                }
                Actions.Breed -> {
                    if (options.contains(Actions.Breed))
                        crownAnimals.add(currentAnimal.createNew())

                    crownAnimals.add(currentAnimal)
                }
                else -> crownAnimals.add(currentAnimal)
            }

            i++
        }

        trunk.animals.forEach {
            it.updateHealth()

            if (it.health <= 0)
                return@forEach

            val options = createOptions(Actions.Stay, Actions.MoveUp, Actions.MoveDown)

            if (availableHollows > 0 && it.isCorrectHome(hollow))
                options.add(Actions.EnterHome)

            if (trunk.food.any {food -> it.canEat(food)})
                options.add(Actions.EatFood)

            if (trunk.animals.any { an -> it !== an && it.canEat(an) })
                options.add(Actions.EatAnimal)

            if (trunk.animals.any { an ->
                    an !== it
                    && an.javaClass.typeName == it.javaClass.typeName
                    && an.gender != it.gender})
                options.add(Actions.Breed)

            when (it.update(options)) {
                Actions.MoveUp -> crownAnimals.add(it)
                Actions.MoveDown -> rootsAnimals.add(it)
                Actions.EnterHome -> if (availableHollows > 0) {
                    availableHollows--
                    trunk.hollows.first { it.animal == null }.animal = it
                }
                Actions.EatFood -> {
                    val fd = trunk.food.firstOrNull { f -> it.canEat(f) }

                    if (fd != null) {
                        it.eat(fd)
                        trunk.removeFood(fd)
                    }

                    trunkAnimals.add(it)
                }
                Actions.Breed -> {
                    if (options.contains(Actions.Breed))
                        trunkAnimals.add(it.createNew())

                    trunkAnimals.add(it)
                }
                else -> trunkAnimals.add(it)
            }
        }

        i = 0
        while (i < roots.animals.size) {
            val currentAnimal = roots.animals[i]

            currentAnimal.updateHealth()

            if (currentAnimal.health <= 0) {
                i++
                continue
            }

            val options = createOptions(Actions.Stay, Actions.MoveUp)

            if (!currentAnimal.canFly)
                options.add(Actions.MoveToRandomTree)

            if (availableHollows > 0 && currentAnimal.isCorrectHome(hole))
                options.add(Actions.EnterHome)

            if (roots.food.any {food -> currentAnimal.canEat(food)})
                options.add(Actions.EatFood)

            if (roots.animals.any { an -> currentAnimal !== an && currentAnimal.canEat(an) })
                options.add(Actions.EatAnimal)

            if (roots.animals.any { it !== currentAnimal
                    && it.javaClass.typeName == currentAnimal.javaClass.typeName
                    && it.gender != currentAnimal.gender})
                options.add(Actions.Breed)

            when (currentAnimal.update(options)) {
                Actions.MoveUp -> trunkAnimals.add(currentAnimal)
                Actions.MoveToRandomTree -> {
                    if (forest.canMoveAnimal(this) && !currentAnimal.canFly)
                        forest.moveAnimalToRandomTree(currentAnimal, this, roots)
                    else
                        rootsAnimals.add(currentAnimal)
                }
                Actions.EnterHome -> if (availableHoles > 0) {
                    availableHoles--
                    roots.holes.first { it.animal == null }.animal = currentAnimal
                }
                Actions.EatFood -> {
                    val fd = roots.food.firstOrNull { f -> currentAnimal.canEat(f) }

                    if (fd != null) {
                        currentAnimal.eat(fd)
                        roots.removeFood(fd)
                    }

                    rootsAnimals.add(currentAnimal)
                }
                Actions.EatAnimal -> {
                    val ind = roots.animals.indexOfFirst { currentAnimal != it && currentAnimal.canEat(it) }

                    if (ind > -1) {
                        currentAnimal.eat(roots.animals[ind])

                        if (ind < i)
                            i--

                        roots.removeAnimal(roots.animals[ind])
                    }

                    rootsAnimals.add(currentAnimal)
                }
                Actions.Breed -> {
                    if (options.contains(Actions.Breed))
                        rootsAnimals.add(currentAnimal.createNew())

                    rootsAnimals.add(currentAnimal)
                }
                else -> rootsAnimals.add(currentAnimal)
            }

            i++
        }

        crown.removeAllAnimals()
        trunk.removeAllAnimals()
        roots.removeAllAnimals()

        crownAnimals.forEach { crown.addAnimal(it) }
        trunkAnimals.forEach { trunk.addAnimal(it) }
        rootsAnimals.forEach { roots.addAnimal(it) }
    }

    fun getAsString(): String {
        return "Возраст: $age\nВысота: $height\nКрона:\n${crown.getAsString().marginString()}\nСтвол:\n${trunk.getAsString().marginString()}\nКорни:\n${roots.getAsString().marginString()}"
    }
}
