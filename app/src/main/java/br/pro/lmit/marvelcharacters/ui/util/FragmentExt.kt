package br.pro.lmit.marvelcharacters.ui.util

import androidx.fragment.app.Fragment
import br.pro.lmit.marvelcharacters.ViewModelFactory
import br.pro.lmit.marvelcharacters.data.DefaultCharacterRepository

fun Fragment.getViewModelFactory(): ViewModelFactory {

    return ViewModelFactory(DefaultCharacterRepository())
}