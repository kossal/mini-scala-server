package frontend

import com.raquo.laminar.api.L._
import org.scalajs.dom

object Counter {

  sealed trait Action
  case object Increment extends Action
  case object Decrement extends Action

  case class State(
      counter: Int,
      interactions: Int
  )

  val stateBus  = new EventBus[State]
  val actionBus = new EventBus[Action]

  val initialState = State(counter = 0, interactions = 0)

  val currentState: Signal[State] =
    stateBus.events.toSignal(initialState)

  val updatedStates: EventStream[State] =
    actionBus.events.withCurrentValueOf(currentState).map {
      case (Increment, state) =>
        state.copy(
          counter = state.counter + 1,
          interactions = state.interactions + 1
        )
      case (Decrement, state) =>
        state.copy(
          counter = state.counter - 1,
          interactions = state.interactions + 1
        )
    }


  def view = {
    div(
      updatedStates --> stateBus.writer,
      h4(color := "red", "Counter ", child.text <-- currentState.map(_.counter.toString)),
      p(color := "green", "Interactions ", child.text <-- currentState.map(_.interactions.toString)),
      button(
        "Increment",
        onClick.mapToValue(Increment) --> actionBus.writer
      ),
      button(
        "Decrement",
        onClick.mapToValue(Decrement) --> actionBus.writer
      )
    )
  }

}

object Main extends App {

  render(dom.document.getElementById("app"), Counter.view)

}
