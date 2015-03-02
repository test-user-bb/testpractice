//11:45 AM  //12:14 PM  ( 30 mins)
//With DP ://12:37   (25 Mins)

package SelectedProblems;

public class Knapsacks {
	static int[] weigts={2,2,1,1,2};
	static int[] values={7,7,11,90,120};

	static int[][] data;


	public static void main(String[] args){

		System.out.println(fillKnapSack(5,5));
		data = new int[values.length+1][values.length+1];
		System.out.println(fillKnapSackDP(5,5,data));
	}

	//
	public static int fillKnapSack(int capasityRemain, int items){

		//Base condition , we will later check for correctness.
		//If no item is sent then no value is returned.
		// if knapsack has zero capasity so, it can not hold any item so value returned will be zero.
		if(items == 0 || capasityRemain == 0){
			return 0;
		}

		//Now lets select an item, but before using it need to ensure that capasity is enough to occomodate it.
		// Otherwise what to do ?
		// Don't select the item, but just select the item count, neeed to check how to do?
		//items-1 is the index in the array for item "items" 
		if(capasityRemain >=  weigts[items-1]){

			// We make two calls here, one is icnluding the item and other is excluding the item.

			int include = values[items-1] + fillKnapSack(capasityRemain-weigts[items-1], items-1);
			int exclude = fillKnapSack(capasityRemain, items-1); // when exclude, capasity does not change.

			int num = include >= exclude ?  include : exclude; 
			//System.out.println("returning "+num);
			return num;

		}else{
			// If capasity remain is less than the weight of the current item then 
			// knapsack will hold the state corresponding to the previos item.

			return fillKnapSack(capasityRemain, items-1);
		}


	}

	// calling capasityAtState for the knapsack since based on that capasity we will trigger 
	// computation ie if it is 4 then we can use item of weigt 1,1,2 or 2,2 etc.
	public static int fillKnapSackDP(int capasityMax, int items, int[][] data){

		// We loop over the items and the capasity remain and wheneve capasity remain is greater 
		// then weight of the current item, we take action.
		// also for all items =0, 


		for (int item=0; item<=items;++item){
			for(int capasityAtState=0;capasityAtState<=capasityMax;++capasityAtState){
				//Base init condition.
				if( item==0 || capasityAtState ==0 ){
					data[item][capasityAtState]=0;

				}else{

					if(capasityAtState >= weigts[item-1]){
//						System.out.println(item);
//						try {
//							//Thread.sleep(10);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						int include = values[item-1]  + data[item-1][capasityAtState-weigts[item-1]];
						int exclude =  data[item-1][capasityAtState];
						int num = include >= exclude ?  include : exclude;
						data[item][capasityAtState]= num;  //IMP
					}else{
						data[item][capasityAtState]=data[item-1][capasityAtState];;
					}
				}

			}
		}

		return data[values.length][values.length];
	}
}
