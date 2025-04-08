package com.example.a10minuteworkout.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a10minuteworkout.R
import com.example.a10minuteworkout.utils.GradientBackground

data class ExerciseDetail(
    val name: String,
    val imageResId: Int,
    val description: String,
    val category: String
)

@Composable
fun ExerciseDetailScreen(navController: NavController, exerciseName: String) {
    val exerciseDetail = getExerciseDetail(exerciseName)

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = exerciseDetail.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(id = exerciseDetail.imageResId),
                        contentDescription = exerciseDetail.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "How to do it",
                            color = Color(0xFFFF9800),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = exerciseDetail.description,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

fun getExerciseDetail(exerciseName: String): ExerciseDetail {
    return when (exerciseName) {
        "Jumping Jacks" -> ExerciseDetail(
            name = "Jumping Jacks",
            imageResId = R.drawable.jumping_jacks,
            category = "Cardio",
            description = "1. Start standing with feet together and arms at your sides\n" +
                    "2. Jump and simultaneously spread your feet wider than hip-width apart while raising your arms above your head\n" +
                    "3. Quickly jump again and return to the starting position\n" +
                    "4. Repeat at a quick, steady pace\n\n" +
                    "Key points:\n" +
                    "• Keep a steady rhythm\n" +
                    "• Land softly on your feet\n" +
                    "• Keep your core engaged"
        )

        "High Knees" -> ExerciseDetail(
            name = "High Knees",
            imageResId = R.drawable.high_knees,
            category = "Cardio",
            description = "1. Stand with feet hip-width apart\n" +
                    "2. Run in place, lifting your knees as high as possible towards your chest\n" +
                    "3. Pump your arms while running\n" +
                    "4. Keep alternating legs at a quick pace\n\n" +
                    "Key points:\n" +
                    "• Maintain good posture\n" +
                    "• Engage your core\n" +
                    "• Land on the balls of your feet"
        )

        "Mountain Climbers" -> ExerciseDetail(
            name = "Mountain Climbers",
            imageResId = R.drawable.mountain_climbers,
            category = "Cardio",
            description = "1. Start in a plank position with arms straight\n" +
                    "2. Drive one knee toward your chest\n" +
                    "3. Quickly switch legs, alternating in a running motion\n" +
                    "4. Keep your hips low and parallel to the ground\n\n" +
                    "Key points:\n" +
                    "• Keep your core tight\n" +
                    "• Maintain a straight back\n" +
                    "• Move at a controlled but quick pace"
        )

        "Burpees" -> ExerciseDetail(
            name = "Burpees",
            imageResId = R.drawable.burpees,
            category = "Cardio",
            description = "1. Start standing, then drop into a squat position with hands on the ground\n" +
                    "2. Kick your feet back into a plank position\n" +
                    "3. Immediately return your feet to the squat position\n" +
                    "4. Jump up from squatting position\n\n" +
                    "Key points:\n" +
                    "• Keep movements fluid\n" +
                    "• Land softly\n" +
                    "• Maintain proper form throughout"
        )

        "Jump Rope" -> ExerciseDetail(
            name = "Jump Rope",
            imageResId = R.drawable.jump_rope,
            category = "Cardio",
            description = "1. Hold the ends of the rope with hands at hip height\n" +
                    "2. Rotate wrists to swing the rope\n" +
                    "3. Jump slightly off the ground as the rope passes under\n" +
                    "4. Land softly and immediately prepare for next jump\n\n" +
                    "Key points:\n" +
                    "• Stay on balls of feet\n" +
                    "• Keep jumps small and controlled\n" +
                    "• Maintain a steady rhythm"
        )

        "Running in Place" -> ExerciseDetail(
            name = "Running in Place",
            imageResId = R.drawable.running_in_place,
            category = "Cardio",
            description = "1. Stand with feet hip-width apart\n" +
                    "2. Begin running in place, lifting feet at least 3-4 inches off the ground\n" +
                    "3. Pump arms naturally as if running\n" +
                    "4. Maintain a quick, steady pace\n\n" +
                    "Key points:\n" +
                    "• Stay light on your feet\n" +
                    "• Keep upper body relaxed\n" +
                    "• Breathe steadily"
        )

        "Jump Squats" -> ExerciseDetail(
            name = "Jump Squats",
            imageResId = R.drawable.jump_squats,
            category = "Cardio",
            description = "1. Stand with feet shoulder-width apart\n" +
                    "2. Lower into a squat position\n" +
                    "3. Explosively jump straight up\n" +
                    "4. Land softly and immediately lower into the next squat\n\n" +
                    "Key points:\n" +
                    "• Keep chest up\n" +
                    "• Land softly with bent knees\n" +
                    "• Drive through heels on jump"
        )

        "Butt Kicks" -> ExerciseDetail(
            name = "Butt Kicks",
            imageResId = R.drawable.butt_kicks,
            category = "Cardio",
            description = "1. Stand with feet hip-width apart\n" +
                    "2. Run in place, kicking heels toward buttocks\n" +
                    "3. Keep alternating legs at a quick pace\n" +
                    "4. Pump arms naturally\n\n" +
                    "Key points:\n" +
                    "• Stay on balls of feet\n" +
                    "• Keep upper body straight\n" +
                    "• Maintain quick pace"
        )

        "Star Jumps" -> ExerciseDetail(
            name = "Star Jumps",
            imageResId = R.drawable.star_jumps,
            category = "Cardio",
            description = "1. Start standing with feet together and arms at sides\n" +
                    "2. Jump up, spreading legs and raising arms to form a star shape\n" +
                    "3. Jump back to starting position\n" +
                    "4. Repeat in a continuous motion\n\n" +
                    "Key points:\n" +
                    "• Jump explosively\n" +
                    "• Land softly\n" +
                    "• Keep movements controlled"
        )

        "Speed Skaters" -> ExerciseDetail(
            name = "Speed Skaters",
            imageResId = R.drawable.speed_skaters,
            category = "Cardio",
            description = "1. Start standing with feet together\n" +
                    "2. Jump laterally to the right, landing on right foot\n" +
                    "3. Swing left leg behind right leg and left arm across body\n" +
                    "4. Alternate sides in a skating motion\n\n" +
                    "Key points:\n" +
                    "• Keep low center of gravity\n" +
                    "• Land softly\n" +
                    "• Maintain momentum"
        )

        "Crunches" -> ExerciseDetail(
            name = "Crunches",
            imageResId = R.drawable.crunches,
            category = "Core",
            description = "1. Lie on back with knees bent and feet flat\n" +
                    "2. Place hands behind head, elbows pointed outward\n" +
                    "3. Lift shoulder blades off ground using core muscles\n" +
                    "4. Lower back down with control\n\n" +
                    "Key points:\n" +
                    "• Don't pull on head/neck\n" +
                    "• Keep lower back pressed to ground\n" +
                    "• Focus on using core muscles"
        )

        "Plank" -> ExerciseDetail(
            name = "Plank",
            imageResId = R.drawable.plank,
            category = "Core",
            description = "1. Start in forearm position with elbows under shoulders\n" +
                    "2. Extend legs behind you, resting on toes\n" +
                    "3. Form straight line from head to heels\n" +
                    "4. Hold position while breathing steadily\n\n" +
                    "Key points:\n" +
                    "• Keep hips level\n" +
                    "• Engage core muscles\n" +
                    "• Don't let back sag"
        )

        "Russian Twists" -> ExerciseDetail(
            name = "Russian Twists",
            imageResId = R.drawable.russian_twists,
            category = "Core",
            description = "1. Sit with knees bent and feet off ground\n" +
                    "2. Lean back slightly, keeping back straight\n" +
                    "3. Clasp hands together in front of chest\n" +
                    "4. Rotate torso side to side\n\n" +
                    "Key points:\n" +
                    "• Keep chest up\n" +
                    "• Control the movement\n" +
                    "• Maintain balanced position"
        )

        "Leg Raises" -> ExerciseDetail(
            name = "Leg Raises",
            imageResId = R.drawable.leg_raises,
            category = "Core",
            description = "1. Lie on back with legs straight and together\n" +
                    "2. Keep arms at sides, palms down\n" +
                    "3. Lift legs to 90 degrees while keeping them straight\n" +
                    "4. Lower legs with control\n\n" +
                    "Key points:\n" +
                    "• Keep lower back pressed to ground\n" +
                    "• Don't swing legs\n" +
                    "• Maintain control throughout"
        )

        "Bicycle Crunches" -> ExerciseDetail(
            name = "Bicycle Crunches",
            imageResId = R.drawable.bicycle_crunches,
            category = "Core",
            description = "1. Lie on back, hands behind head\n" +
                    "2. Lift shoulders off ground and raise legs\n" +
                    "3. Bring right elbow to left knee while extending right leg\n" +
                    "4. Alternate sides in continuous motion\n\n" +
                    "Key points:\n" +
                    "• Keep lower back pressed to ground\n" +
                    "• Move with control\n" +
                    "• Maintain steady breathing"
        )

        "V-Sits" -> ExerciseDetail(
            name = "V-Sits",
            imageResId = R.drawable.v_sits,
            category = "Core",
            description = "1. Sit with legs extended and together\n" +
                    "2. Lean back slightly, keeping back straight\n" +
                    "3. Lift legs off ground to form V shape\n" +
                    "4. Hold position while maintaining balance\n\n" +
                    "Key points:\n" +
                    "• Keep chest up\n" +
                    "• Engage core throughout\n" +
                    "• Control the position"
        )

        "Side Planks" -> ExerciseDetail(
            name = "Side Planks",
            imageResId = R.drawable.side_planks,
            category = "Core",
            description = "1. Lie on side with forearm flat on ground\n" +
                    "2. Stack feet or stagger them\n" +
                    "3. Lift hips to create straight line\n" +
                    "4. Hold position and repeat on other side\n\n" +
                    "Key points:\n" +
                    "• Keep body straight\n" +
                    "• Don't let hips sag\n" +
                    "• Maintain steady breathing"
        )

        "Flutter Kicks" -> ExerciseDetail(
            name = "Flutter Kicks",
            imageResId = R.drawable.flutter_kicks,
            category = "Core",
            description = "1. Lie on back with legs extended\n" +
                    "2. Place hands under buttocks for support\n" +
                    "3. Lift legs slightly off ground\n" +
                    "4. Alternate legs up and down in small motions\n\n" +
                    "Key points:\n" +
                    "• Keep lower back pressed to ground\n" +
                    "• Make movements small and controlled\n" +
                    "• Keep legs straight"
        )

        "Push-Ups" -> ExerciseDetail(
            name = "Push-Ups",
            imageResId = R.drawable.push_ups,
            category = "Intense",
            description = "1. Start in plank position, hands shoulder-width apart\n" +
                    "2. Lower body until chest nearly touches ground\n" +
                    "3. Push back up to starting position\n" +
                    "4. Keep body straight throughout movement\n\n" +
                    "Key points:\n" +
                    "• Keep core tight\n" +
                    "• Don't let hips sag\n" +
                    "• Breathe steadily"
        )

        "Diamond Push-Ups" -> ExerciseDetail(
            name = "Diamond Push-Ups",
            imageResId = R.drawable.diamond_push_ups,
            category = "Intense",
            description = "1. Start in push-up position\n" +
                    "2. Form diamond shape with hands under chest\n" +
                    "3. Lower chest to hands\n" +
                    "4. Push back up to starting position\n\n" +
                    "Key points:\n" +
                    "• Keep elbows close to body\n" +
                    "• Maintain straight back\n" +
                    "• Control the movement"
        )

        "Jump Lunges" -> ExerciseDetail(
            name = "Jump Lunges",
            imageResId = R.drawable.jump_lunges,
            category = "Intense",
            description = "1. Start in lunge position\n" +
                    "2. Jump explosively straight up\n" +
                    "3. Switch legs in mid-air\n" +
                    "4. Land softly in lunge position with opposite leg forward\n\n" +
                    "Key points:\n" +
                    "• Keep chest up\n" +
                    "• Land softly\n" +
                    "• Maintain balance"
        )

        "Plank to Downward Dog" -> ExerciseDetail(
            name = "Plank to Downward Dog",
            imageResId = R.drawable.plank_to_downward_dog,
            category = "Core",
            description = "1. Start in plank position\n" +
                    "2. Push hips up and back\n" +
                    "3. Form inverted V-shape with body\n" +
                    "4. Return to plank position\n\n" +
                    "Key points:\n" +
                    "• Keep arms and legs straight\n" +
                    "• Move with control\n" +
                    "• Maintain steady breathing"
        )

        "Plank Shoulder Taps" -> ExerciseDetail(
            name = "Plank Shoulder Taps",
            imageResId = R.drawable.plank_shoulder_taps,
            category = "Intense",
            description = "1. Start in high plank position\n" +
                    "2. Tap left shoulder with right hand\n" +
                    "3. Return hand to ground\n" +
                    "4. Alternate sides\n\n" +
                    "Key points:\n" +
                    "• Keep hips stable\n" +
                    "• Don't rock side to side\n" +
                    "• Maintain core engagement"
        )

        else -> ExerciseDetail(
            name = "Exercise Not Found",
            imageResId = R.drawable.app_logo,
            category = "Unknown",
            description = "Exercise Not Found")
    }
}