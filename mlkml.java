
public class mlkml {
	
	public static void main(String[] args) {
		double health = 100;
		int count = 0;
		int maxnum = 2000;
		double attack = 15;
		int defence = 3;
		int strength = 15;
		double[] average = new double[maxnum];
		for (int i = 0; i < maxnum; i++) { 
			double hit = Math.random() * (attack / defence);
			hit += hit * ((strength + 60) / 100);
			//System.out.println(hit);
			average[i] = hit;
		}
		double total = 0;
		for (double a : average) {
			total += a;
		}
		System.out.println(total / average.length);
	}
	
}

		
		
		
		
		
		
		
		
		
		
		//		//double strMulti = (1 / (15 - strength)); 
//		for (int i = 0; i < maxnum; i++) {
//			while (health > 0) {
//				//double percentage = Math.random() + strMulti; 
//				
//				if (accuracy == 0) {
//					accuracy = 1;
//				}
//				if (accuracy < 0) {
//					accuracy = - (1 / accuracy);
//				}
//				
//				double maxHit = 1 + (accuracy * ((strength + 620) / 640));
//				double hit = Math.random() * maxHit; 
//				//double hit = (health * (percentage * accuracy));
//				if (hit > .5) {
//					hit = (int) (hit + 1);
//				} else {
//					hit = 0;
//				}
//				health = (health - hit);
//	//			if (health < 1) {
//	//				health = 0;
//	//			}
//				count++;
////				System.out.println(count);
////				System.out.println("hit:" + hit);
////				System.out.println("health:" + health);
//			}
//			health = 7;
//			
//			average[i] = count;
//			count = 0;
//			//System.out.println(i);
//		}
//		
//		double total = 0;
//		for (int poo : average) {
//			total += poo;
//			
//		}
//		System.out.println(total / maxnum);
//			
//			
//
//	}

