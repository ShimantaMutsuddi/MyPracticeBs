package com.mutsuddi_s.mypracticebs.ui.features.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.data.repository.CharacterRepository
import com.mutsuddi_s.mypracticebs.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    /*private val _id = MutableLiveData<Int>()


        init{
            viewModelScope.launch(Dispatchers.IO) {
               // repository.getCharacter(_id)
                _id.switchMap { id ->
                    repository.getCharacter(id)
                }
            }

        }*/


    val character: LiveData<Resource<Character>>
        get() = repository.character



    fun start(id: Int) {
        //_id.value = id
        viewModelScope.launch(Dispatchers.IO) {
            // repository.getCharacter(_id)
                repository.getCharacter(id)
            }
        }


}