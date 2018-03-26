package org.laruche.shiva.core.dao

import org.laruche.shiva.commons.beans.other.Clearable

import scala.collection.mutable

/**
  * Trait définissant les Dao's utilisés par Shiva
  *
  * @tparam T : Type conccerné par les DAO's
  * @author Frédéric Moulé
  */
trait DAO[T] extends Clearable with Iterable[T] {

  ///// Méthode du trait Clearable & Iterable

  override def clear(): Unit = this.delete()
  override def iterator: Iterator[T] = this.select().iterator

  ///// Méthodes du trait DAO :

  @throws[DaoException]
  def select(predicate: T => Boolean): Seq[T]

  @throws[DaoException]
  //noinspection ScalaUnusedSymbol
  def select(): Seq[T] = this.select(bean => true)

  @throws[DaoException]
  def delete(predicate: T => Boolean): Unit

  @throws[DaoException]
  //noinspection ScalaUnusedSymbol
  def delete(): Unit = this.delete(bean => true)

  @throws[DaoException]
  def insert(beans: T*): Unit

  def values_=(vals: Seq[T]): Unit = {
    if (vals == null || vals.isEmpty) {
      return
    }
    this.clear()
    this.insert(vals:_*)
  }

  def values: Seq[T] = this.select()
}

/**
  * Simple implémentation avec une table de hachage
  *
  * @tparam T : Type conccerné par les DAO's
  */
class SetDao[T] extends DAO[T] {
  private val beanSet: mutable.Set[T] = new mutable.HashSet[T]

  override def select(predicate: (T) => Boolean): Seq[T] = beanSet.filter(predicate).toSeq

  override def delete(predicate: (T) => Boolean): Unit = {
    val beansToDelete: Seq[T] = this.select(predicate)
    for (bean <- beansToDelete) {
      beanSet.remove(bean)
    }
  }

  override def insert(beans: T*): Unit = {
    if (beans == null || beans.isEmpty) {
      return
    }
    beans.toStream.foreach(bean => beanSet.add(bean))
  }


}

