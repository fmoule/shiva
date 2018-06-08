package org.laruche.shiva.commons.beans.commons

trait HasName {
  protected var _name: String = ""

  /**
    * Retourne le nom de l'entité concerné par le trait
    *
    * @return nom
    */
  def name: String = return _name
  def name_=(name: String) : Unit = this._name = name

}
