package game

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import javafx.{scene => jfxs}
import scalafx.scene.image.Image


import game.classes.{Agent}
import game.classes.Game

import scalafx.scene.media.{Media, MediaPlayer}

object MainApp extends JFXApp {


  var s: Game = null
  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load(); 
  // retrieve the root component BorderPane from the FXML 
  val roots = loader.getRoot[jfxs.layout.BorderPane]



  // initialize stage
  stage = new PrimaryStage {
    title = "Game"
    icons += new Image("file:src/main/resources/game/images/logo.png")
    scene = new Scene {
      root = roots
    }
  }
 stage.resizable= false


  // Play theme song upon launching the game
  var mainSound = new Media(getClass.getResource("audio/themesong.mp3").toURI().toString())
  var mainPlayer = new MediaPlayer(mainSound) {
    volume = 100
    cycleCount = 1
  }

  //This method shows the main page
  def showMenu() = {

    mainPlayer.play

    val resource = getClass.getResource("view/menu.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 

  // Display the main page when the program starts
  showMenu()

  //This method shows the game over page 
  def showInstructions() {

    val resource = getClass.getResource("view/instructions.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
       
}

//This method shows the pre game page
def setGame(){

  val resource = getClass.getResource("view/setGame.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  
}

//This method starts the game by taking in the player's agent selection
def startGame(x: Agent){


    this.s = new Game(x)
    val resource = getClass.getResource("view/startGame.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)

  
}

//This method shows the game over screen
  def showGameOver(){

    val resource = getClass.getResource("view/gameOver.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  
}
//This method shows the victory screen
  def showWin(){

    val resource = getClass.getResource("view/youWin.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  
}
    
    
  }
 




