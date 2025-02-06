package com.example.personalprojects.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.personalprojects.models.MovieDetailsModel
import com.example.personalprojects.ui.common.TopBar
import com.example.personalprojects.viewModel.MovieViewModel

@Composable
fun MovieDetailScreen(id: Int) {
    val movieViewModel = viewModel<MovieViewModel>()
  //  movieViewModel.movieId = id
    movieViewModel.getMovieDetailsById(id)
    val state = movieViewModel.state

    val details = state.detailsData

    Scaffold (
        modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopBar()
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.TopCenter
            ) {
                BackGroundPoster(details = details)
                Column(
                    Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 60.dp)
                        .align(Alignment.BottomCenter),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = details.title,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 26.sp,
                        color = Color.White,
                        lineHeight = 40.sp,
                        textAlign = TextAlign.Center
                    )
                    Rating(details = details, modifier = Modifier)
                    ContentTextContainer(icon = Icons.Filled.Info, title = "Summary:", bodyText = details.plot)
                    ContentTextContainer(icon = Icons.Filled.Person, title = "Cast:", bodyText = details.actors)
                    ImageRowList(details = details)
                }
            }
        }
        )
}

@Composable
fun ImageRowList(details: MovieDetailsModel) {
    if (details.images.isNotEmpty()) {
        LazyRow {
            items(details.images.size) {
                AsyncImage(
                    model = details.images[it], contentDescription = "",
                    Modifier
                        .padding(6.dp)
                        .height(70.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun ContentTextContainer(icon: ImageVector, title: String, bodyText: String) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "Person",
            tint = Color.White
        )
        Text(
            text = title,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = bodyText, color = Color.White)
}

@Composable
fun Rating(details: MovieDetailsModel, modifier: Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(imageVector = Icons.Filled.Star, contentDescription = "", tint = Color.White)
        Text(
            text = details.rated,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
        Text(
            text = details.runtime,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "", tint = Color.White)
        Text(
            text = details.released,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
    }
}

@Composable
fun BackGroundPoster(details: MovieDetailsModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        AsyncImage(
            model = details.poster, contentDescription = details.title,
            modifier = Modifier
                .matchParentSize()
                .alpha(0.8f)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.DarkGray
                        )
                    ),
                )
        )
    }

}