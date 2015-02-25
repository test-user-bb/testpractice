
public class CoinChange {



	static int[] coins= {10,20,50,100};
	static int num=100;
	static int[] count= new int[] {0,0,0,0};

//
//	static int[] coins= {50,100};
//	static int num=100;
//	static int[] count= new int[] {0,0};

	
	public static void main(String[] args){
		getChange(num, coins, count, 0);
	}

	public static void getChange(int amountRemaining, int[] coinsList, int[] cointCount, int currentCoin){


		if( amountRemaining == 0 ){
			System.out.println("got the change");
			for(int i=0;i<-coinsList.length;++i)
				System.out.print(""+ coinsList[i] +" * "+ cointCount  + " ");
			return;
		}

		if(currentCoin >= coinsList.length){
			return ;
		}

		System.out.println("Coin :"+coinsList[currentCoin ] + "  amt "+amountRemaining);

		
		getChange(amountRemaining, coinsList, cointCount, currentCoin+1);

		int amtRemaining =amountRemaining-coinsList[currentCoin];

		while (amtRemaining >= 0){
			++cointCount[currentCoin];
			getChange(amtRemaining, coinsList, cointCount, currentCoin+1);
			amtRemaining-=coinsList[currentCoin];
		}

	}
}
