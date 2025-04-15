package com.example.tngorganizer.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tngorganizer.navigations.screens.MainScreen
import com.example.tngorganizer.navigations.screens.ProgramsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "main"  // Стартовая страница
    ) {
        // Главный экран
        composable("main") {
            MainScreen(
                onProgramsClick = { navController.navigate("programs") }
            )
        }

        // Список программ
        composable("programs") {
            ProgramsScreen(
                onMainClick = { navController.navigate("main") }
            )
        }
    }
}

/*
// Список программ
        composable("programs") {
            ProgramsScreen(
                onBackClick = { navController.popBackStack() },
                onWorkoutsClick = { programId ->
                    navController.navigate("workouts/$programId")
                }
            )
        }

        // Список тренировок программы
        composable(
            route = "workouts/{programId}",
            arguments = listOf(navArgument("programId") { type = NavType.IntType })
        ) { backStackEntry ->
            val programId = backStackEntry.arguments?.getInt("programId") ?: 0
            WorkoutsScreen(
                programId = programId,
                onBackClick = { navController.popBackStack() },
                onWorkoutClick = { workoutId ->
                    navController.navigate("workout/$workoutId")
                }
            )
        }

        // Детали тренировки
        composable(
            route = "workout/{workoutId}",
            arguments = listOf(navArgument("workoutId") { type = NavType.IntType })
        ) { backStackEntry ->
            val workoutId = backStackEntry.arguments?.getInt("workoutId") ?: 0
            WorkoutScreen(
                workoutId = workoutId,
                onBackClick = { navController.popBackStack() },
                onDayClick = { dayId ->
                    navController.navigate("day/$dayId")
                }
            )
        }

        composable("day") {
            DayScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
*/
