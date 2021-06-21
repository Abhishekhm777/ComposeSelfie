package com.example.selfiecompose

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SelfieViewModel(
    private val  photoUriManager : PhotoUriManager
) : ViewModel() {

     var uri: Uri? = null
    var isPhotoSaved = MutableLiveData<Boolean>()

    private val _uiState = MutableLiveData<PhotoDataClass>()
    val uiState: LiveData<PhotoDataClass> = _uiState


    fun getUriToSaveImage(): Uri? {
        uri = photoUriManager.buildNewUri()
        return uri
    }

    fun onImageSaved(){
        _uiState.value = PhotoDataClass(uri)
    }

}

class SurveyViewModelFactory(
    private val photoUriManager: PhotoUriManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelfieViewModel::class.java)) {
            return SelfieViewModel( photoUriManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
