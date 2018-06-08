package org.laruche.shiva.commons.beans.commons

/**
  * <p>
  * Trait représentant tous les objets comportant une complexité
  * </p>
  *
  * @author Frédéric Moulé
  */
trait HasComplexity {
  protected var complexity: Int

  /**
    * Retourne la complexité
    *
    * @return complexité
    */
  def getComplexity: Int = {
    return this.complexity
  }

  /**
    * Initialise la complexité
    *
    * @param compl : complexité
    */
  def setComplexity(compl: Int) = {
    this.complexity = compl
  }

}
