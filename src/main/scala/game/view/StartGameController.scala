package  game.view


import game.MainApp
import scalafx.scene.control.{Label, Alert, ButtonType}

// Bullet travel
import scalafx.scene.layout.AnchorPane
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

//Bullet travel 2.0
import scalafx.animation.TranslateTransition
import scalafx.util.Duration
import scalafx.scene.paint.Color
import scalafx.animation.Animation
import scalafx.event.EventHandler

//logic
import scalafx.beans.binding.Bindings;
import scalafx.beans.binding.BooleanBinding;
import scalafx.geometry.Point2D;

import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.input.MouseEvent
import game.classes.{Jett, Viper, Chamber, Sage, Agent, Enemy, FlyMonster, Zombie}
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import game.classes.Bullet

import game.view.SetGameController


@sfxml
class StartGameController (

  private var playerCharacter : ImageView,
  private var playerWeapon : ImageView,
  private var enemyHP : Label,
  private var enemiesRemain : Label,
  private var root: AnchorPane  

){

    
    var enemies = MainApp.s.enemies
    
  
    var dialogStage: Stage = null


    // set the player's selected agent and weapon icon when the game starts
    var agentIcon = new Image(MainApp.s.selection.icon);
    var weapon = new Image(MainApp.s.selection.weapon.icon);
    playerCharacter.setImage(agentIcon)
    playerWeapon.setImage(weapon)

    playerCharacter.setFitWidth(100)
    playerWeapon.setFitWidth(100)
    playerCharacter.setPreserveRatio(true);
    playerWeapon.setPreserveRatio(true);


    var enemyCount: Int = enemies.size

    var tracker: Int = 0
    var characterHP : Int = MainApp.s.selection.health
    var enemyMotion: TranslateTransition =null
    var targetMotion: TranslateTransition = null


    // Receive key input of player
    root.onKeyPressed = (e: KeyEvent) => {

        // If player presses "ENTER" pause the game and ask the player to confirm their option
        if(e.getCode().toString()=="ENTER"){
          
        val alert = new Alert(Alert.AlertType.Confirmation){

          enemyMotion.stop() 
          targetMotion.stop()
          initOwner(dialogStage)
          title = "Game Paused"
          headerText = "Are you sure you want to quit the game?"
          contentText = "Click OK to exit and CANCEL to resume game"
    
        }
        // Handle the player's choice to "QUIT GAME" or "CANCEL"
        var result = alert.showAndWait();

        result match {
          case Some(ButtonType.OK) => {
            MainApp.showMenu()
          }
          case _                   => {
            enemyMotion.play()
            targetMotion.play()
            }
        }         
        }
        // If player presses "w" aim the weapon upwards
        else if(e.getCode().toString()=="W"){
          playerWeapon.setRotate(MainApp.s.selection.aimUp);

        }
        // If player presses "S" aim the weapon downwards
        else if(e.getCode().toString()=="S"){

          playerWeapon.setRotate(MainApp.s.selection.aimDown);

        }         
    }

    val PX0 : Int = playerCharacter.getLayoutX().toInt+playerCharacter.getFitWidth().toInt
    val PY0 : Int = playerCharacter.getLayoutY().toInt
    // Set a pointer for the character's position
    val playerPointer = new Bullet(PX0,PY0, 0, Color.BLACK)
    root.children.add(playerPointer)

    // Spawn enemies
    def spawnEnemies(x: Enemy): Unit ={

      enemyHP.text=x.health.toString
      enemiesRemain.text = enemyCount.toString


        
        var enemyX:Int =0
        var enemyY: Int=0
        var travelDistance: Int = 700

        // Set the position of the enemy according to their type
        if(x.isInstanceOf[FlyMonster]){
          enemyX=800
          enemyY=100
          
        }
        else if (x.isInstanceOf[Zombie]){
          enemyX=800
          enemyY=395

        }

        var enemy = new ImageView(new Image(x.icon))
        enemyMotion = new TranslateTransition(Duration(10000/x.movement),enemy)
        enemy.setLayoutX(enemyX) 
        enemy.setLayoutY(enemyY)
        enemy.setFitWidth(100)
        enemy.setPreserveRatio(true)

        enemyMotion.setByX(-travelDistance)
        enemyMotion.setByY(x.move())
        enemyMotion.play()   

        root.children.add(enemy)

        var enemy2 = new Bullet(enemyX, enemyY+50,0, Color.BLACK);
        targetMotion = new TranslateTransition(Duration(10000/x.movement),enemy2)        
        targetMotion.setByX(-travelDistance);
        targetMotion.setByY(x.move())
        targetMotion.play();
        root.children.add(enemy2)
    
      // If the player clicks their mouse, then the weapon will fire a bullet
      root.onMousePressed = (e: MouseEvent) => {
                
        var X0: Int = (playerWeapon.getLayoutX().toInt+playerWeapon.getFitWidth().toInt)
        var Y0: Int = (playerWeapon.getLayoutY().toInt+playerWeapon.getRotate().toInt+10)

        val bullet = new Bullet(X0,Y0, MainApp.s.selection.shoot.size, MainApp.s.selection.shoot.color)

        // Set where the bullet travels based on the angle of the weapon
        val transition = new TranslateTransition(Duration(1000/MainApp.s.selection.weapon.fireRate),bullet)
        transition.setByX(root.getWidth().toInt-playerWeapon.getLayoutX().toInt+playerWeapon.getFitWidth().toInt)
        transition.setByY(playerWeapon.getRotate().toInt*25)
                

                // If the bullet hits the enemy, deduct the enemy's health based on the weapon's damage 
                val hit  = new BooleanBinding (Bindings.createBooleanBinding(()=> { 
                var targetLocation = new Point2D(enemy2.localToParent(enemyX, enemyY+50))
                var projectileLocation = new Point2D(bullet.localToParent(X0, Y0))
                    
                    (targetLocation.distance(projectileLocation) < 50 + 5) 

                }, bullet.translateXProperty(), bullet.translateYProperty()))

                hit.addListener((obs, wasHit, isNowHit) => {
                    if (isNowHit) {

                        x.health=x.health-MainApp.s.selection.weapon.damage
                        root.getChildren().remove(bullet);
                        transition.stop();

                    }
             
                });

                // Once the enemy's health has reached zero or lower, remove the enemy, spawn the next enemy and decrease the number of remaining enemies by 1
                if(x.health<=0){
                          root.getChildren().remove(enemy);
                          root.getChildren().remove(enemy2);
                          enemyMotion.stop();
                          targetMotion.stop();
                          


                          enemyCount= enemyCount-1
                          tracker=tracker+1

                          if(tracker!=enemies.size){
                            
                            // Spawn the next enemy
                            spawnEnemies(enemies(tracker))
                          }

                          // Display game over page once there are no more enemies left in the list of 15 enemies
                          else{
                            MainApp.showWin()
                          }
                }
                // If the enemy is not dead yet, update the enemy's health
                else{
                  enemyHP.text=x.health.toString

                }
        
        root.children.add(bullet)
        transition.play()
      }
      
      
                // If the enemy hits the player, deduct the player's health based on the enemy's attack 
                val hitPlayer  = new BooleanBinding (Bindings.createBooleanBinding(()=> { 
                var characterLocation = new Point2D(PX0,PY0)
                var enemyLocation = new Point2D(enemy2.localToParent(enemyX, enemyY+50))
                    
                (characterLocation.distance(enemyLocation) < 50 + 5) 

                }, enemy2.translateXProperty(), enemy2.translateYProperty()))

                hitPlayer.addListener((obs, wasHit, isNowHit) => {
                    if (isNowHit) {

                      root.getChildren().remove(playerCharacter);
                      root.getChildren().remove(playerPointer);
                      root.getChildren().remove(playerWeapon)
                      enemyMotion.stop();
                      targetMotion.stop();  
                      
                      characterHP=characterHP-x.attack()
                      
                   
                    }

                    // If the player's health reached 0 or lower, display game over page
                    if(characterHP<=0){

                    MainApp.showGameOver()
                    
                }
                    
                });

    }

  // Spawn the first enemy on game start
  spawnEnemies(enemies(tracker))


}

    
 