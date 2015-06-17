package BUSINESS;

import DATA.TheaterChairStore;

public class TheaterTicketValueCalculator extends TicketValueCalculator {

	@Override
	public double calcTicketValue(Presentation p, Chair c, Ticket t) {
		return p.getValue() * t.getDiscount() * TheaterChairStore.getInstance().getTypeValue(c);
	}

}
