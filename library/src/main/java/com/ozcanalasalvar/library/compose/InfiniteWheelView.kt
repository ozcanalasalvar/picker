package com.ozcanalasalvar.library.compose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun InfiniteWheelView(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(256.dp, 200.dp),
    startIndex: Int = 3,
    itemCount: Int = 28,//TODO will be array of element,
    rowOffsetCount: Int = 3,
) {

    val lazyListState = rememberLazyListState(startIndex + (itemCount * 1000))
    val isScrollInProgress = lazyListState.isScrollInProgress
    val spannedIndex = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

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
//             contentPadding = PaddingValues(vertical = size.height / 3)
        ) {


            items(Int.MAX_VALUE) {

                val rotateDegree = calculateItemRotation(spannedIndex.value + rowOffsetCount, it)
                Box(
                    modifier = Modifier
                        .height(size.height / rowCount)
                        .width(size.width)
                        .graphicsLayer {
                            this.rotationX = rotateDegree
                        }
                        .alpha(calculateItemAlpha(spannedIndex.value + rowOffsetCount, it)),
                    contentAlignment = Alignment.Center,
                ) {

                    Text(
                        text = if ((it % itemCount + 1) <10) " ${(it % itemCount + 1)}" else (it % itemCount + 1).toString(),
//                        fontSize = (18 - (spannedIndex.value + rowOffsetCount - it).absoluteValue).sp,
                    )
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
        if (currentItem != null && currentItem.offset <= -height.value * 3 / 10) {
            index++
        }
    }
    Log.d("spanned", "$index")
    return index
}

@Composable
private fun calculateItemRotation(spannedIndex: Int, index: Int): Float {

    /*  val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }.value
      val firstVisibleItem = remember { derivedStateOf { listState.firstVisibleItemIndex } }.value

      val spannedIndex =
          layoutInfo.visibleItemsInfo.firstOrNull()?.index?:0 + 2
     // Log.d("spanned","$spannedIndex")
     // Log.d("spanned","${(spannedIndex - index )}")*/

//    val rotationX = -(20f + ((spannedIndex - index ) * 30f))
    val rotationX = -15f * (spannedIndex - index)
    val rotationXY = (spannedIndex - index)
    return rotationX
}

@Composable
private fun calculateItemAlpha(spannedIndex: Int, index: Int): Float {
    /* val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }.value
     val firstVisibleItem = remember { derivedStateOf { listState.firstVisibleItemIndex } }.value
     val spannedIndex =
         layoutInfo.visibleItemsInfo.firstOrNull()?.index?:0 + 2*/


    val rotationX = 1f - (0.3f * (spannedIndex - index).absoluteValue)
    return rotationX.absoluteValue
}