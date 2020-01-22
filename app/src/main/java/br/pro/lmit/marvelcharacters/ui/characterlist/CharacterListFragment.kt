package br.pro.lmit.marvelcharacters.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.pro.lmit.marvelcharacters.R
import br.pro.lmit.marvelcharacters.databinding.CharacterListFragmentBinding
import br.pro.lmit.marvelcharacters.ui.util.getViewModelFactory
import br.pro.lmit.marvelcharacters.util.EventObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.character_list_fragment.*

class CharacterListFragment : Fragment() {

    private val viewModel by viewModels<CharacterListViewModel> { getViewModelFactory() }

    private lateinit var binding: CharacterListFragmentBinding

    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
            }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setupAdapter()
        setupEvents()
    }

    private fun setupAdapter() {
        val viewModel = binding.viewmodel
        if (viewModel != null) {
            adapter = CharacterAdapter(viewModel)
            binding.recycler.adapter = adapter
        }
    }

    private fun setupEvents() {
        viewModel.viewDetailsEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                R.id.action_characterListFragment_to_characterDetailsFragment,
                bundleOf("id" to it)
            )
        })
        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            val text = if (it.isEmpty()) getString(R.string.default_error) else it
            Snackbar.make(main, text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    viewModel.load()
                }.show()
        })
    }

}
