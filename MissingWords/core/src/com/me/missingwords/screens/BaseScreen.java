package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.me.missingwords.MissingWords;

/**
 * 
 * Clase abstracta que recoge la informaci�n en com�n que van a tener todas las pantallas 
 * descendientes.
 *
 */

public abstract class BaseScreen implements Screen {
	protected MissingWords missingWords;
	protected SpriteBatch myBatch;
	
	/* 
	 * Escenario del juego. El escenario es donde se desarrollar� todo. Tiene una estructura de
	 * �rbol donde se van a�adiendo sus hijos, por lo que el orden es importante para saber 
	 * cuales se mostrar�n antes y cuales despu�s, es decir, para que no se solapen.
	 * Todos los objetos que heredan de Actor implementan dos m�todos: draw() y act(). 
	 * Lo que es lo mismo: como se dibujaran y como actuar�n en el escenario. Luego el escenario
	 * llama a sus m�todos stage.act() y stage.draw() que llamar�n recursivamente a todos los
	 * m�todos act() y draw() de sus hijos para a�adirlos al escenario. 
	 */
	protected Stage stage;
	
	/* 
	 * Un escenario necesita un Viewport. Un viewport es lo que vemos en la pantalla, ya que si
	 * dibujamos fuera de los l�mites de la pantalla, no significa que no se est�
	 * dibujando, sino que no lo vemos porque est� fuera del Viewport.
	 */
	private ScalingViewport viewport;
	
	public BaseScreen(MissingWords missingWords) {
		this.missingWords = missingWords; // Conectamos el juego con la pantalla base
		this.myBatch = missingWords.getSB();
		
		/* 
		 * Con Scaling.stretch ajustamos la aplicaci�n a la pantalla, estrechando
		 * si es necesario.
		 */
		viewport = new ScalingViewport(Scaling.stretch, 
				MissingWords.VIEWPORT_WIDTH , MissingWords.VIEWPORT_HEIGHT);
		
		stage = new Stage(viewport, myBatch);
	}
	
	/* 
	 * El m�todo render() es el m�todo encargado de dibujar los elementos en la pantalla.
	 * Delta es el n�mero de segundos desde que se ejectu� render() anteriormente.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/* 
	 * El m�todo resize() es el m�todo que se llama cuando hay que ajustar la pantalla al 
	 * dispositivo, es decir, cuando hay que cambiar la resoluci�n.
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true); // Actualizamos el viewport del stage
	}
	
	/* show(): se ejecuta cuando se muestra la pantalla */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage); // Indicamos que la entrada la procesar� el stage
		Gdx.input.setCatchBackKey(true); // No usar el bot�n atr�s de Android
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public Stage getStage() {
		return stage;
	}
}
