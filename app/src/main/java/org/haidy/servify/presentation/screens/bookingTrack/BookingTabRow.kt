package org.haidy.servify.presentation.screens.bookingTrack

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookingTabRow(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Theme.colors.background,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = Theme.colors.accent100
            )
        }
    ) {
        Tabs.values().forEachIndexed { index, tab ->
            val isSelected = pagerState.currentPage == index
            val textColor =
                if (isSelected) Theme.colors.accent100 else Theme.colors.accent200.copy(alpha = 0.7f)
            val title = tab.getTitle(Resources.languageCode.value)
            Tab(
                text = { Text(title, color = textColor) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = Theme.colors.accent100,
                unselectedContentColor = Theme.colors.accent300
            )
        }
    }
}