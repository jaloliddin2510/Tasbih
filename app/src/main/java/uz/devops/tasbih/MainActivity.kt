package uz.devops.tasbih

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import uz.devops.tasbih.ui.theme.TasbihTheme
import uz.devops.tasbih.ui.theme.Ui

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var vibrator: Vibrator
    private val songList = listOf(
        R.raw.beep,
        R.raw.chime
    )
    private var songPermission = true

    private fun playFromList(context: Context, index: Int) {
        if (index in songList.indices) {
            playSound(context, songList[index])
        }
    }

    private fun playSound(context: Context, index: Int) {
        val mediaPlayer = MediaPlayer.create(context, index)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("TasbihCount", Context.MODE_PRIVATE)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        setContent {
            TasbihTheme {
                val savedCounter = remember { sharedPreferences.getInt("counter", 0) }
                val counter = remember { mutableIntStateOf(savedCounter) }
                val soundState = remember { mutableStateOf(songPermission) }
                var effect:Long
                Ui().RosaryUi(
                    count = counter.intValue,
                    CountPlus = {
                        counter.intValue++
                        saveCounter(counter.intValue)
                        if (counter.intValue % 33 == 0 && soundState.value) {
                            if (counter.intValue / 2 == 0) {
                                playFromList(this, 0)
                            } else {
                                playFromList(this, 1)
                            }
                            effect=300
                        }else{
                            effect=5
                        }
                        vibrate(effect)
                    },
                    CountNull = {
                        counter.intValue = 0
                        saveCounter(counter.intValue)
                    },
                    Sound = {
                        songPermission = !songPermission
                        soundState.value = songPermission
                    },
                    songPermission = soundState.value
                )
            }
        }
    }

    private fun saveCounter(counterValue: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("counter", counterValue)
        editor.apply()
    }

    private fun vibrate(effect:Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val vibratorEffect =
                VibrationEffect.createOneShot(effect, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibratorEffect)
        } else {
            vibrator.vibrate(effect)
        }
    }

}
