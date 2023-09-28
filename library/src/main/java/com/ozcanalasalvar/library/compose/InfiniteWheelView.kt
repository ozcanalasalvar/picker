package com.ozcanalasalvar.library.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@Composable
fun InfiniteWheelView(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(256.dp, 200.dp),
    selection: Int = 3,
    itemCount: Int = 28,
    rowOffset: Int = 4,
    isEndless: Boolean = true,
    onFocusItem: (Int) -> Unit,
    content: @Composable LazyItemScope.(index: Int) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    val haptic = LocalHapticFeedback.current

    val count = if (isEndless) itemCount else itemCount + 2 * rowOffset
    val rowOffsetCount = maxOf(1, minOf(rowOffset, 4))
    val rowCount = ((rowOffsetCount * 2) + 1)
    val startIndex=
       if (isEndless) selection + (itemCount * 1000) - rowOffset else selection

    val lazyListState = rememberLazyListState(startIndex)
    val isScrollInProgress = lazyListState.isScrollInProgress
    val focusedIndex = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex + rowOffsetCount }
    }

    LaunchedEffect(key1 = itemCount) {
        coroutineScope.launch {
            lazyListState.scrollToItem(startIndex)
        }
    }

    LaunchedEffect(key1 = isScrollInProgress) {
        if (!isScrollInProgress) {
            calculateIndexToFocus(lazyListState, size.height).let {
                val indexToFocus = if (isEndless) {
                    (it + rowOffsetCount) % itemCount
                } else {
                    ((it + rowOffsetCount) % count) - rowOffset
                }

                onFocusItem(indexToFocus)
                if (lazyListState.firstVisibleItemScrollOffset != 0) {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(it, 0)
                    }
                }
            }
        }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }.collect {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }


    Box(
        modifier = modifier
            .height(size.height)
            .fillMaxWidth(),
    ) {

        LazyColumn(
            modifier = Modifier
                .height(size.height)
                .fillMaxWidth(),
            state = lazyListState,
        ) {


            items(if (isEndless) Int.MAX_VALUE else count) {
                val rotateDegree = calculateIndexRotation(focusedIndex.value, it)
                Box(
                    modifier = Modifier
                        .height(size.height / rowCount)
                        .fillMaxWidth()
                        .graphicsLayer {
                            this.rotationX = rotateDegree
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    if (isEndless) {
                        content(it % itemCount)
                    } else if (it >= rowOffsetCount && it < itemCount + rowOffsetCount) {
                        content((it - rowOffsetCount) % itemCount)
                    }
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


private fun calculateIndexToFocus(listState: LazyListState, height: Dp): Int {
    val currentItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()
    var index = currentItem?.index ?: 0

    if (currentItem?.offset != 0) {
        if (currentItem != null && currentItem.offset <= -height.value * 3 / 10) {
            index++
        }
    }
    return index
}

@Composable
private fun calculateIndexRotation(focusedIndex: Int, index: Int): Float {
    return -15f * (focusedIndex - index)
}