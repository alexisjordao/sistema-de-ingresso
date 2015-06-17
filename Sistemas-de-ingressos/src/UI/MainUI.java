	
package UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import BUSINESS.AreaManager;
import BUSINESS.Chair;
import BUSINESS.Presentation;
import BUSINESS.PresentationManager;
import BUSINESS.TheaterAreaManager;
import BUSINESS.Ticket;
import BUSINESS.TicketManager;

public class MainUI {
	final static String FILENAMEPRESENTATION = "PresentationTable\\Presentations.txt";
	final static String FILENAMEAREA = "Chairs\\Area#";
	
	private static Scanner scn = new Scanner(System.in);
	private PresentationManager tpmanager = new PresentationManager();
	private static AreaManager tamanager = new TheaterAreaManager();
	private TicketManager ttmanager = new TicketManager();
	
	private static MainUI mui = new MainUI();
	
	private static Presentation currPresentation = null;
	private static Chair currChair = null;
	
	public static void main(String[] args)
	{
        System.out.println("Lista de Apresentações disponíveis:");
        
        mui.displayPresentationTable();
        
        MainUI.clearConsole();
        
        System.out.println("\nEscolha a apresentação e a hora: (Use a ordem em que as apresentações e as horas aparecem"
        		+ " começando do numero 1.)");
        
        int pindex = 0;
        int tindex = 0;
        
        while(currPresentation == null){
        	pindex = scn.nextInt();
        	tindex = scn.nextInt();
        
        	mui.choosePresentation(pindex,tindex);
        }
        
        MainUI.clearConsole();
        
        mui.displayArea(pindex,tindex);
        
        System.out.println("\nEscolha a linha e a coluna, respectivamente, da cadeira que você quer:");
        
        while(currChair == null){
        	String row = scn.next();
        	String column = scn.next();
        
        	currChair = tamanager.chooseChair(row, column);
        	
        	if(currChair == null){
        		System.out.println("Posição inválida. Tente Novamente:");
        	}
        }
        
        System.out.println("Escolha um método de desconto: 1 - estudante, 2 - sócio, Outros Nºs- nenhum");
        
        int tipo = scn.nextInt();
        
        mui.buyTicket(currPresentation, currChair, tipo);
	}
	
	private  void displayPresentationTable()
	{
		List<Presentation> table = tpmanager.getPresentationTable(FILENAMEPRESENTATION);
		
		for(int i = 0; i < table.size(); i++)
		{
			System.out.println(table.get(i).getDescription() + " -- Censura " + table.get(i).getAgeRate() + " -- R$" + table.get(i).getValue());
			for(int j = 0; j < table.get(i).getTimeList().size(); j++)
			{
				if(table.get(i).getTimeList().get(j).getDate() != null)
				System.out.println(table.get(i).getTimeList().get(j).getDate() + " - " + table.get(i).getTimeList().get(j).getHour());
				else
				{
					if(j == table.get(i).getTimeList().size() - 1)
						System.out.println(table.get(i).getTimeList().get(j).getHour());
					else
						System.out.print(table.get(i).getTimeList().get(j).getHour() + " - ");
				}
			}
			System.out.println("");
		}
	}
	
	private void choosePresentation(int pindex,int tindex)
	{
		currPresentation = tpmanager.choosePresentation(pindex - 1, tindex - 1, FILENAMEPRESENTATION);
	}
	
	private void displayArea(int pindex, int tindex)
	{
		HashMap<String, List<Chair>> areaMapping = tamanager.getArea(FILENAMEAREA + pindex + "." + tindex + ".txt");
		
		List<String> rowList = tamanager.getRowList();
		List<Chair> area = new ArrayList<>();
			
		for(int i = 0; i < rowList.size(); i++)
		{
			area = areaMapping.get(rowList.get(i));
			System.out.print(rowList.get(i) + "  ");
			for(int j = 0; j < area.size(); j++)
			{
				if(area.get(j).getType().equals("VESPACE"))
					System.out.print(" ");
				else if(area.get(j).getType().equals("HESPACE"))
					System.out.println("");
				else{
					if(!area.get(j).isSold())
					System.out.print("0" + area.get(j).getType());
					else
					System.out.print("1" + area.get(j).getType());
				}
			}
			System.out.println("");
			
		}
	}
	
	private void buyTicket(Presentation p, Chair c, int ticketType)
	{
		Ticket ticket = ttmanager.makeTicket(p,c,ticketType);
		
		System.out.println("O valor do ticket é: " + ticket.getValue() + "\nEntre com "
				+ "quanto você vai pagar:");
		
		while(true){
			int pagamento = scn.nextInt();
		
			if(pagamento > ticket.getValue()){
				System.out.println("O seu troco é de: " + (pagamento - ticket.getValue()));
				break;
			}
			
			System.out.println("O valor entrado deve ser maior que o valor do ticket:");
		}
			
		mui.printTicket(ticket);
		
	}
	
	public void printTicket(Ticket t)
	{
		System.out.println("          TICKET\n     " + t.getPresentation().getDescription() + "\n ========================= \n    censura " +
				t.getPresentation().getAgeRate() + " --- " + t.getChair().getRow() + t.getChair().getColumn()
				+ "\n ========================= \n     " + t.getPresentation().getTimeList().get(0).getDate() + " --- " + t.getPresentation().getTimeList().get(0).getHour());
	}
	
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
	 
}
