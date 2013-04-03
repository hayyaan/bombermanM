
public class EnemyThread implements Runnable{
	Enemy e;
	
	public EnemyThread(Enemy _e){
		e=_e;
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
			if (e!=null){
			
			e.moveEnemy();
			e.killPlayer(RandomTest.m.p1);
			e.killPlayer(RandomTest.m.p2);
			}
			else{
				break;
			}
		}
		
	}
	
	
}
