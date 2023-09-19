package com.mutsuddi_s.mypracticebs.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.data.remote.CharacterService
import com.mutsuddi_s.mypracticebs.utils.NetworkChecker
import com.mutsuddi_s.mypracticebs.utils.Resource
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val characterService: CharacterService,
    private val networkChecker: NetworkChecker
) {

    private val TAG = "CharacterRepository"

    //to hold quoteList
    private val _characters = MutableLiveData<Resource<List<Character>>>()

    //accessable livedata
    val characters: LiveData<Resource<List<Character>>>
        get() = _characters

    //to hold quoteList
    private val _character = MutableLiveData<Resource<Character>>()

    //accessable livedata
    val character: LiveData<Resource<Character>>
        get() = _character

    suspend fun getAllCharacter() {
        _characters.postValue(Resource.Loading())
        var isNetwork: Boolean = networkChecker.isNetworkAvailable()
        if (isNetwork) {
            try {
                val result = characterService.getAllCharacters()
                Log.d(TAG, "getAllQuestions: ${result.body()}}")
                if (result?.body() != null) {

                    _characters.postValue(Resource.Success(result.body()!!.results))


                } else {
                    _characters.postValue(Resource.Error("Network Failure"))
                }

            } catch (e: Exception) {
                _characters.postValue(Resource.Error(e.message.toString()))
                /* when(e) {
                     is IOException -> _questions.postValue(Response.Error("Network Failure"))
                     else -> _questions.postValue(Response.Error("Conversion Error"))
                 }*/

            }
        } else {
            _characters.postValue(Resource.Error("No internet connection"))
        }

    }

    suspend fun getCharacter(id: Int) {
        _character.postValue(Resource.Loading())
        var isNetwork: Boolean = networkChecker.isNetworkAvailable()
        if (isNetwork) {
            try {
                val result = characterService.getCharacter(id!!)
                Log.d(TAG, "getCharacter : ${result.body()}}")
                if (result?.body() != null) {

                    _character.postValue(Resource.Success(result.body()))


                } else {
                    _character.postValue(Resource.Error("Network Failure"))
                }

            } catch (e: Exception) {
                _character.postValue(Resource.Error(e.message.toString()))
                /* when(e) {
                     is IOException -> _questions.postValue(Response.Error("Network Failure"))
                     else -> _questions.postValue(Response.Error("Conversion Error"))
                 }*/

            }
        } else {
            _character.postValue(Resource.Error("No internet connection"))
        }

    }


}


