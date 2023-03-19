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

public val IconPack.Home: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = Builder(
            name = "Home",
            defaultWidth = 48.0.dp,
            defaultHeight = 48.0.dp,
            viewportWidth = 48.0f,
            viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.40331f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.2688f, 47.7311f)
                curveTo(0.121f, 47.5832f, 0.0f, 42.0005f, 0.0f, 35.3251f)
                verticalLineTo(23.1881f)
                lineTo(11.5963f, 11.594f)
                curveTo(19.0569f, 4.1349f, 23.4802f, 0.0f, 23.9991f, 0.0f)
                curveTo(24.5183f, 0.0f, 28.938f, 4.1352f, 36.4028f, 11.6053f)
                lineTo(48.0f, 23.2105f)
                lineTo(47.8931f, 35.5044f)
                lineTo(47.7863f, 47.7983f)
                lineTo(38.9151f, 47.9073f)
                curveToRelative(-7.9839f, 0.0981f, -8.9216f, 0.0399f, -9.3758f, -0.5813f)
                curveToRelative(-0.3597f, -0.4921f, -0.5087f, -2.8396f, -0.5189f, -8.1762f)
                curveToRelative(-0.0101f, -5.3114f, -0.1645f, -7.7566f, -0.5313f, -8.4178f)
                curveToRelative(-1.3153f, -2.3709f, -4.9845f, -3.2323f, -7.2908f, -1.7116f)
                curveToRelative(-2.0692f, 1.3644f, -2.2153f, 2.0214f, -2.2308f, 10.0314f)
                curveToRelative(-0.0083f, 4.292f, -0.1892f, 7.7148f, -0.4317f, 8.1681f)
                curveTo(18.1426f, 47.9545f, 17.606f, 48.0f, 9.328f, 48.0f)
                curveTo(4.4933f, 48.0f, 0.4167f, 47.879f, 0.2688f, 47.7311f)
                close()
            }
        }
            .build()
        return _home!!
    }

private var _home: ImageVector? = null
