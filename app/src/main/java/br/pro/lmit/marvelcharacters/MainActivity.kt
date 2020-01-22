package br.pro.lmit.marvelcharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import br.pro.lmit.marvelcharacters.ui.characterlist.CharacterListFragment

class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

}
