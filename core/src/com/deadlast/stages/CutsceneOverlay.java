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

public class CutsceneOverlay implements Disposable {

	public Stage stage;
	public ExtendViewport viewport;
	private SpriteBatch batch;
	private Label map;

	private Label line1;
	private Label line2;
	private Label line3;
	private Label line4;
	private Label line5;
	private Label line6;
	private Skin skin;
	private Table table;
	public CutsceneOverlay(DeadLast game,int levelNum) {
		viewport = new ExtendViewport(DeadLast.V_WIDTH, DeadLast.V_HEIGHT);
		batch = new SpriteBatch();
		stage = new Stage(viewport, batch);
		
		table = new Table();
		table.setFillParent(true);
		table.center();
		table.pad(15);
		
		// table.setDebug(true);
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		
		createText(levelNum);

		stage.addActor(table);
	}
	
	private void createText(int levelNum) {
		
		switch (levelNum) {
		
		case 0:
			map = new Label("Chapter 1: Computer Science", skin);
			line1 = new Label("A zombie outbreak has started at the University of York. Hundreds of students and staff are infected.",skin);
			line2 = new Label("The University has activated'The Society for the Elimination of Pestilent Revenants': SEPR for short.",skin);
			line3 = new Label("As SEPR's finest agent, your first mission is to hack into the Computer Science servers and find out what caused the outbreak.",skin);
			line4 = new Label("Initial scans show the area surrounding the department is teeming with activity, so be careful.",skin);
			line5 = new Label("Good luck out there, agent.",skin);
			break;
		case 1:
			map = new Label("Chapter 2: Hes East", skin);
			line1 = new Label("After scraping the University servers for data on the outbreak, you've discovered information about the origin of the virus.",skin);
			line2 = new Label("Everything seems to point to one Dr. G. Reylag, a suspicous member of the the Biology Department.",skin);
			line3 = new Label("His emails indicate he was scheduled to conduct an experiment the day the outbreak occurred.",skin);
			line4 = new Label("They also imply that he is in possession of a cure for the virus.",skin);
			line5 = new Label("To get to West Campus, you should catch a bus from the edge of Heslington East.",skin);
			break;
		case 2:
			map = new Label("Chapter 3: D Bar", skin);
			line1 = new Label("The bus arrives, and you inform the undead bus driver that you're a student. He waves you on.",skin);
			line2 = new Label("You sit looking out the window for a while, but the brief moment of respite is shattered as a huge zombie charges the bus.",skin);
			line3 = new Label("He rips the side off the bus, frantically picks you up and flings you deep into West Campus.",skin);
			line4 = new Label("You crash through the roof of a building, landing hard on the ground. ",skin);
			line5 = new Label("You stand up, bleary eyed, head throbbing, covered in beer. You recognise the feeling immediately: this must be DBar.",skin);
			break;
		case 3:
			map = new Label("Chapter 4; Library", skin);
			line1 = new Label("In his dying moments, you're surpised to hear the massive zombie speak:",skin);
			line2 = new Label("'ARRCHIFES, LIBRURY, EMPUH, GUUSE'",skin);
			line3 = new Label("He draws his last breath, and his body goes limp.",skin);
			line4 = new Label("Intrigued, you decide to take a detour to the Library.",skin);
			line5 = new Label("After sneaking through West Campus, you arrive at the library entrance.", skin);
			break;
		case 4:
			map = new Label("Chapter 5: Under Lake", skin);
			line1 = new Label("You wonder why this part of the archives is so well hidden, but it doesnt take long to find out.",skin);
			line2 = new Label("Browsing the files you come across what appears to be communications between rogue elements of the Biology Department.",skin);
			line3 = new Label("They have been working on EMPR, or 'Experiments to Make Poultry Robust'. The experiments hardly seem ethical.",skin);
			line4 = new Label("At the end of the files is what appears to be a note left specifically for you:",skin);
			line5 = new Label("'CENTRAL HALL VIA GREGS PLACE IF YOU WANT TRUTH, HONK HONK' - Reylag",skin);
			line6 = new Label("You sneak your way to Greg's Place and are surprised when it begins to sink into the ground, stopping at the entrance to a secret underwater facility.", skin);
			break;	
		case 5:
			map = new Label("Chapter 6: Central Hall", skin);
			line1 = new Label("The lift brings you up to the roof of Central Hall, and you are brought face to face with an abomination.",skin);
			line2 = new Label("The massive creature introduces himself as Dr. G Reylag, leader of the EMPR project.",skin);
			line3 = new Label("'GOOSE FOUNDED THIS UNIVERSITY, HUMANS PUSH GOOSE OUT. EMPR MAKE GOOSE STRONG. EMPR MAKE HUMANS DEAD. HONK'",skin);
			line4 = new Label("He loudly continues a tirdade of nasty comments about humans.",skin);
			line5 = new Label("While he laments, you notice a box behind him labelled 'CURE, HONK'. It seems the only way to cure the virus is to take down Reylag.",skin);
			break;
		}
		table.add(map).align(Align.center).row();
		table.add(line1).align(Align.left).row();
		table.add(line2).align(Align.left).row();
		table.add(line3).align(Align.left).row();
		table.add(line4).align(Align.left).row();
		table.add(line5).align(Align.left).row();
		table.add(line6).align(Align.left).row();
		
		Label space = new Label("Press Space to continue", skin);
		table.add(space).align(Align.center).row();
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
