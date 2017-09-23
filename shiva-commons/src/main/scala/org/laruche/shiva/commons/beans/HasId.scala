package org.laruche.shiva.commons.beans

/**
  * <p>
  * Trait représentant les objets ayant un identifiant. <br />
  * </p>
  *
  * @author Frédéric Moulé
  */
trait HasId {
  protected val theId: String

  /**
    * Retourne l'identifiant
    *
    * @return identifiant
    */
  def id: String = {
    return theId
  }

}
