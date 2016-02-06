package run;



public class RunAllTests {
	
	public static void main(String[] args) 
	{
		String[] testClasses = new String[] 
		{
				"client.clientFacade.ClientFacadeTest",
				"client.serverPoller.ServerPollerTest",
				"client.serverProxy.RealProxyTest",
				"shared.serializerJSON.DeserializerTest",
				"shared.models.DiceTest",
				"shared.models.TradeManagerTest",
				"shared.models.cardClasses.BankTest",
				"shared.models.cardClasses.CardDeckTest",
				"shared.models.cardClasses.DevCardsTest",
				"shared.models.cardClasses.ResourceCardsTest",
				"shared.models.mapClasses.MapTest",
				"shared.models.playerClasses.PlayerTest",
				"shared.models.playerClasses.GamePlayersTest",
				"shared.models.playerClasses.TurnManagerTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}
