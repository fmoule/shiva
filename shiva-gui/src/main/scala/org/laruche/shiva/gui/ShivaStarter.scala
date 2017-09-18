package org.laruche.shiva.gui

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafxml.core.{FXMLView, NoDependencyResolver}

/**
  * Classe permettant de démarrer l'application graphique
  *
  * @author Frédéric Moulé
  */
object ShivaStarter extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Shiva"
    scene = new Scene(FXMLView(getClass.getResource("MainShivaGUI.fxml"), NoDependencyResolver))
  }
}
