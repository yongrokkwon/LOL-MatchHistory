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

public val IconPack.Esports: ImageVector
    get() {
        if (_esports != null) {
            return _esports!!
        }
        _esports = Builder(name = "Esports", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 12.7f, viewportHeight = 12.7f).apply {
            path(fill = SolidColor(Color(0xFF032133)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 0.264583f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveToRelative(2.3224f, 1.7982f)
                curveToRelative(-0.5607f, 0.0f, -1.7371f, -0.2302f, -2.1526f, 0.254f)
                curveToRelative(-0.2422f, 0.2823f, -0.1542f, 0.8116f, -0.1541f, 1.1606f)
                curveToRelative(7.0E-4f, 1.7261f, 1.0715f, 2.7235f, 2.6911f, 2.7283f)
                curveToRelative(0.2511f, 1.5281f, 1.6776f, 3.0955f, 3.1717f, 3.2335f)
                lineToRelative(0.0f, 2.6272f)
                curveToRelative(-0.3687f, 0.0f, -0.9964f, -0.1242f, -1.3146f, 0.1039f)
                curveToRelative(-0.7979f, 0.5718f, 0.7835f, 0.7045f, 1.0262f, 0.7045f)
                curveToRelative(0.6349f, 0.0f, 1.7994f, 0.2378f, 2.3721f, -0.0609f)
                curveToRelative(0.3015f, -0.1572f, 0.1836f, -0.5655f, -0.0797f, -0.6865f)
                curveToRelative(-0.3445f, -0.1582f, -0.8656f, -0.0609f, -1.2352f, -0.0609f)
                curveToRelative(0.0f, -0.6207f, -0.2265f, -1.8619f, 0.0988f, -2.3946f)
                curveToRelative(0.3101f, -0.5076f, 1.3559f, -0.7267f, 1.8148f, -1.1724f)
                curveToRelative(0.6055f, -0.588f, 0.7974f, -1.4459f, 1.3252f, -2.0596f)
                curveToRelative(0.4576f, -0.5321f, 1.4484f, -0.4063f, 2.0024f, -0.9889f)
                curveToRelative(0.52f, -0.5466f, 1.24f, -2.5551f, 0.4838f, -3.1772f)
                curveToRelative(-0.5085f, -0.4184f, -1.5622f, -0.2111f, -2.1689f, -0.2111f)
                curveToRelative(-0.1977f, -2.3627f, -3.3273f, -1.7178f, -4.9017f, -1.7178f)
                curveToRelative(-0.6582f, 0.0f, -1.397f, -0.0912f, -2.0183f, 0.1874f)
                curveToRelative(-0.6117f, 0.2743f, -0.9056f, 0.8665f, -0.9611f, 1.5304f)
                moveToRelative(0.0f, 0.8084f)
                lineToRelative(0.0f, 2.5262f)
                curveToRelative(-0.7824f, -0.1233f, -2.2887f, -1.4187f, -1.3837f, -2.3501f)
                curveToRelative(0.3022f, -0.311f, 0.9971f, -0.1761f, 1.3837f, -0.1761f)
                moveToRelative(7.9773f, 2.5262f)
                lineToRelative(0.0f, -2.5262f)
                curveToRelative(0.3866f, 0.0f, 1.0815f, -0.1349f, 1.3837f, 0.1761f)
                curveToRelative(0.8983f, 0.9245f, -0.6074f, 2.2278f, -1.3837f, 2.3501f)
                close()
            }
        }
        .build()
        return _esports!!
    }

private var _esports: ImageVector? = null
