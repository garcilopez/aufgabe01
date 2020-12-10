package httpserver;


public class Counters {
	private int rede2013;
	private int redeSicherheit;
	private int redeWorter;
	
	
	
	public Counters(int rede2013, int redeSicherheit, int redeWorter) {
		super();
		this.rede2013 = rede2013;
		this.redeSicherheit = redeSicherheit;
		this.redeWorter = redeWorter;
	}
	
	public Counters addRede2013(int value) {
		this.rede2013=this.rede2013+value;
		return this;
	}
	
	public Counters addRedeSicherheit(int value) {
		this.redeSicherheit=this.redeSicherheit+value;
		return this;
	}
	
	public Counters addRedeWorter(int value) {
		this.redeWorter=this.redeWorter+value;
		return this;
	}
	
	
	
	public int getRede2013() {
		return rede2013;
	}
	public void setRede2013(int rede2013) {
		this.rede2013 = rede2013;
	}
	public int getRedeSicherheit() {
		return redeSicherheit;
	}
	public void setRedeSicherheit(int redeSicherheit) {
		this.redeSicherheit = redeSicherheit;
	}
	public int getRedeWorter() {
		return redeWorter;
	}
	public void setRedeWorter(int redeWorter) {
		this.redeWorter = redeWorter;
	}
	
	


	
}
