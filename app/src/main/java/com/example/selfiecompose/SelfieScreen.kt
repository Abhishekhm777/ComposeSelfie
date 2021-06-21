package com.example.selfiecompose

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun SelfieScreen( uri: Uri?, onClickCard: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        val resource = if (uri != null) {
            Icons.Filled.SwapHoriz
        } else {
            Icons.Filled.AddAPhoto
        }

        OutlinedButton(
            modifier = Modifier
                .padding(30.dp),
            onClick = { onClickCard() },
            contentPadding = PaddingValues(),

            ) {
            Column {
                //if  photo has not saved uri will be null and we have to display the thumbnail
                if(uri != null) {
                    Image(
                        painter = rememberCoilPainter(uri, fadeIn = true),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(96.dp)
                            .aspectRatio(4 / 3f)
                    )
                }
                else{
                    PhotoDefaultImage(
                        modifier = Modifier.padding(
                            horizontal = 86.dp,
                            vertical = 74.dp
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.BottomCenter)
                        .padding(vertical = 26.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = resource, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(
                            id = if (uri != null) {
                                R.string.retake_photo
                            } else {
                                R.string.add_photo
                            }
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoDefaultImage(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = MaterialTheme.colors.isLight
) {
    val assetId = if (lightTheme) {
        R.drawable.ic_selfie_light
    } else {
        R.drawable.ic_selfie_dark
    }
    Image(
        painter = painterResource(id = assetId),
        modifier = modifier,
        contentDescription = null
    )


}