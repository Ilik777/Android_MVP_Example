package com.example.mvp_example.mvp.presenter

private const val NO_TEXT = ""

class Presenter(
    private val repository: Repository
) : Contract.Presenter {

    private var view: Contract.View = EmptyView

    override fun onAttachView(view: Contract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = EmptyView
    }

    override fun onSaveButtonClicked(
        title: String,
        subtitle: String,
        date: String
    ) {
        val updatedAdvertData = AdvertData(
            title = title,
            subtitle = subtitle,
            dateString = "Создано $date"
        )

        repository.saveAdvert(updatedAdvertData)

        view.showMessage("Сохранено")
    }

    override fun onDeleteButtonClicked() {
        view.updateAdvertTitle(NO_TEXT)
        view.updateAdvertSubtitle(NO_TEXT)
        view.updateAdvertDateText(NO_TEXT)
        view.showNoAdvertPhoto()

        view.showMessage("Удалено")
    }

    override fun onLoadButtonClicked() {
        val advertData = repository.loadAdvert()

        view.updateAdvertTitle(advertData.title)
        view.updateAdvertSubtitle(advertData.subtitle)
        view.updateAdvertDateText(advertData.dateString)
        view.showAdvertPhoto()
        view.showMessage("Загружено")
    }

    override fun onNextButtonClicked() {
        repository.clearData()

        view.showMessage("Память очищена")
    }

    override fun onUnknownButtonClicked() {
        view.showError("Unknown View Clicked")
    }
}
