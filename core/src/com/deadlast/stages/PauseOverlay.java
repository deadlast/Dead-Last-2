package com.deadlast.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.deadlast.game.DeadLast;

public class PauseOverlay implements Disposable {

	public Stage stage;
	public ExtendViewport viewport;
	private SpriteBatch batch;
	private Label blurb;
	private Label map;
	public PauseOverlay(DeadLast game) {
		viewport = new ExtendViewport(DeadLast.V_WIDTH, DeadLast.V_HEIGHT);
		batch = new SpriteBatch();
		stage = new Stage(viewport, batch);
		
		Table table = new Table();
		table.setFillParent(true);
		table.center();
		table.pad(15);
		
		// table.setDebug(true);
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

		Label title = new Label("PAUSED", skin);
		table.add(title).align(Align.center).row();
		map = new Label("University", skin);
		table.add(map).align(Align.center).row();
		blurb = new Label("Tip: to win, avoid dying.", skin);
		table.add(blurb).align(Align.center).row();
		
		stage.addActor(table);
	}
	
	public void pickBlurb(int levelNum) {
		switch (levelNum) {
		
		case 0:
			this.blurb.setText("Hack into the University servers to find information on the zombie outbreak");
			break;
		case 1:
			this.blurb.setText("Get a bus to Hes East");
			break;
		case 2:
			this.blurb.setText("Defeat Patient Zero (and maybe grab a pint afterwards)");
			break;
		case 3:
			this.blurb.setText("Head to the secret archives at the back of the library to find out about the EMPR project.");
			break;
		case 4:
			this.blurb.setText("Travel through the facility and take the lift to the roof of Central Hall");
			break;	
		case 5:
			this.blurb.setText("Defeat Dr. G. Reylag to stop the zombie outbreak.");
			break;
		case 6:
			this.blurb.setText("Blerrgh!!! Brains!!! Eat students!!!");
			break;
		case 7:
			this.blurb.setText("Collect the coins and escape the maze!");
			break;
		}
	}
	
	public void setMapName(String mapName) {
		this.map.setText(mapName);
	}
	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
	}
	
}
