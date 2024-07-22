import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.CalculatorActivity
import com.example.sidewayloan.ui.NavigationItem
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

        activity("calculator") {
            activityClass = CalculatorActivity::class
        }

    }
}