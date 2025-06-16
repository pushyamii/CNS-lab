//H MAC 
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class hmac {

	public static void main(String[] args) {
		String message="this is a secret key";
		String secretKey="123456";
		try {
			byte[] hmac=generateHmacSHA256(message,secretKey);
			String hmacBase64=Base64.getEncoder().encodeToString(hmac);
			System.out.println("Generated HMAC: "+hmacBase64);
		}catch(NoSuchAlgorithmException | InvalidKeyException e)
		{
		e.printStackTrace();
		}

	}
	public static byte[] generateHmacSHA256(String message, String secretKey)throws NoSuchAlgorithmException,InvalidKeyException
	{
	
		Mac hmacSha256=Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"HmacSHA256");
		hmacSha256.init(secretKeySpec);
		return hmacSha256.doFinal(message.getBytes());
		
	}
}