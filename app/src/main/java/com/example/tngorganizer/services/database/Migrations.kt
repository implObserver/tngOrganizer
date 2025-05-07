package com.example.tngorganizer.services.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Удаление всех данных из таблиц
        database.execSQL("DELETE FROM workouts")
        database.execSQL("DELETE FROM workout_groups")
        database.execSQL("DELETE FROM programs")

        // Пересоздание таблиц, если это необходимо
        // Можно также использовать CREATE TABLE IF NOT EXISTS для создания новых таблиц,
        // если их нет в базе данных
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `workouts` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                `name` TEXT NOT NULL, 
                `programId` INTEGER NOT NULL, 
                `groupId` INTEGER, 
                `createdAt` INTEGER NOT NULL, 
                `updatedAt` INTEGER NOT NULL,
                FOREIGN KEY(`programId`) REFERENCES `programs`(`id`) ON DELETE CASCADE,
                FOREIGN KEY(`groupId`) REFERENCES `workout_groups`(`id`) ON DELETE SET NULL
            )
        """)

        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `workout_groups` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `programId` INTEGER NOT NULL,
                `createdAt` INTEGER NOT NULL,
                `updatedAt` INTEGER NOT NULL,
                FOREIGN KEY(`programId`) REFERENCES `programs`(`id`) ON DELETE CASCADE
            )
        """)

        // Другие таблицы можно пересоздать таким же образом (например, программы).
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `programs` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `createdAt` INTEGER NOT NULL,
                `updatedAt` INTEGER NOT NULL
            )
        """)
    }
}
