package com.example.factsaboutnumber.screen.infoNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.factsaboutnumber.App
import com.example.factsaboutnumber.databinding.FragmentInfoNumberBinding
import com.example.factsaboutnumber.db.entities.NumberInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoNumberFragment : Fragment() {

    private var _binding: FragmentInfoNumberBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: InfoNumberViewModel

    @Inject
    lateinit var factory: InfoNumberViewModelFactory

    private var number: Int = 0

    companion object {
        val TAG = InfoNumberFragment::class.simpleName
        private const val NUMBER_PREF = "number"
        fun newInstance(number: Int): InfoNumberFragment {
            val fragment = InfoNumberFragment()
            fragment.arguments = bundleOf(Pair(NUMBER_PREF, number))
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, factory)[InfoNumberViewModel::class.java]
        number = arguments?.getInt(NUMBER_PREF) ?: 0
        viewModel.getNumberDetails(number)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoNumberBinding.inflate(inflater, container, false)
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effects.onEach(::handleUiEvent).launchIn(this)
                viewModel.viewState.onEach(::handleUiState).launchIn(this)
            }
        }
        binding.btnBack.setOnClickListener {
            popBack()
        }
    }

    private fun handleUiEvent(effect: Effect) {
        when (effect) {
            is Effect.CantFoundNumberInfo -> Toast.makeText(requireActivity(), "Error: $effect.message", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleUiState(numberInfo: NumberInfo?) {
        binding.text.text = numberInfo?.text
    }

    private fun popBack(){
        parentFragmentManager.popBackStack()
    }
}