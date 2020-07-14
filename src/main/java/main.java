
import dao.database.Db;
import dao.database.SingersTableActions;
import dao.database.SongsTableActions;
import dao.database.UsersTableActions;
import utils.Log;
import utils.Logger;
import utils.MultiOutputStream;
import view.MusicGUI;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        MusicGUI.display();
    }
}
