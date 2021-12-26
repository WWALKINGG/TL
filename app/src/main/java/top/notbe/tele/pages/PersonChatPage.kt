package top.notbe.tele.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import top.notbe.tele.R
import top.notbe.tele.components.*
import top.notbe.tele.ui.theme.PlaceHolder
import java.util.*


var pageMode by mutableStateOf(PageMode.None)
val recordSelector = RecordSelector()
val topBarHeight = 45.dp

@ExperimentalAnimationGraphicsApi
@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalFoundationApi
@Composable
fun PersonChatPage() {
    pageMode = if(recordSelector.data.size > 0) PageMode.ChatBubbleLongClicked else PageMode.None
    Scaffold(
        topBar = {
            when (pageMode) {
                PageMode.None -> NoneTopBar()
                PageMode.ChatBubbleLongClicked -> LongClickTopBar()
                else -> {}
            }

        },
        bottomBar = {
            //ChatToolBar()
            Column {
                ChatToolBar()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(painterResource(id = R.drawable.bg),
                contentDescription =null ,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(-1f),
                contentScale = ContentScale.Crop,
                alpha = 1f
            )
            LazyColumn {
                repeat(1) {
                    item {
                        var data = SimpleRecord("t")
                        var clicked by remember {
                            mutableStateOf(false)
                        }
                        println(recordSelector.data)
                        ChatBubble(
                            message = "lalalala",
                            date = Date(),
                            status = ChatBubbleStatus.Sending,
                            type = ChatBubbleType.Other,
                            clicked = clicked,
                            longClicked = recordSelector.data.contains(data),
                            onDismissRequest = { clicked = false },
                            onClick = {
                                if(pageMode == PageMode.ChatBubbleLongClicked) {
                                    if (recordSelector.data.contains(data)) {
                                        recordSelector.data.remove(data)
                                    } else {
                                        recordSelector.data.add(data)
                                    }
                                }else{clicked = !clicked}

                            },
                            onLongClick = {
                                if (recordSelector.data.contains(data)) {
                                    recordSelector.data.remove(data)
                                } else {
                                    recordSelector.data.add(data)
                                }
                            }
                        ) {
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.Replay,
                                "Reply"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.CopyAll,
                                "CopyAll"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.Forward,
                                "Forward"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.Delete,
                                "Delete"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.RunCircle,
                                "WithDraw"
                            ) {}
                        }
                    }

                    item {
                        var data = SimpleRecord("s")
                        var dropdownMenuVisible by remember {
                            mutableStateOf(false)
                        }
                        var clicked by remember {
                            mutableStateOf(false)
                        }
                        ChatBubble(
                            message = "lalalalalalalalalalala",
                            date = Date(),
                            status = ChatBubbleStatus.Sending,
                            clicked = dropdownMenuVisible,
                            onDismissRequest = { dropdownMenuVisible = false },
                            longClicked = recordSelector.data.contains(data),
                            onLongClick = {
                                if (recordSelector.data.contains(data)) {
                                    recordSelector.data.remove(data)
                                } else {
                                    recordSelector.data.add(data)
                                }
                            },
                            onClick= {
                                if(pageMode == PageMode.ChatBubbleLongClicked) {
                                    if (recordSelector.data.contains(data)) {
                                        recordSelector.data.remove(data)
                                    } else {
                                        recordSelector.data.add(data)
                                    }
                                }else{clicked = !clicked}
                            },
                            type = ChatBubbleType.Me
                        ) {
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.Replay,
                                "Reply"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.CopyAll,
                                "CopyAll"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.Forward,
                                "Forward"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.Delete,
                                "Delete"
                            ) {}
                            top.notbe.tele.components.DropdownMenuItem(
                                Icons.Default.RunCircle,
                                "WithDraw"
                            ) {}
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationGraphicsApi
@Composable
private fun NoneTopBar() {
    TopAppBar(
        modifier = Modifier
            .height(topBarHeight)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
                Text(text = "WWALKINGG")
            }
            Box {
                var s by remember {
                    mutableStateOf(false)
                }
                IconButton(onClick = { s = !s }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
                DropdownMenu(
                    expanded = s,
                    onDismissRequest = { s = false },
                    modifier = Modifier.fillMaxWidth(.6f),
                    offset = DpOffset(0.dp, (-20).dp)
                ) {
                    Text(
                        text = "Tool",
                        modifier = Modifier.padding(start = 20.dp),
                        color = PlaceHolder
                    )
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Search, contentDescription = null)
                            Text(text = "Search", fontSize = 16.sp)
                        }
                    }
                    Text(
                        text = "Privacy",
                        modifier = Modifier.padding(start = 20.dp),
                        color = PlaceHolder
                    )
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                            Text(text = "Delete Chat Record", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun LongClickTopBar(
) {
    TopAppBar(
        modifier = Modifier
            .height(topBarHeight)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { recordSelector.data.clear() }) {
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                }
                Text(text = recordSelector.data.size.toString())
            }
            Row(
                modifier = Modifier.fillMaxHeight()
            ){
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null)
                }
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Forward, contentDescription = null)
                }
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Archive, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(10.dp))
                MyIconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}

enum class PageMode {
    ChatBubbleLongClicked,
    ChatBubbleClicked,
    None
}

class RecordSelector {
    var count = 0
    val data = mutableStateListOf<SimpleRecord>()

    fun add(simpleRecord: SimpleRecord) {
        count++
        data.add(simpleRecord)
    }

    fun clear() {
        count = 0
        data.clear()
    }

}

data class SimpleRecord(
    val text: String,
)