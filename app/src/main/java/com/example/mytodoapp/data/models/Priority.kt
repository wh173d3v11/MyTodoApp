package com.example.mytodoapp.data.models

import androidx.compose.ui.graphics.Color
import com.example.mytodoapp.ui.theme.HighPriorityColor
import com.example.mytodoapp.ui.theme.LowPriorityColor
import com.example.mytodoapp.ui.theme.MediumPriorityColor
import com.example.mytodoapp.ui.theme.NoPriorityColor


enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NoPriorityColor)
}