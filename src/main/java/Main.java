import com.codecool.tamagotchi.dao.BattleDao;
import com.codecool.tamagotchi.dao.BattleDaoImpl;
import com.codecool.tamagotchi.model.tamagotchi.Pet;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Type;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(final String[] args) throws Exception {
        Pet playerOne = new Pet();
        playerOne.setName("Player 1");
        playerOne.setHealth(100);
        playerOne.setAttack(60);
        playerOne.setDefence(60);
        playerOne.setSpeed(80);
        playerOne.setType(Type.EARTH);
        playerOne.setState(Action.ATTACK);

        Pet playerTwo = new Pet();
        playerTwo.setName("Player 2");
        playerTwo.setHealth(100);
        playerTwo.setAttack(60);
        playerTwo.setDefence(60);
        playerTwo.setSpeed(80);
        playerTwo.setType(Type.FIRE);
        playerTwo.setState(Action.SECONDARY_ATTACK);

        BattleDaoImpl battle = new BattleDaoImpl(playerOne, playerTwo);
        System.out.println(battle);
        System.out.println(battle.checkIfEvaded());
        System.out.println(battle.getSecondPlayersDefence(playerOne));
        System.out.println(battle.getSecondPlayersDefence(playerTwo));
        System.out.println(battle.checkPrimaryTypes(10));
        System.out.println(battle.checkSecondaryTypes(10));
        battle.primaryAttack(playerOne);
        battle.primaryAttack(playerTwo);
        battle.secondaryAttack(playerOne);
        battle.secondaryAttack(playerTwo);

    }
}