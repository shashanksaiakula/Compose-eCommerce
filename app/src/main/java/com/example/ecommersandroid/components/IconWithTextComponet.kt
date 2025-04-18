package com.example.ecommersandroid.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommersandroid.R

@Composable
fun IconWithTextComponet(modifier: Modifier = Modifier,
                         iamge : Int,
                         text : String,
                         onClick : ()-> Unit) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = colorResource(R.color.buttom_backGround),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(10.dp)
                .clickable (
                    indication = rememberRipple(color = Color.White),
                    interactionSource = remember { MutableInteractionSource() }
                )
                {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(iamge),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp,0.dp)
                    .size(25.dp)
            )
            Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("")
        }
        Spacer(modifier = Modifier.height(10.dp))
}