package top.notbe.tele

import android.os.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import top.notbe.tele.pages.PersonChatPage
import top.notbe.tele.ui.theme.TeleTheme


class MainActivity : ComponentActivity() {
    @ExperimentalAnimationGraphicsApi
    @RequiresApi(Build.VERSION_CODES.S)
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeleTheme {
                // A surface container using the 'background' color from the theme
                PersonChatPage()
            }
        }
    }
}
