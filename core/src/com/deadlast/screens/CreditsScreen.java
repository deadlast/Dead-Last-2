package com.deadlast.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.deadlast.game.DeadLast;

public class CreditsScreen extends DefaultScreen {
	
	private Stage stage;

	public CreditsScreen(DeadLast game) {
		super(game);
		stage = new Stage(new ScreenViewport());
	}
	
	@Override
	public void show() {
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		table.center();
		table.pad(15);
		
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		table.setSkin(skin);
		
		table.add(new Label("Project Manager", skin)).row();
		table.add(new Label("Michael Elliot", skin)).row();
		table.row();
		table.add("").expandX().fillX().row();
		table.add(new Label("Lead Developer", skin)).row();
		table.add(new Label("Matt Aalen", skin)).row();
		table.row();
		table.add("").expandX().fillX().row();
		table.add(new Label("Developers", skin)).row();
		table.add(new Label("Liam Devine", skin)).row();
		table.add(new Label("Rhodri Gale", skin)).row();
		table.row();
		table.add("").expandX().fillX().row();
		table.add(new Label("Art and Design", skin)).row();
		table.add(new Label("Christina Ho", skin)).row();
		table.row();
		table.add("").expandX().fillX().row();
		table.add(new Label("Map Design", skin)).row();
		table.add(new Label("Liam Devine", skin)).row();
		table.add(new Label("Matt Aalen", skin)).row();
		
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.changeScreen(DeadLast.MENU);
			}
		});
		
		stage.addActor(table);
		stage.addActor(backButton);
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
		// TODO Auto-generated method stub
		
	}

}
