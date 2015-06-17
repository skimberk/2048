package activity2;

public class Ease {
	private int duration;
	private int startTime;
	
	public Ease(int duration) {
		this.duration = duration;
		this.startTime = (int) System.currentTimeMillis();
	}
	
	public double getProgress() {
		int currentTime = (int) System.currentTimeMillis();
		int difference = currentTime - startTime;
		
		if(difference > duration) {
			return 1.0;
		}
		else {
			return easeInOutQuad(1.0 * difference / duration);
		}
	}
	
	public boolean isDone() {
		return (int) System.currentTimeMillis() - startTime > duration;
	}
	
	public static double fromTo(double from, double to, double progress) {
		return from + (to - from) * progress;
	}
	
	private double easeInOutQuad(double in) {
	    if ((in/=0.5) < 1) return 0.5*Math.pow(in,2);
	    return -0.5 * ((in-=2)*in - 2);
	}
	
	@SuppressWarnings("unused")
	private double easeInOutQuint(double in) {
	    if ((in /= 0.5) < 1) return 0.5 * Math.pow(in, 5);
	    return 0.5 * (Math.pow((in - 2), 5) + 2);
	}
	
	@SuppressWarnings("unused")
	private double easeInOutSine(double in) {
		return (-0.5 * (Math.cos(Math.PI * in) - 1));
	}
}
