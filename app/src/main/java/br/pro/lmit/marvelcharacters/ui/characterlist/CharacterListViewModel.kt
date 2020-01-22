package br.pro.lmit.marvelcharacters.ui.characterlist

import androidx.lifecycle.*
import br.pro.lmit.marvelcharacters.data.entity.Character
import br.pro.lmit.marvelcharacters.data.CharacterRepository
import br.pro.lmit.marvelcharacters.data.Result
import br.pro.lmit.marvelcharacters.util.Event
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Character>>().apply { value = emptyList() }
    val items: LiveData<List<Character>> = _items

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _viewDetailsEvent = MutableLiveData<Event<Int>>()
    val viewDetailsEvent: LiveData<Event<Int>> = _viewDetailsEvent

    private val _errorEvent = MutableLiveData<Event<String>>()
    val errorEvent: LiveData<Event<String>> = _errorEvent


    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }


    init {
        load()
    }

    fun load() {

        _loading.value = true

        viewModelScope.launch {
            val result = repository.getCharacters()

            if (result is Result.Success) {
                result.data.data?.results?.let {
                    _items.postValue(it)
                }
            } else {
                val msg = (result as Result.Error).exception.message
                _errorEvent.postValue(Event(msg.orEmpty()))
            }
            _loading.value = false
        }

    }

    fun viewDetails(id: Int) {
        _viewDetailsEvent.value = Event(id)
    }


}
