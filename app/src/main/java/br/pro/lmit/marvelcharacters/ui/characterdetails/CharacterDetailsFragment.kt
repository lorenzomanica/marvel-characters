package br.pro.lmit.marvelcharacters.ui.characterdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.pro.lmit.marvelcharacters.R
import br.pro.lmit.marvelcharacters.databinding.CharacterDetailsFragmentBinding
import br.pro.lmit.marvelcharacters.ui.util.getViewModelFactory
import br.pro.lmit.marvelcharacters.util.EventObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.character_list_fragment.*

class CharacterDetailsFragment : Fragment() {

    private val viewModel by viewModels<CharacterDetailsViewModel> { getViewModelFactory() }

    private lateinit var binding: CharacterDetailsFragmentBinding

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailsFragmentBinding.inflate(inflater, container, false)
            .apply {
                this.viewmodel = viewModel
            }

        viewModel.load(args.id)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setupEvents()
    }

    private fun setupEvents() {
        viewModel.openWebEvent.observe(viewLifecycleOwner, EventObserver { d ->
            openLink(d)
        })

        viewModel.openWikiEvent.observe(viewLifecycleOwner, EventObserver {d ->
            d?.let { openLink(it) }
        })
        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            val text = if (it.isEmpty()) getString(R.string.default_error) else it
            Snackbar.make(main, text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    viewModel.load(args.id)
                }.show()
        })
    }

    private fun openLink(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
    }

}
