package game.classes
import scalafx.scene.paint.Color



abstract class Weapon(var name: String, var fireRate: Int, var damage: Int, var icon : String ){

    def fire(): Bullet 
}
class Vandal(a: String, b: Int, c: Int, d: String) extends Weapon(a, b, c, d){

    def this(){
        this("Vandal", 1, 50, "file:src/main/resources/game/images/primeVandal.png")
    }
    def fire(): Bullet ={
        new Bullet(5, Color.BLACK)
    }
}
class Spectre(a: String, b: Int, c: Int, d: String) extends Weapon(a, b, c, d){

    def this(){
        this("Spectre", 3, 30, "file:src/main/resources/game/images/spectre.png")
    }    
    def fire(): Bullet ={        
        new Bullet(3, Color.LIGHTBLUE)
    }
}

class Phantom(a: String, b: Int, c: Int, d: String) extends Weapon(a, b, c, d){

    def this(){
        this("Phantom", 2, 40, "file:src/main/resources/game/images/blastPhantom.png")
    }
    def fire(): Bullet ={     
        new Bullet( 4, Color.PINK)
    }
}

class P90(a: String, b: Int, c: Int, d: String) extends Weapon(a, b, c, d){

    def this(){
        this("P90", 4, 20, "file:src/main/resources/game/images/p90.png")
    }
    def fire(): Bullet ={      
        new Bullet(2, Color.YELLOW)
    }
}

