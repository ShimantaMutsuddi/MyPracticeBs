package com.mutsuddi_s.mypracticebs.ui.features.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.databinding.FragmentCharacterBinding
import com.mutsuddi_s.mypracticebs.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(), CharacterAdapter.OnItemClickListener{

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterViewModel by viewModels()
    private  val TAG = "CharacterFragment"

    private lateinit var adapter: CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()


    }

    private fun setupObservers() {

        viewModel.characters.observe(viewLifecycleOwner){ response ->
            when(response)
            {

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (!response.data.isNullOrEmpty())
                    {
                       // Toast.makeText(requireContext(), response.data, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "setupObservers: ${response.data }")

                        adapter.submitList(response.data)
                    }



                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), response.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                   // Snackbar.make(requireContext(), response.errorMessage.toString(), Snackbar.LENGTH_SHORT).show();

                }


                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

            }

        }
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id: Int) {
        val action=CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(id)
        Navigation.findNavController(binding.root).navigate(action)
    }


}