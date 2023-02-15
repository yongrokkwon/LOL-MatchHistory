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

public val IconPack.Community: ImageVector
    get() {
        if (_community != null) {
            return _community!!
        }
        _community = Builder(name = "Community", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(6.0f, 36.0f)
                verticalLineToRelative(-3.0f)
                horizontalLineToRelative(36.0f)
                verticalLineToRelative(3.0f)
                close()
                moveTo(6.0f, 25.5f)
                verticalLineToRelative(-3.0f)
                horizontalLineToRelative(36.0f)
                verticalLineToRelative(3.0f)
                close()
                moveTo(6.0f, 15.0f)
                verticalLineToRelative(-3.0f)
                horizontalLineToRelative(36.0f)
                verticalLineToRelative(3.0f)
                close()
            }
        }
        .build()
        return _community!!
    }

private var _community: ImageVector? = null
