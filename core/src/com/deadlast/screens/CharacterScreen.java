package com.deadlast.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.deadlast.entities.PlayerType;
import com.deadlast.game.DeadLast;
import com.deadlast.game.GameManager;

/**
 * Screen responsible for presenting character selection choice and transferring to the game screen
 * @author Xzytl
 *
 */
public class CharacterScreen extends DefaultScreen {
	
	private Stage stage;
	private Texture background;	
	private SpriteBatch batch;

	public CharacterScreen(DeadLast game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		background = new Texture("ui/CharacterSelectionScreen.png");
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setFillParent(true);
		table.center();
		table.pad(15);
		table.setHeight(Gdx.graphics.getHeight() - 30);
		stage.addActor(table);

		//table.setDebug(true);
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		Label charTitle1 = new Label("Average Student", skin);
		Label charTitle2 = new Label("Boxer", skin);
		table.add(charTitle1).expandX(); //align(Align.left).width(Value.percentWidth(.45F, table));
		table.add(charTitle2).expandX(); //align(Align.left).width(Value.percentWidth(.45F, table));
		table.row();
		
		Image AvStudImage = new Image(new Texture("entities/player.png"));
		table.add(AvStudImage);
		Image BoxerImage = new Image(new Texture("entities/player.png"));
		table.add(BoxerImage);
		table.row();
		
		Label char1Label = new Label("This lazy student has average stats for a character.", skin);
		char1Label.setWrap(true);
		table.add(char1Label).align(Align.left).width(Value.percentWidth(.45F, table));
		Label char2Label = new Label("The boxer is tough and strong, but cannot run as fast and is less stealthy.", skin);
		char2Label.setWrap(true);
		table.add(char2Label).align(Align.left).width(Value.percentWidth(.45F, table));
		table.row();
		
		TextButton char1Button = new TextButton("Select", skin);
		char1Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selectCharacter(PlayerType.STANDARD);
			}
		});
		
		table.add(char1Button);
		
		TextButton char2Button = new TextButton("Select", skin);
		char2Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selectCharacter(PlayerType.TANK);
			}
		});
		
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.changeScreen(DeadLast.MENU);
			}
		});
		
		table.add(char2Button);

		table.row();

		Label charTitle3 = new Label("\n Stealthy", skin);
		Label charTitle4 = new Label("\n "
				+ "Runner", skin);
		table.add(charTitle3).expandX(); //align(Align.left).width(Value.percentWidth(.45F, table));
		table.add(charTitle4).expandX(); //align(Align.left).width(Value.percentWidth(.45F, table));
		table.row();
		
		Image StealthyImage = new Image(new Texture("entities/zombie.png"));
		table.add(StealthyImage);
		Image RunnerImage = new Image(new Texture("entities/zombie.png"));
		table.add(RunnerImage);
		table.row();
		
		Label char3Label = new Label("This student is an expert at sneaking past lecturers when late, and the same with zombies.", skin);
		char3Label.setWrap(true);
		table.add(char3Label).align(Align.left).width(Value.percentWidth(.45F, table));
		Label char4Label = new Label("The runner has been running away from his problems all his life, so he's fast.", skin);
		char4Label.setWrap(true);
		table.add(char4Label).align(Align.left).width(Value.percentWidth(.45F, table));
		table.row();

		TextButton char3Button = new TextButton("Select", skin);
		char3Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selectCharacter(PlayerType.STEALTH);
			}
		});

		table.add(char3Button);

		TextButton char4Button = new TextButton("Select", skin);
		char4Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selectCharacter(PlayerType.RUNNER);
			}
		});

		table.add(char4Button);
		
		stage.addActor(table);
		stage.addActor(backButton);
	}
	
	private void selectCharacter(PlayerType type) {
		GameManager.getInstance(game).setPlayerType(type);
		GameManager.getInstance(game).setGameRunning(true);
		game.changeScreen(DeadLast.GAME);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		background.dispose();
		batch.dispose();
	}

}
