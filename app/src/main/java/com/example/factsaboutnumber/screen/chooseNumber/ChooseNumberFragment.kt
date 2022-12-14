package com.example.factsaboutnumber.screen.chooseNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.factsaboutnumber.App
import com.example.factsaboutnumber.R
import com.example.factsaboutnumber.adapter.NumberAdapter
import com.example.factsaboutnumber.adapter.NumberListener
import com.example.factsaboutnumber.databinding.FragmentChooseNumberBinding
import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.screen.infoNumber.InfoNumberFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseNumberFragment : Fragment() {

    private var _binding: FragmentChooseNumberBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG = ChooseNumberFragment::class.simpleName
        fun newInstance() = ChooseNumberFragment()
    }

    private lateinit var viewModel: ChooseNumberViewModel

    @Inject
    lateinit var factory: ChooseNumberViewModelFactory

    private lateinit var adapter: NumberAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NumberAdapter(
            object : NumberListener {
                override fun onClick(number: Int) {
                    navigateToNumberDetails(number)
                }
            }
        )
        with(binding.recyclerView) {
            adapter = this@ChooseNumberFragment.adapter
        }

        binding.btnFact.setOnClickListener {
            try {
                val number = Integer.parseInt(binding.edNumber.text.toString())
                viewModel.search(number)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error parse", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRandom.setOnClickListener {
            viewModel.search()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effects.onEach(::handleUiEvent).launchIn(this)
                viewModel.viewState.onEach(::handleUiState).launchIn(this)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, factory)[ChooseNumberViewModel::class.java]
    }

    private fun navigateToNumberDetails(number: Int) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container,
                InfoNumberFragment.newInstance(number),
                InfoNumberFragment.TAG
            )
            .commit()
    }
    private fun handleUiEvent(effect: Effect) {
        when (effect) {
            is Effect.NotFound -> {
                Toast.makeText(requireContext(), "Info not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun handleUiState(list: List<NumberInfo>) {
        adapter.submitList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
