package org.laruche.shiva.gui.controller.project

import javafx.scene.layout.Pane
import org.laruche.shiva.gui.ShivaApplication
import org.laruche.shiva.gui.component.HasMainPane
import org.laruche.shiva.gui.controller.ShivaController
import scalafxml.core.macros.sfxml


///// Définition de l'écran :

trait ProjectController extends ShivaController
  with HasMainPane {

}

@sfxml
class ProjectControllerImpl(shivaAppli: ShivaApplication,
                            theMainPane: Pane)
  extends ProjectController {

  override protected var _shivaApplication: ShivaApplication = shivaAppli
  val _mainPane: Pane = theMainPane


  ///// Getters & Setters :

  /**
    * Retourne l'espace principal
    *
    * @return
    */
  override def mainPane: Pane = this._mainPane
}