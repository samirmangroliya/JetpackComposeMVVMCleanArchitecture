package com.samir.jetpackcomposemvvmclean.presentation.ui.features.cats.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.samir.jetpackcomposemvvmclean.R
import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.CatDataModel
import com.samir.jetpackcomposemvvmclean.presentation.ui.components.EmptyView
import com.samir.jetpackcomposemvvmclean.utils.TestTags
import com.samir.jetpackcomposemvvmclean.utils.TestTags.PROGRESS_BAR
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun CatsScreen(
    state: NetworkResult<List<CatDataModel>>,
    onNavigationRequested: (itemUrl: String, imageId: String) -> Unit,
    onRefreshCall: () -> Unit
) {

    when (state) {
        is NetworkResult.Loading -> {
            LoadingBar()
        }

        is NetworkResult.Success -> {
            showCats(state.data ?: emptyList(), onNavigationRequested)
        }

        is NetworkResult.Error -> {
            EmptyView(state.message)
        }
    }
}

@Composable
fun showCats(
    cats: List<CatDataModel>,
    onNavigationRequested: (String, String) -> Unit,
    onItemClicked: (url: String, imageId: String) -> Unit = { _: String, _: String -> }
) {
    Log.e("TEST", "${cats.size}")
    Log.e("TEST", "${cats.size}")
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        content = {
            this.items(cats) { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    border = BorderStroke(0.5.dp, Color.Gray),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                        .clickable {
                            onItemClicked(item.url, item.imageId)
                        }
                        .semantics { testTag = TestTags.CAT_ITEM_TAG }
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(top = 1.dp),
                    ) {
                        ItemThumbnail(thumbnailUrl = item.url)
                        if (!item.name.isNullOrBlank()) {
                            Column(
                                modifier = Modifier
                                    .background(colorResource(id = R.color.white))
                                    .fillMaxWidth()
                                    .align(Alignment.BottomStart),
                            ) {
                                Text(
                                    text = item.name,

                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    color = colorResource(id = R.color.purple_200),
                                )
                                item.origin?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(horizontal = 10.dp),
                                        color = colorResource(id = R.color.purple_200),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }, modifier = Modifier
            .semantics { testTag = TestTags.CATS_LIST_TAG }
            .fillMaxSize()
    )
}

@Composable
fun LoadingBar() {
    Box(
        modifier = Modifier
            .semantics { testTag = TestTags.LOADING_BAR_TAG }
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center

    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .testTag(PROGRESS_BAR)
                .size(60.dp),
            color = colorResource(id = R.color.purple_700),
            strokeWidth = 5.dp, // Width of the progress indicator's stroke
            trackColor =  colorResource(id = R.color.white),// Color of the track behind the progress indicator
            strokeCap = StrokeCap.Round
        )
    }
}

@Composable
fun ItemThumbnail(thumbnailUrl: String) {
    GlideImage(
        imageModel = { thumbnailUrl },
        modifier = Modifier
            .semantics { testTag = TestTags.LIST_IMG }
            .fillMaxWidth()
            .wrapContentHeight()
    )
}
