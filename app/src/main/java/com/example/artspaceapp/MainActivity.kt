package com.example.artspaceapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp() // Call the composable function to show UI
            }
        }
    }
}

// Composable function untuk konten utama
@Composable
fun ArtSpaceApp() {
    // List art untuk gambar
    val artList = listOf(
        ArtPiece(R.drawable.art1, "Mona Lisa", "Leonardo da Vinci, 1503"),
        ArtPiece(R.drawable.art2, "The Starry Night", "Vincent van Gogh, 1889"),
        ArtPiece(R.drawable.art3, "The Scream", "Edvard Munch, 1893")
    )

    // untuk mentracking index seni saat ini
    var currentArtIndex by remember { mutableStateOf(0) }

    // Tata letak untuk menampilkan karya seni dan kontrol
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Menampilkan gambar seni terkini
        Image(
            painter = painterResource(artList[currentArtIndex].imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kartu untuk menampilkan judul dan informasi artis
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = artList[currentArtIndex].title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 24.sp
                )
                Text(
                    text = artList[currentArtIndex].artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk bernavigasi di antara karya seni
        Row {
            Button(
                onClick = { if (currentArtIndex > 0) currentArtIndex-- }, // Show previous art
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Previous")
            }

            Button(
                onClick = { if (currentArtIndex < artList.size - 1) currentArtIndex++ } // Show next art
            ) {
                Text("Next")
            }
        }
    }
}

// Kelas data untuk menyimpan informasi tentang suatu karya seni
data class ArtPiece(val imageRes: Int, val title: String, val artist: String)

//Fungsi preview untuk melihat desain di Android Studio
@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
