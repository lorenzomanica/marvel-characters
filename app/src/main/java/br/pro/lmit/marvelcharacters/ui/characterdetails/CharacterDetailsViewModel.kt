package br.pro.lmit.marvelcharacters.ui.characterdetails

import android.content.Context
import androidx.lifecycle.*
import br.pro.lmit.marvelcharacters.data.CharacterRepository
import br.pro.lmit.marvelcharacters.data.Result
import br.pro.lmit.marvelcharacters.data.entity.Character
import br.pro.lmit.marvelcharacters.util.Event
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorEvent = MutableLiveData<Event<String>>()
    val errorEvent: LiveData<Event<String>> = _errorEvent

    private val _openWebEvent = MutableLiveData<Event<String>>()
    val openWebEvent: LiveData<Event<String>> = _openWebEvent

    private val _openWikiEvent = MutableLiveData<Event<String?>>()
    val openWikiEvent: LiveData<Event<String?>> = _openWikiEvent


    fun load(id: Int) {

        _loading.value = true

        viewModelScope.launch {
            val result = repository.getCharacter(id)

            if (result is Result.Success) {
                result.data.data?.results?.let {
                    _character.postValue(it[0])
                }
            } else {
                val msg = (result as Result.Error).exception.message
                _errorEvent.postValue(Event(msg.orEmpty()))
            }

            _loading.value = false
        }
    }

    fun openWeb(c: Character) {
        val url = c.urls?.getOrNull(0)
        url?.let {
            _openWebEvent.value = Event(url.url!!)
        }
    }

    fun openWiki(c: Character) {
        val url = c.urls?.getOrNull(1)
        url?.let {
            _openWebEvent.value = Event(url.url!!)
        }
    }



}
