package com.example.mvp_example.mvp.presenter

//Null pattern
object EmptyView : Contract.View {

    override fun showAdvertPhoto() = Unit

    override fun showNoAdvertPhoto() = Unit

    override fun updateAdvertTitle(title: String) = Unit

    override fun updateAdvertSubtitle(subtitle: String) = Unit

    override fun updateAdvertDateText(dateText: String) = Unit

    override fun showMessage(message: String) = Unit

    override fun showError(errorText: String) = Unit
}