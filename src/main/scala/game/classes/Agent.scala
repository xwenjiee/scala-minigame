package game.classes
import scalafx.scene.media.{Media, MediaPlayer, MediaView}
import java.io.File

abstract class Agent(var name: String, val weapon : Weapon,  var icon : String){

    var health: Int = 100

    def shoot():Bullet ={

        weapon.fire()
    }
    def aimUp(): Int ={
        -20
    }
    def aimDown(): Int = {      
        0
    }
}
class Jett(a: String, b: Weapon, c: String) extends Agent(a, b, c){

    def this(){
        this("Jett",new Vandal(), "file:src/main/resources/game/images/jett.png")
    }

}

class Viper(a: String, b: Weapon, c: String) extends Agent(a, b, c){

    def this(){
        this("Viper",new P90(), "file:src/main/resources/game/images/viper.png")
    }

}
class Sage(a: String, b: Weapon, c: String ) extends Agent(a, b, c){
    
    def this(){
        this("Sage",new Phantom(),"file:src/main/resources/game/images/sage.png")
    }

   
}
class Chamber(a: String, b: Weapon, c: String) extends Agent(a, b, c){

    def this(){
        this("Chamber",new Spectre(), "file:src/main/resources/game/images/chamber.png")
    }

}


