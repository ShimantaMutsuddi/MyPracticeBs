package com.mutsuddi_s.mypracticebs.ui.features.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.databinding.FragmentCharacterDetailBinding
import com.mutsuddi_s.mypracticebs.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.id?.let { viewModel.start(it) }
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                   // binding.characterCl.visibility = View.VISIBLE
                }

               is  Resource.Error ->
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_SHORT).show()

               is  Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    //binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(character: Character) {
        binding.name.text = character.name
        binding.species.text = character.species
        binding.status.text = character.status
        binding.gender.text = character.gender
       // binding.origin.text = character.
       // binding.gender.text = character.gender
        Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.image)
    }




}