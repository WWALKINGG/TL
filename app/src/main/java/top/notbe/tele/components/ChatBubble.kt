package top.notbe.tele.components

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import top.notbe.tele.R
import top.notbe.tele.ui.theme.*
import java.util.*

@ExperimentalAnimationGraphicsApi
@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalFoundationApi
@Composable
fun ChatBubble(message:String,
               date:Date,
               status:ChatBubbleStatus,
               type:ChatBubbleType,
               clicked:Boolean,
               longClicked:Boolean = false,
               onDismissRequest:()->Unit,
               onClick: () -> Unit = {},
               onLongClick:()->Unit = {},
               clickMenuContent:@Composable ()->Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (longClicked) C_ChatBubble_LongClick_Background else Color.Unspecified)
            .padding(vertical = 10.dp, horizontal = 10.dp),
        contentAlignment = if(type == ChatBubbleType.Other) Alignment.TopStart else Alignment.BottomEnd
    ){

        Column(
            horizontalAlignment = if(type == ChatBubbleType.Other) Alignment.Start else Alignment.End,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(if (type == ChatBubbleType.Other) C_CharBubble_Other_Background else C_CharBubble_Me_Background)
                .combinedClickable(
                    onLongClick = {
                        val vibrator =
                            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        val vibration =
                            VibrationEffect.createOneShot(5, VibrationEffect.DEFAULT_AMPLITUDE)
                        vibrator.vibrate(vibration)
                        onLongClick()

                    },
                    onClick = onClick
                )
                .border(
                    3.dp,
                    if (clicked) C_ChatBubble_Border else Color.Unspecified,
                    RoundedCornerShape(4.dp)
                )
                .padding(10.dp, 6.dp)
        ) {
            ChatBubbleDropMenu(clicked, onDismissRequest = onDismissRequest){
                clickMenuContent()
            }
            Text(text = message, style = T_ChatBubble_Text)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "AM2:02", style = T_ChatBubble_Status)
                Icon(imageVector = Icons.Default.Done, contentDescription = null, tint = C_ChatBubble_Status, modifier = Modifier.size(16.dp))
            }
        }

    }
}

@ExperimentalAnimationGraphicsApi
@Composable
fun ChatBubbleDropMenu(
    expanded:Boolean, onDismissRequest : ()->Unit,content:@Composable ()->Unit
) {
    DropdownMenu(expanded =expanded , onDismissRequest =onDismissRequest,modifier = Modifier.fillMaxWidth(.4f),) {
        content()
    }
}

@Composable
fun DropdownMenuItem(imageVector:ImageVector, text:String, onClick:()->Unit){
    DropdownMenuItem(onClick = {onClick() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = imageVector, contentDescription = null)
            Text(text = text,style = T_ChatBubble_DropMenu_Text)
        }
    }
}
enum class ChatBubbleStatus{
    Sending,
    Sent,
    Read
}
enum class ChatBubbleType{
    Other,
    Me,
}