package game.view

import scalafxml.core.macros.sfxml
import game.MainApp


@sfxml
class YouWinController (


){

// Display pre game page if the player chooses to play again
    def playAgain(){

        MainApp.setGame()

    }

// Display main page if the player chooses to go back to the main page
    def mainMenu(){

        MainApp.showMenu()
    }


}