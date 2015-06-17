package BUSINESS;

abstract public class Ticket {
	private double value;
	private Presentation presentation;
	private Chair chair;
	
	public double getValue() {
		return value;
	}
	public void setValue(double d) {
		this.value = d;
	}
	
	public Presentation getPresentation() {
		return presentation;
	}
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}
	
	public Chair getChair() {
		return chair;
	}
	public void setChair(Chair chair) {
		this.chair = chair;
	}
	
	public abstract double getDiscount();
}
