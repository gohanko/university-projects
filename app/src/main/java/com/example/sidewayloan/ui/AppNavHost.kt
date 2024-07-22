import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.ui.NavigationItem
import com.example.sidewayloan.ui.composables.CalculatorTopAppBar
import com.example.sidewayloan.ui.screens.History

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.History.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.History.route) {
            History(navController)
        }
        composable(NavigationItem.Settings.route) {
            Text(text="aaaaa")
        }

        composable("calculator") {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    CalculatorTopAppBar { navController.popBackStack() }
                }
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {

                }
            }
        }

    }
}