package org.laruche.shiva.commons.beans

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.laruche.shiva.commons.beans.ProjectTest.{TestTask, createFinishedTask}
import org.laruche.shiva.commons.beans.project.Project
import org.laruche.shiva.commons.beans.project.task.Task

class ProjectTest {


  @Test
  def test_equals(): Unit = {
    val project = new Project("id")
    assertThat(project) isEqualTo new Project("id")
    assertThat(project.hashCode()) isEqualTo new Project("id").hashCode()
    assertThat(project) isNotEqualTo null
  }


  @Test
  def test_filter(): Unit = {
    val project = new Project("test")
    project += new TestTask("task1")
    project += new TestTask("task2")
    project += new TestTask("otherTask")
    assertThat(project.nbTasks).isEqualTo(3)
    assertThat(project.filterTasks(task => task.id.startsWith("task")).size).isEqualTo(2)
    assertThat(project.filterTasks(null).size).isEqualTo(3)
  }


  @Test
  def test_nbFinishedTask(): Unit = {
    val project = new Project("test")
    project += new TestTask("task1")
    project += new TestTask("task2")
    project += createFinishedTask("task3")
    project += createFinishedTask("task4")
    assertThat(project.nbFinishedTasks).isEqualTo(2)
  }


  @Test
  def test_iterator(): Unit = {
    val project = new Project("test")
    project.addTask(new TestTask("1"))
    project.addTask(new TestTask("2"))
    var count: Int = 0
    for (task: Task <- project) {
      count += 1
    }
    assertThat(count).isEqualTo(2)
  }

}


object ProjectTest {

  private class TestTask(id: String) extends Task {
    override protected var complexity: Int = _
    override protected val theId: String = id
    override protected var desc: String = _

  }

  def createFinishedTask(id: String): Task = {
    val task = new TestTask(id)
    task.setStatus(Task.FINISHED)
    return task
  }

}
