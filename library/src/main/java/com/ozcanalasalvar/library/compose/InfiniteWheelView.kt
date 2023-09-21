package com.ozcanalasalvar.library.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun InfiniteWheelView(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(256.dp, 200.dp),
    startIndex: Int = 3,
    itemCount: Int = 28,//TODO will be array of element,
    rowOffset: Int = 4,
    content: @Composable LazyItemScope.(index: Int) -> Unit,
    onFocusItem: (Int) -> Unit,
) {

    val lazyListState = rememberLazyListState(startIndex + (itemCount * 1000))
    val isScrollInProgress = lazyListState.isScrollInProgress
    val rowOffsetCount = maxOf(1, minOf(rowOffset, 4))
    val rowCount = ((rowOffsetCount * 2) + 1)

    val spannedIndex =
        remember { derivedStateOf { lazyListState.firstVisibleItemIndex + rowOffsetCount } }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = isScrollInProgress) {
        if (!isScrollInProgress) {
            calculateSnappedItemIndex(lazyListState, size.height).let {
                val focusedIndex = (it + rowOffsetCount) % itemCount
                onFocusItem(focusedIndex)
                if (lazyListState.firstVisibleItemScrollOffset != 0) {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(it, 0)
                    }
                }
            }
        }
    }


    Box(
        modifier = modifier
            .height(size.height)
            .width(size.width),
    ) {

        LazyColumn(
            modifier = Modifier
                .height(size.height)
                .width(size.width),
            state = lazyListState,
            // contentPadding = PaddingValues(vertical = size.height / rowCount*rowOffsetCount)
        ) {


            items(Int.MAX_VALUE) {

                val rotateDegree = calculateItemRotation(spannedIndex.value, it)
                Box(
                    modifier = Modifier
                        .height(size.height / rowCount)
                        .width(size.width)
                        .graphicsLayer {
                            this.rotationX = rotateDegree
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    content(it % itemCount)
                }

            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xE6FFFFFF),
                            Color(0xE6FFFFFF),
                            Color(0xB3FFFFFF),
                            Color(0xB3FFFFFF),
                            Color(0x80FFFFFF),
                            Color(0x80FFFFFF),
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color(0x80FFFFFF),
                            Color(0x80FFFFFF),
                            Color(0xB3FFFFFF),
                            Color(0xB3FFFFFF),
                            Color(0xE6FFFFFF),
                            Color(0xE6FFFFFF),
                        )
                    )
                ),
        ) {}

    }


}


private fun calculateSnappedItemIndex(listState: LazyListState, height: Dp): Int {
    val currentItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()
    var index = currentItem?.index ?: 0

    if (currentItem?.offset != 0) {
        if (currentItem != null && currentItem.offset <= -height.value * 3 / 10) {
            index++
        }
    }
    Log.d("spanned", "$index")
    return index
}

@Composable
private fun calculateItemRotation(spannedIndex: Int, index: Int): Float {
    return -15f * (spannedIndex - index)
}