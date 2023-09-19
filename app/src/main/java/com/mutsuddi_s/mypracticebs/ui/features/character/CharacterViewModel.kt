package com.mutsuddi_s.mypracticebs.ui.features.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.data.repository.CharacterRepository
import com.mutsuddi_s.mypracticebs.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository): ViewModel(){
   /* val characters: LiveData<Resource<CharacterResponse>>
        get() = repository.getCharacters()*/

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCharacter()
        }

    }  /* val characters: LiveData<Resource<CharacterResponse>> = viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters()

    }*/

    val characters: LiveData<Resource<List<Character>>>
        get()=repository.characters
}