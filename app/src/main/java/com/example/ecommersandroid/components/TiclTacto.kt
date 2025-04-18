package com.example.ecommersandroid.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TickTack(modifier: Modifier = Modifier) {

    var slected by remember { mutableStateOf(mutableMapOf<Int, String>(
        1 to "", 2 to "", 3 to "", 4 to "", 5 to "", 6 to "", 7 to "", 8 to "", 9 to ""
    )) }
    var turn by remember { mutableStateOf("X") }
    var win by remember { mutableStateOf(false) }

    fun checkWin() {
        // othe way not this way is better
        val winningCombinations = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(3, 6, 9),
            listOf(1, 5, 9),
            listOf(3, 5, 7)
        )


        for (combination in winningCombinations) {
            val (a, b, c) = combination
            if (slected[a] == slected[b] && slected[b] == slected[c] && slected[a] != "") {
                win = true
                break
            }
        }
    }

    Column {
        Card (
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )

        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    if(win) "$turn Win" else "Turn $turn",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(slected.entries.chunked(3), key = { it }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    it.forEach { item ->
                        key(item) {
                            Card (
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(10.dp)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.inverseOnSurface
                                    )
                                    .clickable {
                                        if (!win) {
                                            if (item.value.isEmpty()) {
                                                slected = slected.toMutableMap().apply {
                                                    this[item.key] = turn
                                                }
                                                checkWin()
                                                if(!win)
                                                turn = if (turn == "X") "O" else "X"
                                            }
                                        } else {

                                        }

                                    }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        item.value,
                                        textAlign = TextAlign.Center,
                                        fontSize = 30.sp
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
        Card (
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                ).clickable {
                    slected = mutableMapOf(
                        1 to "", 2 to "", 3 to "", 4 to "", 5 to "", 6 to "", 7 to "", 8 to "", 9 to ""
                    )
                    turn = "X"
                    win = false
                }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Reset",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            }
        }

    }

}


