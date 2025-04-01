package com.juancrfig.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.juancrfig.Marterio;
public class MenuScreen implements Screen {
    private final Marterio game;
    private Texture landscape;
    private Music backgroundMusic;
    private SpriteBatch batch;
    private float landscapeX, landscapeY;
    private float alpha = 0f;
//    private Stage stage;
//    private Skin skin;
    public MenuScreen(Marterio game) {
        this.game = game;
        loadResources();
        calculatePositions();
        // createUI();
    }
    private void loadResources() {
        batch = new SpriteBatch();
        landscape = new Texture("images/valala_productions.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/menu_music.wav"));
        backgroundMusic.setLooping(true);
    }
    private void calculatePositions() {
        landscapeX = (Gdx.graphics.getWidth() - landscape.getWidth()) / 2f;
        landscapeY = (Gdx.graphics.getHeight() - landscape.getHeight()) / 2f;
    }
    //private void createUI() {
        //stage = new Stage(new ScreenViewport());
        //skin = new Skin(Gdx.files.internal("skin/uiskin.json")); // Requires skin file
        //Table table = new Table();
        //table.setFillParent(true);
        //TextButton playButton = new TextButton("Start Game", skin);
        //playButton.addListener(e -> {
           // if(e.isHandled()) {
                // Transition to gameplay screen
                // game.setScreen(new GameScreen(game));
           // }
          //  return true;
        // });
        //table.add(playButton).pad(20);
        //table.row();
        //stage.addActor(table);
        // Gdx.input.setInputProcessor(stage);
    //}
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setColor(1, 1, 1, 1);
        batch.draw(landscape, landscapeX, landscapeY);
        batch.end();
        // stage.act(delta);
        //stage.draw();
    }
    @Override
    public void dispose() {
        landscape.dispose();
        batch.dispose();
        backgroundMusic.dispose();
        // skin.dispose();
    }
    @Override
    public void resize(int width, int height) {
    }
    // Unused Screen methods
    @Override public void show() {
        backgroundMusic.play();
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {
        dispose();
    }
}
