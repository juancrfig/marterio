package com.juancrfig.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.juancrfig.Marterio;
import com.badlogic.gdx.audio.Music;

public class SplashScreen implements Screen {

    // The "final" keyword forbids to change a variable's value
    private final Marterio game;
    private SpriteBatch batch;
    private Music backgroundMusic;
    private Texture valalaImg;
    private float valalaImgX, valalaImgY;
    private float alpha = 0f;
    private float displayTimer = 0f;
    private final float musicDuration = 10f; // Approximate duration in seconds
    private final float fadeDuration = 3f; // Duration for fade-in and fade-out

    private enum State {
        FADE_IN, DISPLAY, FADE_OUT, COMPLETE
    }
    private State currentState = State.FADE_IN;

    public SplashScreen(Marterio game) {
        this.game = game;
        loadResources();
        calculatePositions();
        backgroundMusic.play();
    }

    private void loadResources() {
        batch = new SpriteBatch();
        valalaImg = new Texture("images/valala_productions.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/industrial_drum_loop.wav"));
        backgroundMusic.setLooping(false);
    }

    private void calculatePositions() {
        valalaImgX = (Gdx.graphics.getWidth() - valalaImg.getWidth()) / 2f;
        valalaImgY = (Gdx.graphics.getHeight() - valalaImg.getHeight()) / 2f;
    }

    @Override
    // This method is called aprox 60 times per second. "delta" is the time elapsed since the last frame
    public void render(float delta) {
        // Sets a color to for clearing the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        // Uses the selected color for clearing the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateState(delta);

        // Tell the "SpriteBatch" to start recording drawing operations
        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(valalaImg, valalaImgX, valalaImgY);
        // Flush all queued drawing operations to the screen at once
        batch.end();
    }

    private void updateState(float delta) {
        switch (currentState) {
            case FADE_IN:
                alpha += delta / fadeDuration;
                if (alpha >= 1) {
                    alpha = 1;
                    currentState = State.DISPLAY;
                }
                break;

            case DISPLAY:
                displayTimer += delta;
                if (displayTimer >= musicDuration - (2 * fadeDuration)) {
                    currentState = State.FADE_OUT;
                }
                break;

            case FADE_OUT:
                alpha -= delta / fadeDuration;
                if (alpha <= 0) {
                    alpha = 0;
                    currentState = State.COMPLETE;
                    game.setScreen(new MenuScreen(game));
                }
                break;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        valalaImg.dispose();
        backgroundMusic.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int w, int h) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {
        dispose();
    }
}
