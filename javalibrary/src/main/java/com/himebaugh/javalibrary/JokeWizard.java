package com.himebaugh.javalibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JokeWizard {

    static Random random = new Random();

    // Jokes from https://www.ducksters.com/jokes/computer.php

    static List<String> jokeList = new ArrayList<>(Arrays.asList(
            "Q: What did the spider do on the computer?\n" + "A: Made a website!",
            "Q: What did the computer do at lunchtime?\n" + "A: Had a byte!",
            "Q: What does a baby computer call his father?\n" + "A: Data!",
            "Q: Why did the computer keep sneezing?\n" + "A: It had a virus!",
            "Q: What is a computer virus?\n" + "A: A terminal illness!",
            "Q: Why was the computer cold?\n" + "A: It left it's Windows open! ",
            "Q: Why was there a bug in the computer?\n" + "A: Because it was looking for a byte to eat?",
            "Q: Why did the computer squeak?\n" + "A: Because someone stepped on it's mouse! ",
            "Q: What do you get when you cross a computer and a life guard?\n" + "A: A screensaver!",
            "Q: Where do all the cool mice live?\n" + "A: In their mousepads",
            "Q: What do you get when you cross a computer with an elephant?\n" + "A: Lots of memory!"));

    public static String getJoke() {
        int index = random.nextInt(jokeList.size());
        return jokeList.get(index);
    }

//    public String getJoke(){
//
//        return "A hand-crafted joke";
//    }

}
