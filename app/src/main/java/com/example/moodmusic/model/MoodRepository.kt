package com.example.moodmusic.model
import com.example.moodmusic.R

class MoodRepository {

    fun getMoods(): List<Mood> = listOf(
        Mood("happy", "Happy", "\uD83D\uDE00", R.raw.happy),
        Mood("sad", "Sad", "\uD83D\uDE22", R.raw.sad),
        Mood("angry", "Angry", "\uD83D\uDE21", R.raw.angry),
        Mood("sleepy", "Sleepy", "\uD83D\uDE34", R.raw.sleepy),
        Mood("excited", "Excited", "\uD83E\uDD29", R.raw.excited),
        Mood("calm", "Calm", "\uD83D\uDE0C", R.raw.calm),
        Mood("love", "Love", "\uD83E\uDD70", R.raw.love),
        Mood("cool", "Cool", "\uD83D\uDE0E", R.raw.cool),
        Mood("crying", "Crying", "\uD83D\uDE2D", R.raw.crying),
        Mood("shy", "Shy", "\uD83E\uDD2D", R.raw.shy),
        Mood("surprised", "Surprised", "\uD83D\uDE32", R.raw.surprised),
        Mood("confused", "Confused", "\uD83D\uDE15", R.raw.confused),
        Mood("sick", "Sick", "\uD83E\uDD12", R.raw.sick),
        Mood("party", "Party", "\uD83E\uDD73", R.raw.party),
        Mood("thinking", "Thinking", "\uD83E\uDD14", R.raw.thinking),
        Mood("nervous", "Nervous", "\uD83D\uDE2C", R.raw.nervous),
        Mood("relaxed", "Relaxed", "\uD83D\uDE0A", R.raw.relaxed),
        Mood("bored", "Bored", "\uD83D\uDE10", R.raw.bored),
        Mood("wink", "Wink", "\uD83D\uDE09", R.raw.wink),
        Mood("furious", "Furious", "\uD83E\uDD2C", R.raw.furious)
    )

    fun getMoodById(id: String): Mood? {
        return getMoods().find { it.id == id }
    }
}