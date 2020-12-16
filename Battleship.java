
public class Battleship {
	/** Main class in theproject which handles the game. **/
	public static void main(String args[]) {
		BattleshipModel model = new BattleshipModel();
        BattleshipView view = new BattleshipView();
		BattleshipController controller = new BattleshipController(model, view);
		
		controller.CreateField(model.home, model.enemy);		

	}

}
