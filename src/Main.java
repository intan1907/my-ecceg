import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.EllipticCurve;
import util.NumberFormatter;
import util.Prime;
import util.SHA1;
/**
 * main program: creating GUI and integrate all classes
 */

/**
 * @author intan
 *
 */
public class Main {

	public static void main(String[] args) {
            System.out.println("Dor");
            System.out.println("GeeksForGeeks");
            SHA1 sha_test = new SHA1();
            String coba = sha_test.encodeHex("hello world");
            System.out.println(coba);
            
            EllipticCurve curve = new EllipticCurve(EllipticCurve.DEFAULT_A,EllipticCurve.DEFAULT_B,EllipticCurve.DEFAULT_P);
	}

}
