package com.juancrfig;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Marterio extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture valalaImg;
    private Texture backgroundImg;
    private float valalaImgX, valalaImgY;
    private float backgroundImgX, backgroundImgY;
    private float alpha = 0f;     // For fade effect
    private float fadeSpeed = 0.125f; // Controls fade speed

    // State management for our sequence
    private enum State { FIRST_FADE_IN, FIRST_DISPLAY, FIRST_FADE_OUT, SECOND_FADE_IN, COMPLETE }
    private State currentState = State.FIRST_FADE_IN;

    // How long to display first image at full opacity
    private float displayDuration = 0.5f; // 2 seconds
    private float displayTimer = 0f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        valalaImg = new Texture("images/valala_productions.png");
        backgroundImg = new Texture("images/marterio_background.png");

        // Center first image
        valalaImgX = (Gdx.graphics.getWidth() - valalaImg.getWidth()) / 2f;
        valalaImgY = (Gdx.graphics.getHeight() - valalaImg.getHeight()) / 2f;

        // Center second image
        backgroundImgX = (Gdx.graphics.getWidth() - backgroundImg.getWidth()) / 2f;
        backgroundImgY = (Gdx.graphics.getHeight() - backgroundImg.getHeight()) / 2f;
    }

    @Override
    public void render() {
        // Clear the screen with black background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        // Update state
        updateState(deltaTime);

        batch.begin();

        // Draw the appropriate image based on current state
        if (currentState == State.FIRST_FADE_IN ||
            currentState == State.FIRST_DISPLAY ||
            currentState == State.FIRST_FADE_OUT) {
            batch.setColor(1, 1, 1, alpha);
            batch.draw(valalaImg, valalaImgX, valalaImgY);
        } else {
            batch.setColor(1, 1, 1, alpha);
            batch.draw(backgroundImg, backgroundImgX, backgroundImgY);
        }

        batch.end();
    }

    private void updateState(float deltaTime) {
        switch (currentState) {
            case FIRST_FADE_IN:
                // Increase alpha until image is fully visible
                alpha += fadeSpeed * deltaTime;
                if (alpha >= 1f) {
                    alpha = 1f;
                    currentState = State.FIRST_DISPLAY;
                }
                break;

            case FIRST_DISPLAY:
                // Display first image for displayDuration seconds
                displayTimer += deltaTime;
                if (displayTimer >= displayDuration) {
                    currentState = State.FIRST_FADE_OUT;
                }
                break;

            case FIRST_FADE_OUT:
                // Decrease alpha until image is fully transparent
                alpha -= fadeSpeed * deltaTime;
                if (alpha <= 0f) {
                    alpha = 0f;
                    currentState = State.SECOND_FADE_IN;
                }
                break;

            case SECOND_FADE_IN:
                // Increase alpha until second image is fully visible
                alpha += fadeSpeed * deltaTime;
                if (alpha >= 1f) {
                    alpha = 1f;
                    currentState = State.COMPLETE;
                }
                break;

            case COMPLETE:
                // Both images have been shown - we're in the final state
                // This is where you could transition to your game's main menu or gameplay
                break;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        valalaImg.dispose();
        backgroundImg.dispose();
    }
}
