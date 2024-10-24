package id.dafa.grid1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.dafa.grid1.affirmation.Affirmation
import id.dafa.grid1.affirmation.Datasource
import id.dafa.grid1.ui.theme.Grid1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Menetapkan tema untuk aplikasi dan menampilkan layar utama
            Grid1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil composable utama yang menampilkan daftar afirmasi
                    AffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    // Memanggil AffirmationList dengan data yang diambil dari Datasource
    AffirmationList(
        affirmationList = Datasource().loadAffirmations(),
    )
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    // Menampilkan daftar afirmasi dalam bentuk LazyColumn (komponen list yang bisa di-scroll)
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            // Menampilkan setiap afirmasi dalam bentuk kartu
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    // Membuat kartu untuk setiap item afirmasi
    Card(modifier = modifier) {
        Column {
            // Menampilkan gambar afirmasi
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop // Skala gambar agar memenuhi ukuran yang ditetapkan
            )
            // Menampilkan teks afirmasi
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall // Menggunakan gaya teks default dari tema Material
            )
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    // Preview tampilan kartu afirmasi saat sedang mendesain aplikasi
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}
