package game.view

import game.MainApp
import scalafxml.core.macros.sfxml

import scalafx.event.ActionEvent

@sfxml
class InstructionsController (

){

// Go back to the main page after player reads the instructions and clicks "OK"
def Okay(action :ActionEvent) {

    MainApp.showMenu();
       
}

  
}