package com.example.tngorganizer.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tngorganizer.navigations.screens.exerciseScreen.ui.ExerciseScreen
import com.example.tngorganizer.navigations.screens.MainScreen
import com.example.tngorganizer.navigations.screens.ProgramsScreen
import com.example.tngorganizer.navigations.screens.workoutScreen.ui.WorkoutScreen
import com.example.tngorganizer.shared.lib.provides.LocalNavController

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
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

            composable(
                route = "program/{programId}/workouts",
                arguments = listOf(navArgument("programId") { type = NavType.LongType })
            ) {
                WorkoutScreen(
                    onMainClick = { navController.navigate("main") }
                )
            }

            composable(
                route = "program/{programId}/workouts/{workoutId}",
                arguments = listOf(navArgument("programId") { type = NavType.LongType },navArgument("workoutId") { type = NavType.LongType })
            ) {
                ExerciseScreen(
                    onMainClick = { navController.navigate("main") }
                )
            }
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
