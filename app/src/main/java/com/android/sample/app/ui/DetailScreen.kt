package com.android.sample.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.sample.app.R
import com.android.sample.app.ui.common.DetailDescriptionText
import com.android.sample.app.ui.common.DetailTitleText
import com.android.sample.app.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.label_detail))
                },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                }
            )
        },
        content = {
            Content(viewModel = viewModel) { movieDetail ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    DetailTitleText(resourceId = R.string.label_title)
                    Spacer(Modifier.height(4.dp))
                    DetailDescriptionText(text = movieDetail.title)
                    Spacer(Modifier.height(8.dp))
                    DetailTitleText(resourceId = R.string.label_description)
                    Spacer(Modifier.height(4.dp))
                    DetailDescriptionText(text = movieDetail.subTitle)
                }
            }
        })
}