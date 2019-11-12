package luyao.wanandroid.compose.ui.home

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.lifecycle.LiveData
import androidx.ui.core.Text
import androidx.ui.layout.FlexColumn
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.TopAppBar
import luyao.wanandroid.compose.R
import luyao.wanandroid.compose.ui.VectorImageButton

/**
 * Created by luyao
 * on 2019/11/12 11:02
 */

private enum class HomeSections(val title: String) {
    Main("首页"),
    Square("广场"),
    Lasted("最新项目"),
    System("体系"),
    Navigation("导航")
}

@Composable
fun HomeScreen(articleUiModel: ArticleViewModel.ArticleUiModel, openDrawer: () -> Unit,onTabSelected:(Int) -> Unit) {
    var section by +state { HomeSections.Main }
    val sectionTitles = HomeSections.values().map { it.title }

    FlexColumn {
        inflexible {
            TopAppBar(title = { Text("玩安卓") },
                navigationIcon = {
                    VectorImageButton(id = R.drawable.ic_android, onClick = { openDrawer() })
                })
        }
        inflexible {
            TabRow(items = sectionTitles, selectedIndex = section.ordinal) { index, text ->
                Tab(text = text, selected = section.ordinal == index) {
                    onTabSelected(index)
                    section = HomeSections.values()[index]
                }
            }
        }
        expanded(1f) {
            when (section) {
                HomeSections.Main -> MainTab(articleUiModel)
                HomeSections.Square -> MainTab(articleUiModel)
                HomeSections.Lasted -> MainTab(articleUiModel)
                HomeSections.System -> MainTab(articleUiModel)
                HomeSections.Navigation -> MainTab(articleUiModel)
            }
        }
    }
}