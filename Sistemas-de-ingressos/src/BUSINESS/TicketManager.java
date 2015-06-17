package BUSINESS;

public class TicketManager {
	public Ticket makeTicket(Presentation p, Chair c, int ticketType)
	{
		TicketValueCalculator tvcalc = new TheaterTicketValueCalculator();
		Ticket ticket = ticketFactory(ticketType);
		
		ticket.setValue(tvcalc.calcTicketValue(p,c,ticket));
		ticket.setPresentation(p);
		ticket.setChair(c);
		
		return ticket;
	}
	
	public Ticket ticketFactory(int index)
	{
		if(index == 1)
			return new TicketStudent();
		else if(index == 2)
			return new TicketMember();
		else
			return new TicketRegular();
	}
}
