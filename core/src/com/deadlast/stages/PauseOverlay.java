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

		Label blurb = new Label("Tip: to win, avoid dying.", skin);
		table.add(blurb).align(Align.center).row();
		
		stage.addActor(table);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
	}
	
}
