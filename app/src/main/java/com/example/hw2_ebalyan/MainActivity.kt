package com.example.hw2_ebalyan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") {
                    WelcomeScreen(navController = navController)
                }
                composable("list") {
                    val cities = listOf(
                        CityInfo("New York", "New York City is the largest city in the United States, known for its iconic skyline, Central Park, and a vibrant arts scene. Explore the Big Apple and discover its diverse neighborhoods.", R.drawable.new_york),
                        CityInfo("Sydney", "Sydney, Australia's largest city, is famous for its stunning harbor, the Sydney Opera House, and beautiful beaches like Bondi Beach. Experience the beauty and culture of Sydney.", R.drawable.sydney),
                        CityInfo("Rio de Janeiro", "Rio de Janeiro, Brazil, is known for its lively Carnival, Copacabana Beach, and the Christ the Redeemer statue. Enjoy samba music and the breathtaking views of the city.", R.drawable.rio_de_janeiro),
                        CityInfo("Dubai", "Dubai is a global business hub and tourist destination, famous for its skyscrapers, luxury shopping, and man-made islands. Explore the futuristic city of Dubai.", R.drawable.dubai),
                        CityInfo("Cape Town", "Cape Town, South Africa, is a coastal city with stunning landscapes, including Table Mountain and the Cape of Good Hope. Discover the natural beauty of Cape Town.", R.drawable.cape_town),
                        CityInfo("Bali", "Bali, Indonesia, is a tropical paradise known for its beautiful beaches, rice terraces, and vibrant culture. Relax in Balinese resorts and explore its unique traditions.", R.drawable.bali)
                    )

                    CityListScreen(cities = cities, navController = navController)
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to City Explorer!",
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(72.dp))
            Button(
                onClick = {
                    navController.navigate("list")
                },
                modifier = Modifier
                    .height(56.dp)
            ) {
                Text(
                    text = "Explore Cities",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

data class CityInfo(val name: String, val description: String, val imageResId: Int)

@Composable
fun CityListScreen(cities: List<CityInfo>, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .height(40.dp)
        ) {
            Text(
                text = "Go Back",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }
        LazyColumn {
            items(cities) { city ->
                var isExpanded by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = city.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        if (isExpanded) {
                            Image(
                                painter = painterResource(id = city.imageResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )

                            Text(
                                text = city.description,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = "See Less",
                                color = Color.Blue,
                                modifier = Modifier.clickable {
                                    isExpanded = !isExpanded
                                }
                            )
                        } else {
                            Text(
                                text = "See More",
                                color = Color.Blue,
                                modifier = Modifier.clickable {
                                    isExpanded = !isExpanded
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
