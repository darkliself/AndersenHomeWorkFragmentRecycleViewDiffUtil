package com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository


const val NUMBER_PREFIX = "+3806600000"
const val RON_SWANSON_NAME = "Ron Swanson"
const val RON_SWANSON_ABOUT =
    "Ronald Ulysses Swanson is a fictional character portrayed by Nick Offerman in Parks and Recreation, a situational comedy television series. The character was created by Michael Schur and Greg Daniels with inspiration from a real-life Libertarian elected official. Offerman provided creative input, and aspects of his own personality were folded into the character. NBC was initially reluctant to cast Offerman in the role, despite the creators' intentions, until the network finally agreed five months later."
const val RICK_SANCHEZ_NAME = "Rick Sanchez"
const val RICK_SANCHEZ_ABOUT = "Richard Daniel \"Rick\" Sanchez is one of the two eponymous characters from the Adult Swim animated television series Rick and Morty and resulting franchise. Created by Justin Roiland and Dan Harmon, and voiced by the former in the first six seasons of the series and promotional media,[1] and Yōhei Tadano in Rick and Morty: The Anime, Sanchez is a misanthropic, alcoholic scientist inspired by Christopher Lloyd's Dr. Emmett \"Doc\" Brown from Back to the Future and Reed Richards / Mr. Fantastic from Marvel Comics. In September 2021, Lloyd portrayed Sanchez himself in a series of promotional interstitials for the series.[2][3]\n" +
        "\n" +
        "Known for his reckless, nihilistic behavior, pragmatic moral ambiguity and pessimistic personality, the character has been well received. Rick C-137 is a mad scientist who seems to know everything in the universe and thus finds life a traumatizing and pointless experience. Following the murder of his wife Diane and daughter Beth Sanchez in his native reality (C-137) by his parallel self Rick Prime, Rick dedicates his life to hunting Prime down, developing inter-dimensional travel and building a wall around the segment of the multiverse where Rick is \"The Smartest Man in the Universe\", dubbed the Central Finite Curve, to narrow his search, massacring countless alternate versions of himself in the process of hunting down Prime before resigning himself to failure, founding the Citadel of Ricks out of the survivors, and retiring to move in with the family of an adult version of Beth (Smith) from Prime's own native reality (whom Prime had abandoned when Beth was a teenager) out of the slim hope of Prime one day returning so Rick could complete his vendetta, consisting of his son-in-law Jerry and teenage grandchildren Summer and Morty Smith, going on a series of adventures with the latter, over the course of which events in the first and fifth seasons lead to Morty and Summer having two children of their own, Mortimer Junior and Naruto.\n" +
        "\n" +
        "The first three volumes of the Rick and Morty comic series follow the Rick and Morty of Dimension C-132 while most issues of subsequent volumes (following the \"Head-Space\" arc in which Rick C-132 is killed) follow the main Rick (C-137) and Morty (Prime) from the television series, with the final volume (\"The Rickoning\") and Rick and Morty Go to Hell following another alternate Rick (and Morty) identified as Devil Rick in the latter series, and featuring a Girl Rick designed after cosplayer Santana Maynard by series writer Kyle Starks;[5] the video game Pocket Mortys meanwhile follows the Rick and Morty of C-123.[6] The main character Rick of the franchise and their alternative selves have received a positive critical reception"

const val RANDY_MARSH_NAME = "Randy Marsh"
const val RANDY_MARSH_ABOUT =
    "Randy S. Marsh is a fictional character in the American adult animated sitcom South Park. He is the most prominent parent on the series and a married father who raises his son Stan and daughter Shelley along with his wife Sharon in the fictional town of South Park, Colorado. His first name is derived from the first name of series co-creator Trey Parker's father,[1] and Parker describes him as \"the biggest dingbat in the entire show\".[2] According to the season 16 episode \"Reverse Cowgirl\", the Marsh home address was 260 Avenue de los Mexicanos until their move to Tegridy Farms in season 23.\n" +
            "\n" +
            "In tradition with South Park's animation style, Randy is composed of simple geometrical shapes, animated with the use of a computer, and rendered to mimic the appearance of construction paper cutout compositions animated through the use of stop motion, which was the technique used to animate the Spirit of Christmas short films.[3] Randy is voiced by Trey Parker.[4]"
const val SENSAI_NAME = "Sensai"
const val SENSAI_ABOUT =
    "Sensai is an overweight martial arts instructor who teaches the fighting style of Death Kwon Do, using a notebook full of drawings as his technique book. He often reacts to bad news by shouting \"NOOOOO!\" He only appears with a few lines/quotes. In \"Death Kwon Do-Livery,\" he is revealed to have an apprentice, Jerry-San. He returned in the episode \"Sandwich of Death\" as the owner of Death Kwon Do Pizza and Subs"
const val AVA_URL = "https://picsum.photos/"
const val AVA_ENDPOINT = 200


class ContactsRepo {
    companion object {
        var contactsList = mutableListOf<Contact>().apply {
            repeat(120) { index ->
                add(fillContacts(index))
            }
        }

        fun getContact(index: Int): Contact {
            return contactsList[index]
        }

        private fun fillContacts(index: Int): Contact {
            return if (index % 4 == 0) {
                Contact(
                    id = index,
                    name = RON_SWANSON_NAME + index,
                    number = NUMBER_PREFIX + index,
                    avatarUrl = AVA_URL + (AVA_ENDPOINT + index),
                    about = RON_SWANSON_ABOUT
                )
            } else if (index % 3 == 0) {
                Contact(
                    id = index,
                    name = RICK_SANCHEZ_NAME + index,
                    number = NUMBER_PREFIX + index,
                    avatarUrl = AVA_URL + (AVA_ENDPOINT + index),
                    about = RICK_SANCHEZ_ABOUT
                )
            } else if (index % 2 == 0) {
                Contact(
                    id = index,
                    name = RANDY_MARSH_NAME + index,
                    number = NUMBER_PREFIX + index,
                    avatarUrl = AVA_URL + (AVA_ENDPOINT + index),
                    about = RANDY_MARSH_ABOUT
                )
            } else {
                Contact(
                    id = index,
                    name = SENSAI_NAME + index,
                    number = NUMBER_PREFIX + index,
                    avatarUrl = AVA_URL + (AVA_ENDPOINT + index),
                    about = SENSAI_ABOUT
                )
            }

        }
    }
}