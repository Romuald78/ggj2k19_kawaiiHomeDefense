package fr.rphstudio.ggj2k19.game;

import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ggj2k19.factory.EnemyFactory;
import fr.rphstudio.ggj2k19.launcher.Common;
import org.newdawn.slick.geom.Vector2f;

import java.util.List;

public class WaveGeneration {
    private EnemyFactory enemyFactory;
    private Vector2f centerPosition;
    private float maxSpawnRadius;
    private float minSpawnRadius;
    private final List<Entity> targets;
    private RenderFont waveIndicator;
    private RenderFont renderFontNextWave;
    private float time = 0;
    private float nextSpawnTime = 0;
    private int waveCounter = 0;

    public static int lastScoreWave = 0;

    public WaveGeneration(
            EnemyFactory enemyFactory,
            Vector2f centerPosition,
            float maxSpawnRadius,
            float minSpawnRadius,
            List<Entity> targets, RenderFont waveNumber, RenderFont renderFontNextWave) {
        this.enemyFactory = enemyFactory;
        this.centerPosition = centerPosition;
        this.maxSpawnRadius = maxSpawnRadius;
        this.minSpawnRadius = minSpawnRadius;
        this.targets = targets;
        this.waveIndicator = waveNumber;
        this.renderFontNextWave = renderFontNextWave;
    }

    void createNewRandom(boolean boss) {
        //sens anti trigo!!! et 0 a droite
        double minAngleRad = Math.PI;
        double maxAngleRad = (Math.PI * 2) - (Math.PI / 2);
        double randomOnCircleRad = (Math.random() * (maxAngleRad - minAngleRad)) + minAngleRad;
        float distFromCenter = (float) ((Math.random() * (maxSpawnRadius - minSpawnRadius)) + minSpawnRadius);
        distFromCenter = maxSpawnRadius;
        float x = (float) Math.cos(randomOnCircleRad) * distFromCenter;
        float y = (float) Math.sin(randomOnCircleRad) * distFromCenter;
        enemyFactory.create(new Vector2f(x + centerPosition.x, y + centerPosition.y), targets,boss);
    }

    public void update(float delta) {
        time += delta;
        renderFontNextWave.setMessage("NEXT WAVE: "+((int)((nextSpawnTime-time)/1000))+"s");
        if (time > nextSpawnTime) {
            waveCounter++;
            this.lastScoreWave = waveCounter;
            waveIndicator.setMessage("WAVE " + waveCounter);
            System.out.println("next wave :" + waveCounter);
            //wave of ten
            int nbToWpawn = (int) ((int) (Common.WAVE_NB_ENEMIES * (targets.size() - 1)) * Math.pow(Common.WAVE_INCREASE, waveCounter));
            for (int i = 0; i < nbToWpawn; i++) {
                createNewRandom(false);
            }
            //next wave time

            if(waveCounter%5==0){
                createNewRandom(true);
                nextSpawnTime = time + Common.WAVE_INTERVAL_PAUSE_MS;
            }else{
                nextSpawnTime = time + Common.WAVE_INTERVAL_MS;
            }
        }
    }

}
