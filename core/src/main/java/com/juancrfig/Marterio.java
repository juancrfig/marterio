/* The package keyword allows to link classes from different files into a unique namespace.
Classes within the same package can access each other's package-private members. */
package com.juancrfig;

// Manages Lifecycle methods for the game
import com.badlogic.gdx.Game;
import com.juancrfig.screens.SplashScreen; // Explicit import

// The Game class acts as the central coordinator of the videogame
public class Marterio extends Game {

    /* The "create" method is the entry point of the game. When the game is launched
    LibGDX calls this method to initialize resources, load assets, and so on... */
    @Override
    public void create() {
        // Designates the current active screen.
        setScreen(new SplashScreen(this));
    }
}
