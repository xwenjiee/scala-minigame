package game.classes

abstract class Enemy(var name: String, var movement: Double, var icon : String, var movementType: String){

    var health: Int = 100
    def attack(): Int={
        100

    }
    def move():Int 

}

class Zombie(a: String, b: Double, c: String, d: String) extends Enemy(a, b, c, d){

    def this(){
        this("Zombie", 3.5, "file:src/main/resources/game/images/zombie.gif", "Walking")
    }
    def move():Int ={
        0

    }

}
class FlyMonster(a: String, b: Double, c: String, d: String) extends Enemy(a, b, c, d){


    def this(){
        this("Fly Monster", 4, "file:src/main/resources/game/images/cuteFlyingMonster.png", "Flying")
    }
    def move():Int ={
        280

    }

}
