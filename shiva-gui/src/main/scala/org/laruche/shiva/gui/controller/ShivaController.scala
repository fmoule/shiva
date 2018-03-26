package org.laruche.shiva.gui.controller

import org.laruche.shiva.gui.ShivaApplication

import scalafxml.core.FXMLLoader

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

object ShivaController {

  /**
    * Méthode permettant de retourner un élément à partir
    * du "loader" et de l'identifiant de l'élément. <br />
    *
    * @param loader : FXML Loader
    * @param id     : Identifiant de l'élément à rechercher
    * @tparam T : Type de l'élément
    * @return élément
    */
  def getElement[T](loader: FXMLLoader, id: String): T = {
    return loader.getNamespace.get(id).asInstanceOf[T]
  }
}
