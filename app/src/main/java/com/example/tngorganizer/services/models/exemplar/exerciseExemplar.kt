import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tngorganizer.services.models.etalon.ExerciseEntity

@Entity(
    tableName = "exercise_exemplars",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutExemplar::class,
            parentColumns = ["id"],
            childColumns = ["workoutInstanceId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("workoutInstanceId"), Index("templateId")]
)
data class ExerciseExemplar(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutInstanceId: Int,
    val templateId: Int?,
    val sets: Int,
    val reps: Int,
    val weight: Float,
    val comment: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
