package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.pub.cs.systems.eim.practicaltest01var05.ui.theme.PracticalTest01Var05Theme

class MainActivity : ComponentActivity() {

    private val pressesKey = "presses"
    private var presses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        presses = sharedPreferences.getInt(pressesKey, 0)

        Toast.makeText(this, "Presses: $presses", Toast.LENGTH_LONG).show()

        setContentView(R.layout.activity_practical_test01_var05_main)
        val transferButton: Button = findViewById(R.id.transfer_button)
        val topLeftButton: Button = findViewById(R.id.top_left_button)
        val topRightButton: Button = findViewById(R.id.top_right_button)
        val centerButton: Button = findViewById(R.id.center_button)
        val bottomLeftButton: Button = findViewById(R.id.bottom_left_button)
        val bottomRightButton: Button = findViewById(R.id.bottom_right_button)
        val displayText: TextView = findViewById(R.id.display_text)
        displayText.text = ""
        val buttons = listOf(topLeftButton, topRightButton, centerButton, bottomLeftButton, bottomRightButton)
        buttons.forEach { button ->
            button.setOnClickListener {
                presses += 1
                sharedPreferences.edit().putInt(pressesKey, presses).apply()
                if (displayText.text.isNotEmpty()) {
                    displayText.text = "${displayText.text}, ${button.text}"
                } else {
                    displayText.text = button.text
                }
            }
        }
        transferButton.setOnClickListener {
            val text = "${topLeftButton.text}, ${topRightButton.text}, ${centerButton.text}, ${bottomLeftButton.text}, ${bottomRightButton.text}"
            displayText.text = text
        }
    }
}


