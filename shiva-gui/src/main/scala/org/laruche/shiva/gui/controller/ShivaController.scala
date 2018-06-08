package org.laruche.shiva.gui.controller

import org.laruche.shiva.gui.ShivaApplication

import scalafxml.core.FXMLLoader

/**
  * Trait définissant tous les controlleurs
  * de l'application Shiva. <br />
  *
  * @author Frédéric Moulé
  */
trait ShivaController {
  protected var _shivaApplication: ShivaApplication

  /**
    * Retourne l'application en cours
    *
    * @return application Shiva
    */
  def application: ShivaApplication = this._shivaApplication

  /**
    * Initialisation de l'objet ShivaApplication.
    *
    * @param appli : objet application
    */
  def application_=(appli: ShivaApplication): Unit = {
    this._shivaApplication = appli
  }

}
