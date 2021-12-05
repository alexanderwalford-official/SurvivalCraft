import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Objects;


public class SystemTests {

    @Test
    public static void TestUsername () {
        // test for the removal of illegal characters that may cause errors on the scoreboard
        System.out.println("SYS: Performing automated testing on username..");
        String[] username = RenderSinglePlayerMap.playerid.getText().split("");
        String[] blockedvars = {"/","_","*","%","\\"};
        for (String s : username) {
            for (String b : blockedvars) {
                if (Objects.equals(s, b)) {
                    RenderSinglePlayerMap.playerid.setText(s.replace(b, ""));
                }
            }
        }
        System.out.println("SYS: Username check completed.");
    }
}
