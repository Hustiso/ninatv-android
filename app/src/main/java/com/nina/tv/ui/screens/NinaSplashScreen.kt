package com.nina.tv.ui.screens

import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nina.tv.R
import com.nina.tv.ui.theme.NinaColorScheme
import kotlinx.coroutines.delay

/**
 * Nina TV custom animated splash screen.
 * Ports the ninaflix-tizen splash: bouncing toddler face, expanding rings,
 * title animation, loading bar, and laugh audio.
 */
@Composable
fun NinaSplashScreen(
    onSplashComplete: () -> Unit
) {
    val context = LocalContext.current
    var showSplash by remember { mutableStateOf(true) }

    // Play laugh audio on first composition
    LaunchedEffect(Unit) {
        try {
            val mp = MediaPlayer.create(context, R.raw.laugh)
            mp?.setVolume(0.8f, 0.8f)
            mp?.setOnCompletionListener { it.release() }
            mp?.start()
        } catch (_: Exception) {}

        // Splash duration ~3.2s matching tizen
        delay(3200)
        showSplash = false
        delay(800) // fade out
        onSplashComplete()
    }

    if (showSplash) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0D0D0D)),
            contentAlignment = Alignment.Center
        ) {
            // Expanding rings
            ExpandingRings()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Bouncing toddler face
                BouncingFace()

                Spacer(modifier = Modifier.height(40.dp))

                // Title "nina tv"
                SplashTitle()

                Spacer(modifier = Modifier.height(8.dp))

                // Subtitle
                SplashSubtitle()

                Spacer(modifier = Modifier.height(44.dp))

                // Loading bar
                LoadingBar()
            }
        }
    }
}

@Composable
private fun BouncingFace() {
    val infiniteTransition = rememberInfiniteTransition(label = "splashDance")

    // Bounce Y
    val bounceY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -22f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 800
                0f at 0
                -18f at 80
                -8f at 160
                -22f at 240
                -5f at 320
                -15f at 400
                -3f at 480
                -12f at 560
                -6f at 640
                -2f at 720
                0f at 800
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "bounceY"
    )

    // Rotation
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 800
                0f at 0
                -4f at 80
                5f at 160
                -3f at 240
                4f at 320
                -2f at 400
                2f at 480
                -1f at 560
                1f at 640
                0f at 720
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Scale
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 800
                1f at 0
                1.06f at 80
                0.97f at 160
                1.08f at 240
                0.98f at 320
                1.04f at 400
                1f at 480
                1.02f at 560
                1.01f at 640
                1f at 720
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "scale"
    )

    Image(
        painter = painterResource(id = R.drawable.nina_face),
        contentDescription = "Nina",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(320.dp)
            .graphicsLayer {
                translationY = bounceY.dp.toPx()
                rotationZ = rotation
                scaleX = scale
                scaleY = scale
            }
    )
}

@Composable
private fun ExpandingRings() {
    val ringColor = Color(0xFFFF6B6B)

    for (i in 0..2) {
        val delayMs = i * 300
        val alpha = when (i) {
            0 -> 0.15f
            1 -> 0.10f
            else -> 0.06f
        }

        val infiniteTransition = rememberInfiniteTransition(label = "ring$i")
        val scale by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 2.5f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1500,
                    delayMillis = delayMs,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "ringScale$i"
        )

        val ringAlpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1500,
                    delayMillis = delayMs,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "ringAlpha$i"
        )

        Box(
            modifier = Modifier
                .size(380.dp)
                .scale(scale)
                .alpha(ringAlpha * alpha)
                .clip(CircleShape)
                .background(Color.Transparent)
                .then(
                    Modifier.graphicsLayer {
                        // Ring border effect via shadow
                    }
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .then(
                        Modifier.graphicsLayer {
                            shadowElevation = 0f
                        }
                    )
            )
        }
    }
}

@Composable
private fun SplashTitle() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 700, delayMillis = 100, easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)),
        label = "titleAlpha"
    )

    val offsetY by animateFloatAsState(
        targetValue = if (visible) 0f else 24f,
        animationSpec = tween(durationMillis = 700, delayMillis = 100, easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)),
        label = "titleOffset"
    )

    Row(
        modifier = Modifier
            .alpha(alpha)
            .graphicsLayer { translationY = offsetY.dp.toPx() },
        verticalAlignment = Alignment.Bottom
    ) {
        androidx.compose.material3.Text(
            text = "nina",
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-3).sp
        )
        androidx.compose.material3.Text(
            text = "tv",
            color = Color(0xFFFF6B6B),
            fontSize = 64.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-3).sp
        )
    }
}

@Composable
private fun SplashSubtitle() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(350)
        visible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "subAlpha"
    )

    val offsetY by animateFloatAsState(
        targetValue = if (visible) 0f else 24f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "subOffset"
    )

    androidx.compose.material3.Text(
        text = "made with love",
        color = Color(0xFF666666),
        fontSize = 14.sp,
        letterSpacing = 4.sp,
        modifier = Modifier
            .alpha(alpha)
            .graphicsLayer { translationY = offsetY.dp.toPx() }
    )
}

@Composable
private fun LoadingBar() {
    var visible by remember { mutableStateOf(false) }
    var started by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600)
        visible = true
        delay(200)
        started = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "barAlpha"
    )

    val offsetY by animateFloatAsState(
        targetValue = if (visible) 0f else 24f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "barOffset"
    )

    val barWidth by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(durationMillis = 1800, delayMillis = 800, easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)),
        label = "barFill"
    )

    Box(
        modifier = Modifier
            .width(240.dp)
            .height(3.dp)
            .alpha(alpha)
            .graphicsLayer { translationY = offsetY.dp.toPx() }
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(3.dp))
            .background(Color(0x0AFFFFFF))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(barWidth)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(3.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFF6B6B), Color(0xFFFF8E8E))
                    )
                )
        )
    }
}
