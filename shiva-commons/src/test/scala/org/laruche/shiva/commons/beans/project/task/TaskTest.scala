package org.laruche.shiva.commons.beans.project.task

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.laruche.shiva.commons.beans.project.task.TaskTest.TestTask

class TaskTest {


  @Test
  def test_defaultValue() : Unit = {
    val task = new TestTask("task1")
    assertThat(task.status == Task.NOT_FINISHED)
    assertThat(task.isPrimitive).isTrue
    val subTasks = task.children()
    assertThat(subTasks).isNotNull
    assertThat(subTasks.isEmpty).isTrue()
  }

}

object TaskTest {

  class TestTask(id: String) extends Task {
    override protected val theId: String = id
    override protected var desc: String = _
    override protected var complexity: Int = _
  }
}
