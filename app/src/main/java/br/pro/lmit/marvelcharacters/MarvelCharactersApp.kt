package br.pro.lmit.marvelcharacters

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class MarvelCharactersApp : Application() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }

}