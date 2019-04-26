package com.deadlast.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.deadlast.game.DeadLast;
import com.deadlast.game.GameManager;

public class Hud implements Disposable {

	public Stage stage;
	public ExtendViewport viewport;
	public Table topView;
	public Table bottomView;
	public Table centreView;
	
	private SpriteBatch batch;
	
	Label timeValLabel;
	Label scoreValLabel;
	Label levelLabel;
	Label coinValLabel;
	Label healthValLabel;

	public Hud(DeadLast game) {
		viewport = new ExtendViewport(DeadLast.V_WIDTH, DeadLast.V_HEIGHT);
		batch = new SpriteBatch();
		stage = new Stage(viewport, batch);
		
		topView = new Table();
		topView.top();
		topView.setFillParent(true);
		
		// TODO: Replace with an asset manager
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		//Label.LabelStyle labelStyle = new Label.LabelStyle(Color.WHITE);
		timeValLabel = new Label(String.format("%03d", 0), skin);
		scoreValLabel = new Label(String.format("%04d", 0), skin);
		levelLabel = new Label("Ron Cooke Hub", skin);
		Label coinLabel;


		if(GameManager.getInstance(game).isMinigameActive()){
			coinLabel = new Label("Coins Collected: ", skin);
			coinValLabel = new Label(String.format("%02d",0),skin);
			topView.add(coinLabel).expandX().padTop(10);
		}
		topView.row();
		topView.add(timeValLabel).expandX();
		topView.add(levelLabel).expandX();
		topView.add(scoreValLabel).expandX();

		if(GameManager.getInstance(game).isMinigameActive()){ topView.add(coinValLabel).expandX(); }

		stage.addActor(topView);
		
		bottomView = new Table();
		bottomView.setFillParent(true);
		bottomView.bottom();
		
		Label healthLabel = new Label("Health: ", skin);
		healthValLabel = new Label("000", skin);
		
		bottomView.add(healthLabel).padBottom(10);
		bottomView.add(healthValLabel).padBottom(10);
		
		stage.addActor(bottomView);

		centreView = new Table();
		centreView.center();
		centreView.setFillParent(true);


		stage.addActor(centreView);
	}
	
	public void setTime(int time) {
		timeValLabel.setText(String.format("%03d", time));
	}
	
	public void setScore(int score) {
		scoreValLabel.setText(String.format("%06d", score));
	}
	
	public void setHealth(int health) {
		healthValLabel.setText(Integer.toString(health));
	}
	
	public void setLevelName(String name) {
		levelLabel.setText(name);
	}


	public void setCoinsCollected(int coinsCollected, DeadLast game){
		if(GameManager.getInstance(game).isMinigameActive()) {
			coinValLabel.setText(String.format("%02d", coinsCollected));
		}
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

	
	
}
