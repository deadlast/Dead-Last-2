package com.deadlast.screens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.deadlast.game.DeadLast;
import com.deadlast.game.GameManager;

public class EndScreen extends DefaultScreen {

	private Stage stage;

	private boolean won;
	private int finalScore;
	public EndScreen(DeadLast game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		won = GameManager.getInstance(game).getWinLevel() == 1 ? true : false;
		this.finalScore = GameManager.getInstance(game).getScore();
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true);
		table.center();
		table.pad(15);
		
		// table.setDebug(true);
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

		String titleText;
		if (won) {
			titleText = "You won!";
		} else {
			titleText = "You lost.";
		}
		Label title = new Label(titleText, skin);
		table.add(title).align(Align.center).row();

		String blurbText;
		if (won) {
			blurbText = "Congratulations Agent! Dr G. Reylag has been defeated and you can begin distributing the cure!";
		} else {
			blurbText = "You have been turned, and the zombie threat is rapidly expanding outside the University.";
		}
		Label blurb = new Label(blurbText, skin);
		table.add(blurb).align(Align.center).row();


		table.add(new Label("Your end score: " + this.finalScore,skin)).align(Align.center).row();

		
		TextButton returnButton = new TextButton("Menu", skin);
		returnButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameManager.getInstance(game).resetScore();
				table.reset();
				game.changeScreen(DeadLast.MENU);
			}
		});
		
		table.add(returnButton);

		stage.addActor(table);

		GameManager.getInstance(game).clearLevel();
		
		int score = GameManager.getInstance(game).getScore();
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
		FileHandle file = Gdx.files.local("data/scores.csv");
		if (file.exists()) {
			file.writeString(score + "," + dateTime , true);
		} else {
			System.out.println("Warning: could not write to score file - file does not exist.");
		}
//		GameManager.getInstance(game).dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act();
		stage.draw();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
