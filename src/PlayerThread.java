
public class PlayerThread implements Runnable {

	Player p;

	public PlayerThread(Player _p){
		p=_p;
	}
	
	@Override
	public void run() {

		// TODO Auto-generated method stub
		while (true){
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (p.movement ==1){
				MoveExecutor.executeMove(p, Types.Move.UP,p.playerStep);
			}
			else if (p.movement==-1){
				MoveExecutor.executeMove(p, Types.Move.DOWN,p.playerStep);
			}
			else if (p.movement ==2){
				MoveExecutor.executeMove(p, Types.Move.RIGHT,p.playerStep);
			}
			else if (p.movement ==-2){
				MoveExecutor.executeMove(p, Types.Move.LEFT,p.playerStep);
			}
			
			this.p.sprites.animatePlayer(p);
			
			if (p.powerUp==null){
				MoveExecutor.checkPowerUp(p);
			}
				else{
					p.powerUp.timer--;
					if(p.powerUp.timer<=0){
						System.out.println("expired");
						p.powerUp=null;
					}
				}
				
				if (RandomTest.m.map.checkEndDoor(p)){ //if all enemies cleared
//					gameEnd = true;
//					time.stop();
					System.out.println("Game Over!\n You won!");
				}
			
		}
		
	}

	
}
