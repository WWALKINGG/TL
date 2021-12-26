package top.notbe.tele.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)

val T_ChatBubble_Status = TextStyle(
    fontSize = 13.sp,
    color = C_ChatBubble_Status
)

val T_ChatBubble_Text = TextStyle(
    fontSize = 16.sp,
    color = C_ChatBubble_Text
)

val T_ChatBubble_DropMenu_Text = TextStyle(
    fontSize = 14.sp
)