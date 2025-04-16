import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_exemplars",  // Изменено имя таблицы
    foreignKeys = [
        ForeignKey(
            entity = ScheduledWorkoutEntity::class,
            parentColumns = ["id"],
            childColumns = ["scheduledWorkoutId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("scheduledWorkoutId")]
)
data class WorkoutExemplar(  // Изменено имя класса
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val scheduledWorkoutId: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
