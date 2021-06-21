package com.example.selfiecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.example.selfiecompose.ui.theme.SelfieComposeTheme

class MainActivity : ComponentActivity() {

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()){ photoSaved ->
        if (photoSaved) viewModel.onImageSaved()

    }
    private val viewModel: SelfieViewModel by viewModels {
        SurveyViewModelFactory(PhotoUriManager(this.applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfieComposeTheme {
                viewModel.uiState.observeAsState().value.let {
                        SelfieScreen(it?.uri) {  onClick() }
                }
            }
        }
    }

    private fun onClick(){
        takePicture.launch(viewModel.getUriToSaveImage())
    }
}

