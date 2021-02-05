package com.saba.core.usecases.category.model

import androidx.annotation.DrawableRes
import com.saba.core.R
import kotlin.reflect.full.isSubclassOf

sealed class Category(var id: String, @DrawableRes val image: Int) {

    object Antiques : Category("ANTIQUES & COLLECTIBLES", R.drawable.ic_antiques)

    object Architecture : Category("ARCHITECTURE", R.drawable.ic_architecture)

    object Art : Category("ART", R.drawable.ic_art)

    object Bibles : Category("BIBLES", R.drawable.ic_bibles)

    object Biography : Category("BIOGRAPHY & AUTOBIOGRAPHY", R.drawable.ic_biography)

    object Body : Category("BODY, MIND & SPIRIT", R.drawable.ic_bodymindstpirit)

    object Business : Category("BUSINESS & ECONOMICS", R.drawable.ic_businessandeconomy)

    object Comic : Category("COMICS & GRAPHIC NOVELS", R.drawable.ic_comics)

    object Computer : Category("COMPUTERS", R.drawable.ic_computers)

    object Cooking : Category("COOKING", R.drawable.ic_cooking)

    object Hobbies : Category("CRAFTS & HOBBIES", R.drawable.ic_hobbies)

    object Design : Category("DESIGN", R.drawable.ic_design)

    object Drama : Category("DRAMA", R.drawable.ic_drama)

    object Education : Category("EDUCATION", R.drawable.ic_education)

    object Family : Category("FAMILY & RELATIONSHIPS", R.drawable.ic_family)

    object Fiction : Category("FICTION", R.drawable.ic_fiction)

    object Foreign : Category("FOREIGN LANGUAGE STUDY", R.drawable.ic_foreignlenguaje)

    object Games : Category("GAMES & ACTIVITIES", R.drawable.ic_gamesandactivities)

    object Gardening : Category("GARDENING", R.drawable.ic_gardering)

    object Health : Category("HEALTH & FITNESS", R.drawable.ic_healthfitness)

    object History : Category("HISTORY", R.drawable.ic_history)

    object House : Category("HOUSE & HOME", R.drawable.ic_hosehome)

    object Humor : Category("HUMOR", R.drawable.ic_comedy)

    object JuvenileFiction : Category("JUVENILE FICTION", R.drawable.ic_juvenilfiction)

    object JuvenileNonFiction : Category("JUVENILE NONFICTION", R.drawable.ic_juvenilnonfiction)

    object Language : Category("LANGUAGE ARTS & DISCIPLINES", R.drawable.ic_lenguajeartdisciplina)

    object Law : Category("LAW", R.drawable.ic_law)

    object LiteraryCollection : Category("LITERARY COLLECTIONS", R.drawable.ic_literaturecollections)

    object LiteraryCriticism : Category("LITERARY CRITICISM", R.drawable.ic_literarturecriticism)

    object Mathematics : Category("MATHEMATICS", R.drawable.ic_matematics)

    object Medical : Category("MEDICAL", R.drawable.ic_medical)

    object Music : Category("MUSIC", R.drawable.ic_music)

    object Nature : Category("NATURE", R.drawable.ic_nature)

    object Performing : Category("PERFORMING ARTS", R.drawable.ic_performingarts)

    object Pet : Category("PETS", R.drawable.ic_pets)

    object Philosophy : Category("PHILOSOPHY", R.drawable.ic_philosophy)

    object Photography : Category("PHOTOGRAPHY", R.drawable.ic_photography)

    object Poetry : Category("POETRY", R.drawable.ic_poetry)

    object Political : Category("POLITICAL SCIENCE", R.drawable.ic_politicalscience)

    object Psychology : Category("PSYCHOLOGY", R.drawable.ic_psicology)

    object Reference : Category("REFERENCE", R.drawable.ic_reference)

    object Religion : Category("RELIGION", R.drawable.ic_religion)

    object Science : Category("SCIENCE", R.drawable.ic_science)

    object SelfHelp : Category("SELF-HELP", R.drawable.ic_autohelp)

    object Social : Category("SOCIAL SCIENCE", R.drawable.ic_socialscience)

    object Sport : Category("SPORTS & RECREATION", R.drawable.ic_sports)

    object Study : Category("STUDY AIDS", R.drawable.ic_studyaids)

    object Technology : Category("TECHNOLOGY & ENGINEERING", R.drawable.ic_tecnhologyenginering)

    object Transportation : Category("TRANSPORTATION", R.drawable.ic_transportation)

    object Travel : Category("TRAVEL", R.drawable.ic_travel)

    object TrueCrime : Category("TRUE CRIME", R.drawable.ic_truecrime)

    object YoungAdultFiction : Category("YOUNG ADULT FICTION", R.drawable.ic_youngadultfiction)

    object YoungAdultNonFiction : Category("YOUNG ADULT NONFICTION", R.drawable.ic_youngadultnonfiction)

    companion object {
        fun getValues() = Category::class.nestedClasses.filter { klass ->
            klass.isSubclassOf(Category::class)
        }.map { klass -> klass.objectInstance }
            .filterIsInstance<Category>()

    }
}

