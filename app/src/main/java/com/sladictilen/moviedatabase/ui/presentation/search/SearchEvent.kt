package com.sladictilen.moviedatabase.ui.presentation.search

sealed class SearchEvent {
    data class OnSearchValueChange(val searchText: String) : SearchEvent()
    object OnClearSearchClick : SearchEvent()
    data class OnSearchedItemClick(val id: Int) : SearchEvent()
}
