package ThreadsPractice;
//8:12
public class Singleton {
	public  static int ID=1000;
	private Singleton(){
		++ID;
	};
	private volatile static Singleton sObject=null;
	

	public static Singleton getInstance(){

		if (sObject == null){
			synchronized (Singleton.class) {
				if( sObject == null){
					return (sObject = new Singleton());
				}

			}
		}

		return sObject;
	}
	
}

