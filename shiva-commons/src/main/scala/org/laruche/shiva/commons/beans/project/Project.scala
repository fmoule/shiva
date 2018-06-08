package org.laruche.shiva.commons.beans.project

import org.laruche.shiva.commons.beans.commons.HasId
import org.laruche.shiva.commons.beans.project.task.Task

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Classe représentant les projets. <br />
  *
  * @param id : identifiant du projet
  */
class Project(id: String) extends HasId with collection.Iterable[Task] {
  override protected val theId: String = id
  private var theTasks: mutable.Buffer[Task] = new ListBuffer[Task]

  ///// Méthodes de la classe Object :

  override def hashCode(): Int = {
    return this.theId.hashCode
  }

  /**
    * Méthode permettant de tester l'égalité avec un autre objet. <br />
    *
    * @param obj : objet à tester
    * @return booléen montrant l'égalité
    */
  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case proj: Project => {
        return this.id == proj.id
      }
      case _ => false
    }
  }

  ////// Méthode de l'interface Iterable[Task]

  override def iterator: Iterator[Task] = {
    return this.theTasks.clone().iterator
  }

  ///// Méthodes générales :

  /**
    * Retourne la liste des tâches
    *
    * @return tâches des projets
    */
  def tasks: collection.Seq[Task] = return this.theTasks


  /**
    * Ajoute une tâche au projet courant
    *
    * @param task : Tâche
    */
  def addTask(task: Task): Unit = theTasks += task
  def addTask(tasks: collection.Seq[Task]) : Unit = {
    if (tasks == null || tasks.isEmpty) {
      return
    }
    tasks.toStream.foreach(tsk => this.addTask(tsk))
  }

  /**
    * Méthode permettant de récupérer la liste des tâches filtrée
    * par la fonction passée en paramètre. <br />
    *
    * @param filter : filtre des tâches
    * @return
    */
  def filterTasks(filter: Task => Boolean): collection.Seq[Task] = {
    filter match {
      case null => return this.tasks
      case _ => return this.theTasks.filter(filter)
    }
  }

  /**
    * Retourne le nombre de tâches constituant le projet. <br />
    *
    * @return nombre des tâches
    */
  def nbTasks: Int = return theTasks.size

  def nbFinishedTasks: Int = {
    if (theTasks == null || theTasks.isEmpty) {
      return 0
    }
    return theTasks.toStream.count(tsk => tsk.status() == Task.FINISHED)
  }

  ///// Opérateurs

  /**
    * Appel à la fonction addTask
    *
    * @param task : tâche à ajouter
    */
  def +=(task: Task): Unit = this.addTask(task)
  def +=(tasks: collection.Seq[Task]): Unit = this.addTask(tasks)

}
