package game.classes

import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import scalafx.Includes._

case class Bullet(val x: Int, val y: Int, val size: Int, val color: Color) extends Circle {
    
    displayBullet(x, y, size, color)

    def displayBullet(x: Int, y: Int, size: Int, color: Color): Unit = {
        this.centerX_=(x)
        this.centerY_=(y)
        this.radius_=(size)
        this.fill_=(color)
    }

    def this(size: Int, color: Color){
        this(0,0,size,color)
    }
    
}