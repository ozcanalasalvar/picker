package com.ozcanalasalvar.library.compose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun InfiniteWheelView(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(200.dp, 200.dp),
    startIndex: Int = 3,
    itemCount: Int = 28,//TODO will be array of element,
    rowOffsetCount: Int = 2,
) {

    val lazyListState = rememberLazyListState(startIndex + (itemCount * 10))
    val isScrollInProgress = lazyListState.isScrollInProgress

    val rowCount = ((rowOffsetCount * 2) + 1)

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = isScrollInProgress) {
        coroutineScope.launch {
            if (!isScrollInProgress) {
                calculateSnappedItemIndex(lazyListState, size.height).let {
                    if (lazyListState.firstVisibleItemScrollOffset != 0) lazyListState.animateScrollToItem(
                        it, 0
                    )
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
            // contentPadding = PaddingValues(vertical = size.height / 3)
        ) {


            items(Int.MAX_VALUE) {

                val rotateDegree = calculateItemRotation(lazyListState, itemCount = itemCount, it)
                Box(
                    modifier = Modifier
                        .height(size.height / rowCount)
                        .width(size.width),
//                        .graphicsLayer {
//                            this.rotationX = calculateItemRotation(lazyListState, itemCount, it)
//                        },
                    contentAlignment = Alignment.Center,
                ) {

                    Text(text = (it % itemCount + 1).toString())
                    //Text(text = (it).toString())
                }

            }
        }

    }


}


private fun calculateSnappedItemIndex(listState: LazyListState, height: Dp): Int {
    val currentItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()
    var index = currentItem?.index ?: 0

    if (currentItem?.offset != 0) {
        if (currentItem != null && currentItem.offset <= -height.value * 4 / 10) {
            index++
        }
    }
    return index
}

private fun calculateItemRotation(listState: LazyListState, itemCount: Int, index: Int): Float {

    val firstVisibleItem = listState.firstVisibleItemIndex
    val spannedIndex =
        firstVisibleItem - 1 + (listState.layoutInfo.visibleItemsInfo.size / 2).toInt()
   // Log.d("spanned","$spannedIndex")
    Log.d("spanned","${(spannedIndex - index )}")

//    val rotationX = -(20f + ((spannedIndex - index ) * 30f))
    val rotationX = -25f * (spannedIndex - index)
    val rotationXY =  (spannedIndex - index)
    return rotationX
}

private fun calculateItemAlpha(listState: LazyListState, itemCount: Int, index: Int): Float {

    val firstVisibleItem = listState.firstVisibleItemIndex
    val spannedIndex =
        firstVisibleItem - 1 + (listState.layoutInfo.visibleItemsInfo.size / 2).toInt()

    Log.d("spanned","$spannedIndex")

    val rotationX = 1f - (0.3f * (spannedIndex - index).absoluteValue)
    return rotationX
}