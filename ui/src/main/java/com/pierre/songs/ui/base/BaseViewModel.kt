package com.pierre.songs.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(
   // private val logger: Logger
) : ViewModel() {
//
//    private val disposables = CompositeDisposable()
//
//    /**
//     * This [ReplayProcessor] have the same function as a [MutableLiveData],
//     * but also bring the power of the reactive world with it.
//     *
//     * Our ViewModels will be able to update it, notifying any reactive Observer,
//     * updating at the same time [uiModelLiveData] for our Fragments observing the fields.
//     */
//    private val _uiModelPublisher = ReplayProcessor.create<State<UI_MODEL>>()
//    val uiModelLiveData: LiveData<State<UI_MODEL>> = _uiModelPublisher
//        .toLiveData()
//        .distinctUntilChanged()
//
//    private val _uiModelUpdater = PublishSubject.create<(UI_MODEL) -> UI_MODEL>()
//
//    init {
//        observeUpdates()
//    }
//
//    private fun observeUpdates() {
//        _uiModelUpdater.withLatestFrom(_uiModelPublisher.toObservable())
//            .map { (updateBlock, uiModelState) ->
//                updateBlock(
//                    when (uiModelState is State.Success) {
//                        true -> uiModelState.data
//                        false -> initialUiModel()
//                    }
//                )
//            }
//            .subscribeOnIoObserveOnMain()
//            .subscribe(::updateUiModel, logger::logException)
//            .autoDispose()
//    }
//
//    /**
//     * Call this block to reactively post an update of the current ui model.
//     *
//     * If no [UI_MODEL] is found (for instance because it's in a Loading state), then
//     * the [initialUiModel] is provided for the caller.
//     */
//    protected fun updateUiModel(updateBlock: (UI_MODEL) -> UI_MODEL) {
//        _uiModelUpdater.onNext(updateBlock)
//    }
//
//    abstract fun initialUiModel(): UI_MODEL
//
//    protected fun updateUiModel(uiModel: UI_MODEL) {
//        _uiModelPublisher.onNext(State.Success(uiModel))
//    }
//
//    protected fun dispatchError(
//        tag: String? = null,
//        message: String? = null,
//        uiException: UiException
//    ) {
//        logger.logException(tag ?: TAG, message, uiException.cause)
//        _uiModelPublisher.onNext(State.Error(uiException))
//    }
//
//    protected fun showLoading() {
//        _uiModelPublisher.onNext(State.Loading())
//    }
//
//    /**
//     * Call this method if you want to reactively observe the [UI_MODEL] changes
//     * from the [ViewModel] point of view or if you want to retrieve the last updated [UI_MODEL].
//     *
//     * Fragments should only rely on [LiveData] by the help of [uiModelLiveData].
//     */
//    protected val uiModelObservable: Observable<UI_MODEL> =
//        _uiModelPublisher.toObservable()
//            .filterInstance<State.Success<UI_MODEL>>()
//            .map(State.Success<UI_MODEL>::data)
//
//    @CallSuper
//    override fun onCleared() {
//        disposables.dispose()
//    }
//
//    protected fun Disposable.autoDispose() {
//        disposables.add(this)
//    }
//
//    /**
//     * Create a [LiveData] directly from one of our [UI_MODEL] values.
//     * This [LiveData] will immediately changes and notify any observers
//     * when the mapped Value of our [UI_MODEL] is updated.
//     *
//     * This only works if the [UI_MODEL] is [State.Success].
//     *
//     * @return a [LiveData] which will changes each time the value is updated
//     * in our [UI_MODEL].
//     */
//    fun <Value> map(mapper: (UI_MODEL) -> Value): LiveData<Value> {
//
//        return MediatorLiveData<Value>().apply {
//
//            // Add the original source to our freshly created LiveData.
//            // The callback is called each time the original source changes.
//            this.addSource(uiModelLiveData) { uiModelState ->
//
//                val uiModel = (uiModelState as? State.Success)?.data
//                val newValue: Value? = if (uiModel != null) mapper(uiModel) else null
//
//                newValue?.let(::postValue)
//            }
//        }.distinctUntilChanged()
//    }
//
//    companion object {
//        private val TAG = BaseViewModel::class.java.simpleName
//    }
}
