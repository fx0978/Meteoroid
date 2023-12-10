## About
Welcome to Meteoroids! This game is a simple implementation of a top-down shooter by Feng Xiong for the UST Graduate class SEIS 601 Foundations of Java I.

The game's intent is to allow the user to accumulate points by shooting down meteoroids. Reds are 2 points and Blues are 1 point. As of this writing, there is no victory condition. The user may shoot til they come into contact with a meteoroid. From that point they can restart or return to the menu.

This README is intended to help guide players and developers in understanding some basics.

## Running the game
You can run the game in VS Code by running the file at /src/main/Main.java

## Controls
Control the spaceship with the following scheme:

W - Up

A - Left

S - Down

D - Right

Space bar to shoot your bullets/laser

Use your mouse to make a menu option selection

press the "P" key or click outside of the window to pause the game

## Folder Structure - For Devs

The workspace contains the following directories under /src:
 - /characters - contains the classes of objects represented in the game

 - /inputs - Contains the inputs.java file which transcribes the user's inputs into method calls to do things in the game

 - /music - Contains the Midi files to be played by the game

 - /main - Contains the main components of the game such as the panel, frame, game loop start, object creations etc.

 - /resources - Mainly contains .png files from which the objects represented in the game are used to display as.

 - /states - Contains the various game states and related classes

 - /util - Contains miscellaneous tools and menthods. Of note are the BeatBox (music creation), and "global" constants.

## Adding Music - For Devs
To add music you can run BeatBox.java. From which you can select a various instruments to play at different intervals. Once you are satisfied with your selection then select the "Export" button. This will automatically create a file called "Music.mid" under the /src/music path. You can rename this to whatever you want.

Then in your code trigger the music play back by using the MusicPlayer class object's method .PlayMusic(song) or .PlayMusicLoop(song) where song is the full name (including extension) of the file you created above in the BeatBox.

## GitHub Repository
You can find the repository for this code at: https://github.com/fx0978/Meteoroid

## Sources
This implementation followed the following guide on YouTube by the channel "Kaarin Gaming" to help construct the underlying game loop logic:
https://www.youtube.com/watch?v=6_N8QZ47toY&list=PL4rzdwizLaxYmltJQRjq18a9gsSyEQQ-0

The following site was used to create graphics:
https://www.piskelapp.com/p/create/sprite

Modified code from Head First Java Third Edition ch.15 BeatBox.java was used to help in generating the music files. You can find the whole book code available at: https://resources.oreilly.com/examples/0636920034452

defaultFontStyle was grabbed from: https://www.dafont.com/pixel-operator.font

Chat GPT was used as a helper to develop this game but most of the logic and implementation was done by Feng Xiong in tandem with the above stated guide.