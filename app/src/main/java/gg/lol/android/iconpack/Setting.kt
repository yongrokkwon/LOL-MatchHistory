package gg.lol.android.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import gg.lol.android.IconPack

public val IconPack.Setting: ImageVector
    get() {
        if (_setting != null) {
            return _setting!!
        }
        _setting = Builder(
            name = "Setting",
            defaultWidth = 48.0.dp,
            defaultHeight = 48.0.dp,
            viewportWidth = 48.0f,
            viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(19.4f, 44.0f)
                lineToRelative(-1.0f, -6.3f)
                quadToRelative(-0.95f, -0.35f, -2.0f, -0.95f)
                reflectiveQuadToRelative(-1.85f, -1.25f)
                lineToRelative(-5.9f, 2.7f)
                lineTo(4.0f, 30.0f)
                lineToRelative(5.4f, -3.95f)
                quadToRelative(-0.1f, -0.45f, -0.125f, -1.025f)
                quadTo(9.25f, 24.45f, 9.25f, 24.0f)
                quadToRelative(0.0f, -0.45f, 0.025f, -1.025f)
                reflectiveQuadTo(9.4f, 21.95f)
                lineTo(4.0f, 18.0f)
                lineToRelative(4.65f, -8.2f)
                lineToRelative(5.9f, 2.7f)
                quadToRelative(0.8f, -0.65f, 1.85f, -1.25f)
                reflectiveQuadToRelative(2.0f, -0.9f)
                lineToRelative(1.0f, -6.35f)
                horizontalLineToRelative(9.2f)
                lineToRelative(1.0f, 6.3f)
                quadToRelative(0.95f, 0.35f, 2.025f, 0.925f)
                quadTo(32.7f, 11.8f, 33.45f, 12.5f)
                lineToRelative(5.9f, -2.7f)
                lineTo(44.0f, 18.0f)
                lineToRelative(-5.4f, 3.85f)
                quadToRelative(0.1f, 0.5f, 0.125f, 1.075f)
                quadToRelative(0.025f, 0.575f, 0.025f, 1.075f)
                reflectiveQuadToRelative(-0.025f, 1.05f)
                quadToRelative(-0.025f, 0.55f, -0.125f, 1.05f)
                lineTo(44.0f, 30.0f)
                lineToRelative(-4.65f, 8.2f)
                lineToRelative(-5.9f, -2.7f)
                quadToRelative(-0.8f, 0.65f, -1.825f, 1.275f)
                quadToRelative(-1.025f, 0.625f, -2.025f, 0.925f)
                lineToRelative(-1.0f, 6.3f)
                close()
                moveTo(24.0f, 30.5f)
                quadToRelative(2.7f, 0.0f, 4.6f, -1.9f)
                quadToRelative(1.9f, -1.9f, 1.9f, -4.6f)
                quadToRelative(0.0f, -2.7f, -1.9f, -4.6f)
                quadToRelative(-1.9f, -1.9f, -4.6f, -1.9f)
                quadToRelative(-2.7f, 0.0f, -4.6f, 1.9f)
                quadToRelative(-1.9f, 1.9f, -1.9f, 4.6f)
                quadToRelative(0.0f, 2.7f, 1.9f, 4.6f)
                quadToRelative(1.9f, 1.9f, 4.6f, 1.9f)
                close()
                moveTo(24.0f, 27.5f)
                quadToRelative(-1.45f, 0.0f, -2.475f, -1.025f)
                quadTo(20.5f, 25.45f, 20.5f, 24.0f)
                quadToRelative(0.0f, -1.45f, 1.025f, -2.475f)
                quadTo(22.55f, 20.5f, 24.0f, 20.5f)
                quadToRelative(1.45f, 0.0f, 2.475f, 1.025f)
                quadTo(27.5f, 22.55f, 27.5f, 24.0f)
                quadToRelative(0.0f, 1.45f, -1.025f, 2.475f)
                quadTo(25.45f, 27.5f, 24.0f, 27.5f)
                close()
                moveTo(24.0f, 24.0f)
                close()
                moveTo(21.8f, 41.0f)
                horizontalLineToRelative(4.4f)
                lineToRelative(0.7f, -5.6f)
                quadToRelative(1.65f, -0.4f, 3.125f, -1.25f)
                reflectiveQuadTo(32.7f, 32.1f)
                lineToRelative(5.3f, 2.3f)
                lineToRelative(2.0f, -3.6f)
                lineToRelative(-4.7f, -3.45f)
                quadToRelative(0.2f, -0.85f, 0.325f, -1.675f)
                quadToRelative(0.125f, -0.825f, 0.125f, -1.675f)
                quadToRelative(0.0f, -0.85f, -0.1f, -1.675f)
                quadToRelative(-0.1f, -0.825f, -0.35f, -1.675f)
                lineTo(40.0f, 17.2f)
                lineToRelative(-2.0f, -3.6f)
                lineToRelative(-5.3f, 2.3f)
                quadToRelative(-1.15f, -1.3f, -2.6f, -2.175f)
                quadToRelative(-1.45f, -0.875f, -3.2f, -1.125f)
                lineTo(26.2f, 7.0f)
                horizontalLineToRelative(-4.4f)
                lineToRelative(-0.7f, 5.6f)
                quadToRelative(-1.7f, 0.35f, -3.175f, 1.2f)
                quadToRelative(-1.475f, 0.85f, -2.625f, 2.1f)
                lineTo(10.0f, 13.6f)
                lineToRelative(-2.0f, 3.6f)
                lineToRelative(4.7f, 3.45f)
                quadToRelative(-0.2f, 0.85f, -0.325f, 1.675f)
                quadToRelative(-0.125f, 0.825f, -0.125f, 1.675f)
                quadToRelative(0.0f, 0.85f, 0.125f, 1.675f)
                quadToRelative(0.125f, 0.825f, 0.325f, 1.675f)
                lineTo(8.0f, 30.8f)
                lineToRelative(2.0f, 3.6f)
                lineToRelative(5.3f, -2.3f)
                quadToRelative(1.2f, 1.2f, 2.675f, 2.05f)
                quadTo(19.45f, 35.0f, 21.1f, 35.4f)
                close()
            }
        }
            .build()
        return _setting!!
    }

private var _setting: ImageVector? = null
