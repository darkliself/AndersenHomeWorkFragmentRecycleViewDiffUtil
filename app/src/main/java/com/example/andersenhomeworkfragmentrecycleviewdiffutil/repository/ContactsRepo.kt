package com.example.andersenhomeworkfragmentrecycleviewdiffutil.repository


const val NUMBER_PREFIX = "+3806600000"
const val RON_SWANSON_NAME = "Ron Swanson"
const val RON_SWANSON_ABOUT =
    "Ronald Ulysses Swanson is a fictional character portrayed by Nick Offerman in Parks and Recreation, a situational comedy television series. The character was created by Michael Schur and Greg Daniels with inspiration from a real-life Libertarian elected official. Offerman provided creative input, and aspects of his own personality were folded into the character. NBC was initially reluctant to cast Offerman in the role, despite the creators' intentions, until the network finally agreed five months later."
const val ERIK_CARTMAN_NAME = "Erik Cartman"
const val ERIK_CARTMAN_ABOUT =
    "Eric Theodore Cartman, commonly referred to by his surname,[1] is a fictional character in the adult animated sitcom South Park, created by Trey Parker and Matt Stone. He is voiced by Parker, and is one of the series' four main characters, alongside Stan Marsh, Kyle Broflovski, and Kenny McCormick. He first appeared with the name Kenny in the short film The Spirit of Christmas (1992), and later appeared in the 1995 film of the same title before debuting in \"Cartman Gets an Anal Probe\", the first episode of the series, on August 13, 1997.\n" +
            "\n" +
            "Cartman is an elementary school student who lives with his single mother, Liane, in the eponymous Colorado town. Cartman is principally characterized by his obesity, his amorality, and his bigoted and especially antisemitic disposition, being described by Parker and Stone as \"a little Archie Bunker.\" In later seasons, particuarly following the fifth season episode \"Scott Tenorman Must Die\", Cartman exhibits increasingly sociopathic and manipulative behavior. The latter is showcased through Cartman's various schemes, the majority of which fail either due to opposition from other characters or Cartman's own hubris, frequently leaving Cartman in complete humiliation."

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
                    name = ERIK_CARTMAN_NAME + index,
                    number = NUMBER_PREFIX + index,
                    avatarUrl = AVA_URL + (AVA_ENDPOINT + index),
                    about = ERIK_CARTMAN_ABOUT
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