
public class BombThread implements Runnable{
	Bomb bomb;
	
	public BombThread(Bomb _bomb){
		bomb = _bomb;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true){
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				if (bomb!=null){
					if (bomb.timer==1){
					//explosion
					MoveExecutor.explodeBomb(bomb,RandomTest.m.p1);
					System.out.println("Explosion");
					bomb.timer--;
					bomb.fireTimer = 20;
					bomb.fireCounter=0;
					bomb.fire = true;
					}
				
					else if (bomb.timer>0){
					bomb.timer--;
						if (bomb.timer %8 ==0){
						bomb.sprites.animateBomb(bomb);
						}
					}
				}
				
				
				
				if (bomb.fire ==true){
					bomb.fireTimer--;
					if (bomb.fireTimer % 8 ==0){
						if (bomb.fireCounter<3){
						Sprite.animateFire(bomb.fireCounter);
						bomb.fireCounter++;
						}
					}
				}
				
				if (bomb.fireTimer <0){
//					System.out.println("running");
					bomb.fire=false;
					for (int r=0;r<15;r++){
			    		for (int c=0;c<15;c++){
			    			if( RandomTest.m.map.map[r][c] == null){
			    				continue;
			    			}
			    			else if ( RandomTest.m.map.map[r][c].isofType(Types.BlockType.FIRE)){
			    				RandomTest.m.map.map[r][c]=null;
			    			}
			    		}
			    	}
					break;
				}
				
		}
	}
	
	
}
