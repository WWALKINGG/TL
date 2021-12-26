package top.notbe.tele.components

import android.app.Activity
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.FilePresent
import androidx.compose.material.icons.outlined.KeyboardVoice
import androidx.compose.material.icons.outlined.OutlinedFlag
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


import top.notbe.tele.ui.theme.PlaceHolder


@Composable
fun ChatToolBar() {
    var value by remember {
        mutableStateOf("")
    }
    var inputStatus by remember {
        mutableStateOf(InputStatus.Text)
    }
    val context = LocalContext.current as Activity
    context.getSystemService(Context.INPUT_METHOD_SERVICE)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val (p1, p2, p3) = createRefs()
        Row(modifier = Modifier.constrainAs(p1) {
            bottom.linkTo(parent.bottom)
        }) {
            MyIconButton(
                onClick = {
                    inputStatus =
                        if (inputStatus == InputStatus.Emoji) InputStatus.Text else InputStatus.Emoji
                }
            ) {
                Icon(imageVector = Icons.Outlined.EmojiEmotions, contentDescription = null)
            }
        }
        val interactionSource = MutableInteractionSource()
        val isFocus = interactionSource.collectIsFocusedAsState().value
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            cursorBrush = SolidColor(Color.Black),
            interactionSource = interactionSource,
            modifier = Modifier
                .zIndex(0.1f)
                .constrainAs(p2) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    start.linkTo(p1.end, 0.dp)
                    end.linkTo(p3.start, 0.dp)
                    width = Dimension.fillToConstraints
                }
                .padding(vertical = 12.dp),
            decorationBox = @Composable {
                if (value.isEmpty()) {
                    Text(text = "This is Placeholder", color = PlaceHolder)
                }
                if (value.isNotEmpty() || isFocus) it()
            },
            textStyle = TextStyle(
                fontSize = 18.sp
            )
        )
        Row(
            modifier = Modifier
                .animateContentSize()
                .constrainAs(p3) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(horizontal = 10.dp)

        ) {
            if (value.isEmpty()) {
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.OutlinedFlag, contentDescription = null)
                }
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.FilePresent, contentDescription = null)
                }
                MyIconButton(onClick = {
                    inputStatus =
                        if (inputStatus == InputStatus.Voice) InputStatus.Text else InputStatus.Voice
                }) {
                    Icon(imageVector = Icons.Outlined.KeyboardVoice, contentDescription = null)
                }
            } else {
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Send, contentDescription = null)
                }
            }

        }


    }
    // emoji
    Box(
        modifier = Modifier.animateContentSize()
    ) {
        when (inputStatus) {
            top.notbe.tele.components.InputStatus.Emoji -> EmojiInput()
            InputStatus.Voice -> VoiceInput()
            else -> {}
        }
    }
}

enum class InputStatus {
    Text,
    Emoji,
    Voice,
    Video
}

@Composable
fun EmojiInput() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Text(text = "Emoji")
    }
}

@Composable
fun VideoInput() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Text(text = "VideoInput")
    }
}

@Composable
fun VoiceInput() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Text(text = "VoiceInput")
    }
}