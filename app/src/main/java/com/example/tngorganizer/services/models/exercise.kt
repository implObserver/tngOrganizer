import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutEntity::class,
            parentColumns = ["id"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExercisesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val workoutId: Int // внешняя связь с тренировкой
)