package com.samir.jetpackcomposemvvmclean.presentation.ui.features.cats

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityOptionsCompat
import com.samir.jetpackcomposemvvmclean.presentation.ui.components.EmptyView
import com.samir.jetpackcomposemvvmclean.presentation.ui.features.catDetails.CatFullImageActivity
import com.samir.jetpackcomposemvvmclean.presentation.ui.features.cats.view.CatsScreen
import com.samir.jetpackcomposemvvmclean.presentation.ui.features.cats.viewmodel.CatsViewModel
import com.samir.jetpackcomposemvvmclean.presentation.ui.theme.JetpackComposeMVVMCleanTheme
import com.samir.jetpackcomposemvvmclean.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsActivity : ComponentActivity() {
    private val viewModel: CatsViewModel by viewModels()
    private val myActivityResultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.getFavCatsData()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeMVVMCleanTheme {
                CatsScreenPresentation()
            }
        }
    }

    @Composable
    fun CatsScreenPresentation() {
        CatsScreen(
            state = viewModel.state.collectAsState().value,
            onNavigationRequested = { itemUrl, imageId ->
                myActivityResultContract.launch(
                    Intent(
                        this@CatsActivity,
                        CatFullImageActivity::class.java
                    ).apply {
                        putExtra(
                            Constants.URL,
                            itemUrl
                        )
                        putExtra(Constants.IMAGE_ID, imageId)
                    }, ActivityOptionsCompat.makeSceneTransitionAnimation(this)
                )

            },
            onRefreshCall = {
                viewModel.getCatsData()

            })
    }
}