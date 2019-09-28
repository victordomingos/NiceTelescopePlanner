package gui;

import javax.swing.JOptionPane;
import com.apple.mrj.MRJAboutHandler;
import com.apple.mrj.MRJPrefsHandler;
import com.apple.mrj.MRJQuitHandler;

public class MacOSXController 
implements MRJAboutHandler, MRJQuitHandler, MRJPrefsHandler
{

  public void handleAbout()
  {
    JOptionPane.showMessageDialog(null, 
                                  "about", 
                                  "about", 
                                  JOptionPane.INFORMATION_MESSAGE);
  }

  public void handlePrefs() throws IllegalStateException
  {
    JOptionPane.showMessageDialog(null, 
                                  "prefs", 
                                  "prefs", 
                                  JOptionPane.INFORMATION_MESSAGE);
  }

  public void handleQuit() throws IllegalStateException
  {
    JOptionPane.showMessageDialog(null, 
                                  "quit", 
                                  "quit", 
                                  JOptionPane.INFORMATION_MESSAGE);
    // handle exit here
    System.exit(0);
  }

}
