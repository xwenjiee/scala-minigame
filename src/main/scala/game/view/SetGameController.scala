package  game.view

import game.MainApp
import scalafx.scene.control.{Label, Alert, RadioButton}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.event.ActionEvent
import javafx.scene.control.ToggleGroup
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import game.classes.{Jett, Viper, Chamber, Sage, Agent, FlyMonster, Zombie, Spectre, P90, Phantom, Vandal, Weapon, Enemy}

@sfxml
class SetGameController (

    private val     selecta1 : RadioButton,
    private val selecta2 : RadioButton,
    private val       selecta3 : RadioButton,
    private val   selecta4 : RadioButton,
    private val a1Icon : ImageView,
    private val a2Icon : ImageView,
    private val a3Icon : ImageView,    
    private val a4Icon : ImageView,

    private val a1Icon2 : ImageView,
    private val a2Icon2 : ImageView,
    private val a3Icon2 : ImageView,    
    private val a4Icon2 : ImageView,
    private val a1Name : Label,
    private val a2Name : Label,
    private val a3Name : Label,
    private val a4Name : Label,
    private val a1Weapon : Label,
    private val a2Weapon : Label,
    private val a3Weapon : Label,
    private val a4Weapon : Label,

    private val w1Icon : ImageView,
    private val w2Icon : ImageView,
    private val w3Icon : ImageView,    
    private val w4Icon : ImageView,
    private val w1Name : Label,
    private val w2Name : Label,
    private val w3Name : Label,
    private val w4Name : Label,
    private val w1Damage : Label, 
    private val w2Damage : Label,
    private val w3Damage : Label,
    private val w4Damage : Label,
    private val w1Fire : Label, 
    private val w2Fire : Label,
    private val w3Fire : Label,
    private val w4Fire : Label,

    private val e1Icon : ImageView,
    private val e2Icon : ImageView,
    private val e1Name : Label,
    private val e2Name : Label,
    private val e1Speed : Label,
    private val e2Speed : Label,
    private val e1Type : Label,
    private val e2Type : Label,

    var player1: Agent
){

    var   dialogStage : Stage  = null
    
    // Character/Weapon Info
    var a1 : Agent = new Jett()
    var a2: Agent = new Chamber()
    var a3 : Agent = new Viper()
    var a4 : Agent = new Sage()

    var w1 : Weapon = new Vandal()
    var w2: Weapon = new Spectre()
    var w3 : Weapon = new P90()
    var w4 : Weapon = new Phantom()

    var e1: Zombie = new Zombie()
    var e2: FlyMonster = new FlyMonster()


    var agentSet: List[Agent] = List(a1, a2, a3, a4)
    var weaponSet: List[Weapon] = List(w1, w2, w3, w4)
    var enemySet: List[Enemy] = List(e1, e2)

    var selectButton: List[RadioButton] = List(selecta1, selecta2, selecta3, selecta4)
    var iconSet: List[ImageView] = List(a1Icon, a2Icon, a3Icon, a4Icon)

    var iconSet2: List[ImageView] = List(a1Icon2, a2Icon2, a3Icon2, a4Icon2)
    var agentNameSet: List[Label] = List(a1Name, a2Name, a3Name, a4Name)
    var agentWeaponSet: List[Label] = List(a1Weapon, a2Weapon, a3Weapon, a4Weapon)

    var iconSet3: List[ImageView] = List(w1Icon, w2Icon, w3Icon, w4Icon)
    var weaponName: List[Label] = List(w1Name, w2Name, w3Name, w4Name)
    var weaponDamage: List[Label]= List(w1Damage, w2Damage, w3Damage, w4Damage)
    var weaponFire: List[Label]= List(w1Fire, w2Fire, w3Fire, w4Fire)

    var iconSet4: List[ImageView] = List(e1Icon, e2Icon)
    var enemyName: List[Label] = List(e1Name, e2Name)
    var enemySpeed: List[Label] = List(e1Speed, e2Speed)
    var enemyType: List[Label] = List(e1Type, e2Type)

    // Set information
    for( a <- 0 to agentSet.size-1){

    var agentIcon = new Image(agentSet(a).icon);
    var weaponIcon = new Image(agentSet(a).weapon.icon);


    iconSet(a).setImage(agentIcon)
    selectButton(a).text = agentSet(a).name

    iconSet2(a).setImage(agentIcon)
    agentNameSet(a).text = agentSet(a).name
    agentWeaponSet(a).text = agentSet(a).weapon.name

      }

      for(a <- 0 to weaponSet.size-1){

        var weaponIcon = new Image(weaponSet(a).icon);

        iconSet3(a).setImage(weaponIcon)
        weaponName(a).text = weaponSet(a).name
        weaponDamage(a).text = weaponSet(a).damage.toString
        weaponFire(a).text = weaponSet(a).fireRate.toString

      }
      
      for(a <- 0 to enemySet.size-1){

        var enemyIcon = new Image(enemySet(a).icon);

        iconSet4(a).setImage(enemyIcon)
        enemyName(a).text = enemySet(a).name
        enemySpeed(a).text = enemySet(a).movement.toString
        enemyType(a).text = enemySet(a).movementType

      }
 
    // Allow only one selection at a time
    private val tGroup: ToggleGroup  = new ToggleGroup();
    selecta1.setToggleGroup(tGroup)
    selecta2.setToggleGroup(tGroup)
    selecta3.setToggleGroup(tGroup)
    selecta4.setToggleGroup(tGroup)

// Go back to main page
def back(action :ActionEvent) {

    MainApp.showMenu();
       
}


// Handle player agent selection
def agentSelection(){


    if(selecta1.isSelected()){
        player1 = new Jett()

    }
    else if(selecta2.isSelected()){

        player1 = new Chamber() 
         
    }
    else if(selecta3.isSelected()){
         player1 = new Viper() 
    }
    else if(selecta4.isSelected()){

         player1 = new Sage()   

    }
  
}

// Check if an agent is selected by the player
def checkSelect(action :ActionEvent) {
  
    // If an agent is selected, start the game
    if(tGroup.getSelectedToggle()!= null){

        MainApp.startGame(player1)
    
    }

    // Display error message if no agent is selected
    else{

        val alert = new Alert(Alert.AlertType.Error){
          initOwner(dialogStage)
          title = "Agent Select Error"
          headerText = "No agent selected!"
          contentText = "Please select an agent before starting the game!"
    
        }.showAndWait()       
       
}

  
}
}
