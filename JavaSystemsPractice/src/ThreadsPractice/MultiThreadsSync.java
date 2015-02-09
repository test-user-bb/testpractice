package ThreadsPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//10:30 AM
public class MultiThreadsSync implements Runnable{

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	private Random randGen = new Random();
	

	Object o1 = new Object();
	Object o2 = new Object();

	public  void  updateList1(){
		for (int i = 0 ; i <1000;++i){

			synchronized (o1) {
				list1.add(randGen.nextInt(1000));

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}

		}

	}

	public  void updateList2(){
		for (int i = 0 ; i <1000;++i){
			synchronized (o2) {
				list2.add(randGen.nextInt(1000));
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void run() {
		updateList1();
		updateList2();

	}

	long getSizeLits1(){
		return list1.size();
	}

	long getSizeLits2(){
		return list2.size();
	}


}

//11:02 AM
