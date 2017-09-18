package org.laruche.shiva.commons.beans.project.task

import org.laruche.shiva.commons.beans.project.task.Task.{NOT_FINISHED, TaskStatus}
import org.laruche.shiva.commons.beans.{HasComplexity, HasDescription, HasId}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * <p>
  * Trait représentant les tâches gérées. <br />
  * </p>
  *
  * @author Frédéric Moulé
  */
trait Task extends HasComplexity
  with HasDescription
  with HasId {

  protected val theTasks: mutable.Buffer[Task] = new ListBuffer[Task]
  protected var taskStatus: TaskStatus = NOT_FINISHED


  /**
    * Retourne les sous-tâches de l'instance. <br />
    *
    * @return sous-tâches de la tâche courante
    */
  def children(): collection.Seq[Task] = theTasks

  /**
    * Retourne un booléen permettant de voirr si la tâche est primitive. <br />
    *
    * @return booléen
    */
  def isPrimitive: Boolean = this.theTasks == null || this.theTasks.isEmpty

  def isComposite: Boolean = !this.isPrimitive

  /**
    * Retourne le status de la tâche. <br />
    *
    * @return status de la tâche
    */
  def status(): TaskStatus = this.taskStatus

  def setStatus(status : TaskStatus) : Unit = this.taskStatus = status

  def addTask(task: Task) : Unit = {
    theTasks += task
  }

  def addTask(tasks: collection.Seq[Task]): Unit = {
    for (task <- theTasks) {
      this.addTask(task)
    }
  }

  /// Opérateurs :
  def +=(task: Task): Unit = this.addTask(task)
  def +=(tasks: collection.Seq[Task]): Unit = this.addTask(tasks)

}

object Task {

  sealed class TaskStatus(theId: String) {
    protected val _id: String = theId
    def id(): String = _id
  }

  case object FINISHED extends TaskStatus("finished")
  case object NOT_FINISHED extends TaskStatus("not_finished")

}

