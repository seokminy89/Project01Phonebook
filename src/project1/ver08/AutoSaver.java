package project1.ver08;

public class AutoSaver extends Thread
{
	PhoneBookManager pb1 ;

	public AutoSaver(PhoneBookManager pb1)
	{
		this.pb1 = pb1;
	}
	
	@Override
	public void run()
	{
		try {
			while(true) {
				pb1.saveInfoText();
				sleep(1000);
			}
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
