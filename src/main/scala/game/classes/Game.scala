package game.classes
import scala.util.Random
import scala.collection.mutable.ArrayBuffer

class Game(x: Agent){

    var selection = x
    var enemies = new ArrayBuffer[Enemy]()

    var r = scala.util.Random
    def generateEnemies(){
    // Generate a list of 15 random enemies of Zombie or Fly Monster
    while(enemies.size!=15){
      if(r.nextInt(2)==0){
        enemies += new FlyMonster()
      }
      else if(r.nextInt(2)==1){
        enemies += new Zombie()
      }
    } 
    }
    generateEnemies()
}