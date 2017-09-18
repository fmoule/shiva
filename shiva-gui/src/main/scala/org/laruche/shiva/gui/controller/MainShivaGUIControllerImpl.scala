package org.laruche.shiva.gui.controller

import javafx.{fxml => jfxf}

import org.slf4j.{Logger, LoggerFactory}

import scalafx.scene.layout.Pane
import scalafxml.core.macros.sfxml

///// Interface

trait MainShivaGuiController extends HasMainPane {
  protected val LOG: Logger = LoggerFactory.getLogger(classOf[MainShivaGUIControllerImpl])
}

///// Implémentation

@sfxml
class MainShivaGUIControllerImpl extends MainShivaGuiController {

  @jfxf.FXML
  private var theMainPane: Pane = _

  def onExit(): Unit = {
    LOG.info("==== Arrêt de l'application")
    System.exit(0)
  }

  /**
    * Retourne l'espace principale
    *
    * @return espace principal
    */
  override def mainPane: Pane = this.theMainPane
}