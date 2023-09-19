package com.ozcanalasalvar.library.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun WheelView(
    modifier: Modifier = Modifier, size: DpSize = DpSize(256.dp, 256.dp), startIndex: Int = 5
) {

    val lazyListState = rememberLazyListState(startIndex)
    val isScrollInProgress = lazyListState.isScrollInProgress

    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(key1 = isScrollInProgress) {
        Log.d("offset", "enter")
        coroutineScope.launch {
            if (!isScrollInProgress) {
                /* var index = lazyListState.firstVisibleItemIndex
                 val offset = lazyListState.firstVisibleItemScrollOffset*/
                val visibleItemsInfo = lazyListState.layoutInfo.visibleItemsInfo
                /* Log.d("offset", "$offset")
                 Log.d("offset", "index = $index")*/
                Log.d("offset", "visibleItemsInfo1 = ${visibleItemsInfo[0].offset}")
                Log.d("offset", "visibleItemsInfo2 = ${visibleItemsInfo[1].offset}")

                calculateSnappedItemIndex(lazyListState)?.let {
                    if (lazyListState.firstVisibleItemScrollOffset != 0)
                        lazyListState.animateScrollToItem(it, 0)


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
            contentPadding = PaddingValues(vertical = size.height / 3)
        ) {

            items(20) {

                Box(
                    modifier = Modifier
                        .height(size.height / 3)
                        .width(size.width),
                    contentAlignment = Alignment.Center
                ) {

                    //Text(text = (it % 28 + 1).toString())
                    Text(text = (it).toString())
                }

            }
        }

    }


}

fun LazyListState.animateScrollAndCentralizeItem(index: Int, scope: CoroutineScope) {
    val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    scope.launch {
        if (itemInfo != null) {
            val center = this@animateScrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            this@animateScrollAndCentralizeItem.animateScrollBy((childCenter - center).toFloat())
        } else {
            this@animateScrollAndCentralizeItem.animateScrollToItem(index)
        }
    }
}


private fun calculateSnappedItemIndex(listState: LazyListState): Int? {
    val currentItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()
    var index = currentItem?.index ?: 0

    if (currentItem?.offset != 0) {
        if (currentItem != null) {
            index++
        }
    }
    return index
}