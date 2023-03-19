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

public val IconPack.Champion: ImageVector
    get() {
        if (_champion != null) {
            return _champion!!
        }
        _champion = Builder(
            name = "Champion",
            defaultWidth = 48.0.dp,
            defaultHeight = 48.0.dp,
            viewportWidth = 12.7f,
            viewportHeight = 12.7f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0746494f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(3.1234f, 12.5505f)
                curveToRelative(-1.2612f, -0.3921f, -2.2945f, -1.4333f, -2.905f, -2.9271f)
                lineToRelative(-0.2183f, -0.5343f)
                lineToRelative(0.2502f, -0.2443f)
                curveToRelative(0.5913f, -0.5775f, 0.6614f, -0.8849f, 0.7327f, -3.2149f)
                curveToRelative(0.0555f, -1.8144f, 0.068f, -1.9256f, 0.2746f, -2.4421f)
                curveToRelative(0.5326f, -1.3319f, 1.753f, -2.376f, 3.4614f, -2.9612f)
                curveToRelative(0.619f, -0.212f, 0.7242f, -0.2267f, 1.6291f, -0.2267f)
                curveToRelative(0.9049f, 0.0f, 1.0101f, 0.0146f, 1.6291f, 0.2267f)
                curveToRelative(1.4358f, 0.4919f, 2.57f, 1.3439f, 3.1637f, 2.3768f)
                curveToRelative(0.4778f, 0.8311f, 0.5646f, 1.2874f, 0.5691f, 2.9919f)
                curveToRelative(0.0058f, 2.1801f, 0.1265f, 2.7096f, 0.7434f, 3.2627f)
                lineToRelative(0.2467f, 0.2212f)
                lineToRelative(-0.2204f, 0.5392f)
                curveToRelative(-0.7248f, 1.7732f, -2.0959f, 2.9449f, -3.5643f, 3.046f)
                lineToRelative(-0.5158f, 0.0355f)
                lineToRelative(0.2829f, -0.3604f)
                curveToRelative(0.783f, -0.9978f, 0.8728f, -2.5211f, 0.2432f, -4.1293f)
                curveToRelative(-0.1097f, -0.2802f, -0.1994f, -0.5194f, -0.1994f, -0.5316f)
                curveToRelative(0.0f, -0.0122f, 0.136f, -0.0571f, 0.3023f, -0.0998f)
                curveToRelative(0.4638f, -0.1192f, 1.0355f, -0.4597f, 1.3027f, -0.7761f)
                curveToRelative(0.3248f, -0.3845f, 0.3876f, -0.7887f, 0.21f, -1.3513f)
                lineToRelative(-0.14f, -0.4436f)
                horizontalLineToRelative(-0.3564f)
                curveToRelative(-0.414f, 0.0f, -1.2438f, 0.224f, -1.5976f, 0.4313f)
                curveToRelative(-0.384f, 0.225f, -0.7637f, 0.5453f, -1.0172f, 0.8582f)
                lineToRelative(-0.2327f, 0.2872f)
                lineToRelative(-0.0216f, 1.7086f)
                lineToRelative(-0.0216f, 1.7086f)
                lineToRelative(-0.4039f, 0.2974f)
                lineToRelative(-0.4039f, 0.2974f)
                lineToRelative(-0.4224f, -0.312f)
                lineToRelative(-0.4224f, -0.312f)
                lineToRelative(-1.0E-4f, -1.7046f)
                lineToRelative(-1.0E-4f, -1.7046f)
                lineToRelative(-0.3542f, -0.3869f)
                curveToRelative(-0.6191f, -0.6762f, -1.6785f, -1.1685f, -2.5146f, -1.1685f)
                curveToRelative(-0.3335f, 0.0f, -0.3432f, 0.0067f, -0.4691f, 0.3234f)
                curveToRelative(-0.1441f, 0.3626f, -0.1677f, 0.8926f, -0.0515f, 1.1547f)
                curveToRelative(0.1389f, 0.3133f, 0.6782f, 0.7512f, 1.1725f, 0.9521f)
                curveToRelative(0.266f, 0.1081f, 0.5333f, 0.1966f, 0.5939f, 0.1966f)
                curveToRelative(0.0666f, 0.0f, 0.0941f, 0.0479f, 0.0696f, 0.121f)
                curveToRelative(-0.0223f, 0.0665f, -0.1355f, 0.397f, -0.2515f, 0.7343f)
                curveToRelative(-0.5232f, 1.5211f, -0.3906f, 2.9886f, 0.3522f, 3.8971f)
                lineToRelative(0.2402f, 0.2938f)
                lineToRelative(-0.3813f, -0.0026f)
                curveToRelative(-0.2097f, -0.0013f, -0.5626f, -0.0589f, -0.7843f, -0.1278f)
                close()
            }
        }
            .build()
        return _champion!!
    }

private var _champion: ImageVector? = null
