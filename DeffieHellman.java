// DEFFIE HELLMAN KEY EXCHANGE
import java.util.Scanner;

public class DiffieHellman {

	public static void main(String[] args)
	{
		long n,g,x,a,y,b,ka,kb;
		Scanner sc=new Scanner(System.in);
		System.out.println("both users should be agreed upon the keys g and n");
		System.out.println("Enter the value for Parameter g");
		g=sc.nextLong();
		System.out.println("Enter the value for parameter n");
		n=sc.nextLong();
		System.out.println(" Enter value for private key a selected by user 1: ");
		a=sc.nextLong();
		System.out.println(" Enter value for private key b selected by user 2: ");
		b=sc.nextLong();
		x=calculatePower(g,a,n);
		y=calculatePower(g,b,n);
		ka=calculatePower(y,a,n);
		kb=calculatePower(x,b,n);
		System.out.println("Secret key for user 1 is"+ka);
		System.out.println("Secret key for user 2 is"+kb);
	}
	
	private static long calculatePower(long g,long y,long N)
	{
		long result=0;
		if(y==1)
		{
			return g%N;
		}
		
		else {
			result=((long)Math.pow(g, y))%N;
			return result;
		}
	}
}