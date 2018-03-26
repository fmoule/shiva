package org.laruche.shiva.gui.component

import javafx.scene.layout.Pane


/**
  * Trait représentant tous les composants graphiques possédant
  * un espace principal. <br />
  *
  * @author Frédéric Moulé
  */
trait HasMainPane {

  /**
    * Retourne l'espace principal
    * @return
    */
  def mainPane: Pane
}
