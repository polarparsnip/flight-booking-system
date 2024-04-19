package flight.vidmot;

import flight.classes.User;
import flight.serviceLayers.UserServiceLayer;
import javafx.fxml.FXML;

public class MenuController {

    UserServiceLayer USL = new UserServiceLayer();

    private User user;

    private static String userId;

    @FXML
    protected void fxLoginHandler() {
        LoginDialog u = new LoginDialog();
        userId = u.getUser();

        user = USL.searchUserById(userId);

        if (userId != null && user.getName() != null) {
            if (user.getId().equals(userId) ) {
                ViewSwitcher.switchTo(View.HOMEPAGE);
                HomepageController hpc = (HomepageController) ViewSwitcher.lookup(View.HOMEPAGE);
                hpc.setPurchaser(user);

            }
        } else {
            System.out.println("User cancelled");
        }
    }


    @FXML
    protected void fxRegisterHandler() {
        UserDialog v = new UserDialog();
        User puser = v.getUser();
        if (puser!= null) {
            user = puser;
            try {
                USL.createUser(puser);
            } catch(Exception e) {
                System.err.println(e);
            }

        } else {
            System.out.println("User cancelled");
        }
    }
    
}
