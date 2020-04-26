package me.saket.press.shared.ui

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.observableOfEmpty
import com.badoo.reaktive.subject.publish.PublishSubject

/**
 * @param [Event] UI events being performed by the user.
 * @param [Model] Content model for describing the UI
 * @param [Effect] One-off updates on the UI that cannot be modeled as state in the content
 *                 model. For e.g., updating a text field just once, showing a toast or
 *                 navigating to a new screen.
 */
abstract class Presenter<Event, Model, Effect> {

  private val viewEvents = PublishSubject<Event>()
  protected fun viewEvents(): Observable<Event> = viewEvents

  fun dispatch(viewEvent: Event) {
    viewEvents.onNext(viewEvent)
  }

  abstract fun uiModels(): Observable<Model>

  open fun uiEffects(): Observable<Effect> = observableOfEmpty()
}

sealed class UiUpdate<Model, Effect> {
  data class UiModel<T>(val model: T) : UiUpdate<T, Nothing>()
  data class UiEffect<T>(val effect: T) : UiUpdate<Nothing, T>()
}
