package br.pro.lmit.marvelcharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pro.lmit.marvelcharacters.data.CharacterRepository
import br.pro.lmit.marvelcharacters.ui.characterdetails.CharacterDetailsViewModel
import br.pro.lmit.marvelcharacters.ui.characterlist.CharacterListViewModel

class ViewModelFactory(
    private val repository: CharacterRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
            when {
                isAssignableFrom(CharacterListViewModel::class.java) ->
                    CharacterListViewModel(repository)
                isAssignableFrom(CharacterDetailsViewModel::class.java) ->
                    CharacterDetailsViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}