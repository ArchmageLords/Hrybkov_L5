package com.example.lection5

data class Person(
    val name: String,
    val surname: String,
    val age: Int,
    var mother: Person? = null,
    var father: Person? = null,
    var relatives: Array<Person>? = null
) {
    var numberOfRelatives = 0
    var listOfRelatives: MutableList<Person> = mutableListOf()

    override fun toString(): String {
        return this.name + " " + this.surname
    }

    fun countRelatives(person: Person){
        this.mother?.let {
            person.listOfRelatives.add(it)
            person.numberOfRelatives++
            it.countRelatives(person)
        }
        this.father?.let {
            person.listOfRelatives.add(it)
            person.numberOfRelatives++
            it.countRelatives(person)
        }
        this.relatives?.let {
            it.forEach {
                person.listOfRelatives.add(it)
                person.numberOfRelatives++
                it.countRelatives(person)
            }
        }
    }
}

fun main() {
    val me = getMe()
    me.countRelatives(me)
    println("$me have ${me.numberOfRelatives} relatives. It is: ${me.listOfRelatives}")
}

fun getMe(): Person {
    var me = Person("Dmitry", "Hrybkov", 19)
    var brother = Person("Andrew", "Skywalker", 14)
    var cousin = Person("Kirill", "Solo", 26)
    var uncle = Person("Oleksandr", "Solo", 50)
    var fatherBrother = Person("Oleg", "Jinn", 45)

    me.mother = Person("Tatiana", "Organa", 46)
    me.father = Person("Igor", "Kenobi", 47)
    me.relatives = arrayOf(brother, cousin)
    me.mother!!.mother = Person("Tamara", "Amidala", 75)
    me.mother!!.father = Person("Viktor", "Vader", 80)
    me.mother!!.relatives = arrayOf(uncle)
    me.father!!.mother = Person("Nina", "Naberrie", 86)
    me.father!!.father = Person("Anatoly", "Jarrus", 84)
    me.father!!.relatives = arrayOf(fatherBrother)

    return me
}