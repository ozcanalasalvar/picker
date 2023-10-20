package com.ozcanalasalvar.wheelview

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


@Composable
fun WheelView(
    modifier: Modifier = Modifier,
    itemSize: DpSize = DpSize(256.dp, 256.dp),
    selection: Int = 0,
    itemCount: Int,
    rowOffset: Int = 3,
    isEndless: Boolean = true,
    onFocusItem: (Int) -> Unit,
    userScrollEnabled: Boolean = true,
    selectorOption: SelectorOptions = SelectorOptions(),
    lazyWheelState: LazyListState? = null,
    content: @Composable LazyItemScope.(index: Int) -> Unit,
) {

    InfiniteWheelViewImpl(
        modifier = modifier,
        itemSize = itemSize,
        selection = selection,
        itemCount = itemCount,
        rowOffset = rowOffset,
        isEndless = isEndless,
        onFocusItem = onFocusItem,
        userScrollEnabled = userScrollEnabled,
        selectorOption = selectorOption,
        lazyWheelState = lazyWheelState,
    ) {
        content(it)
    }

}
