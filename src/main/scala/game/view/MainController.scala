package game.view

import game.MainApp
import scalafxml.core.macros.sfxml
import scalafx.scene.layout.AnchorPane

import scalafx.event.ActionEvent

@sfxml
class MainController (
    

){

// Display game instructions to the player
def showInstructions(action :ActionEvent) {

    MainApp.showInstructions();
       
}
// Direct player to pre game page
def setGame(action :ActionEvent) {

    MainApp.setGame();
       
}
// Close the game
def exit(action :ActionEvent) {

    MainApp.stage.close();
       
}

  
}