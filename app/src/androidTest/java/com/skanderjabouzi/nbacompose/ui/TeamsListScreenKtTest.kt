package com.skanderjabouzi.nbacompose.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.skanderjabouzi.nbacompose.R
import com.skanderjabouzi.nbacompose.components.TeamsListHeader
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamsListScreenKtTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun TestTopBarMenu() {
        rule.setContent {
            TeamsListHeader()
        }
        val winsText = rule.activity.getString(R.string.wins)
        val lossesTest = rule.activity.getString(R.string.losses)

        rule.onNodeWithText(winsText).assertExists()
        rule.onNodeWithText(lossesTest).assertExists()
    }

//    @Test
//    fun TestTeamsList() {
//        rule.setContent {
//            TeamList(
//                contentPadding = innerPadding,
//                teamsList = teamsUiState,
//                onItemClicked = onItemClicked,
//            )
//
//        }
//    }
}
