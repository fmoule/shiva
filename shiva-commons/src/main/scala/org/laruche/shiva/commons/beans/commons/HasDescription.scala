package org.laruche.shiva.commons.beans.commons

/**
  * <p>
  * Trait représentant les objets comportant une description. <br />
  * </p>
  *
  * @author Frédéric Moulé
  */
trait HasDescription {
  protected var desc: String

  /**
    * Méthode permettant de retourner la description
    *
    * @return description de la tâche
    */
  def description: String = {
    return desc
  }

  /**
    * Définit la description
    *
    * @param desc : Description
    */
  def setDescription(desc: String): Unit = {
    this.desc = desc
  }
}
