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
            curve.generateBasePoint();
            int privateKey = new Random().nextInt((int) curve.getP());
            while (privateKey < 0 || privateKey > curve.getP()) {
                    privateKey += curve.getP();
            }

            if (privateKey == 0) privateKey = (int) (curve.getP()/2);
            
            long[] publicKey = curve.getPublicKey(privateKey);
            
            System.out.println(privateKey);
            System.out.println(publicKey[0] + " " + publicKey[1]);
            
            
            System.out.println("Signing...");
            Prime pri = new Prime();
            NumberFormatter numFor = new NumberFormatter();
            int k = new Random().nextInt((int)curve.getP()) ;;
            long R = -1, S = -1;
            
            while (R <= 0 || S <=0){
                k = new Random().nextInt((int)curve.getP()) ;
                R = (k * curve.getBasePoint()[0]) % EllipticCurve.DEFAULT_P;
                System.out.println(numFor.parseHashToLong(coba) + R * privateKey);
                S = (pri.getInverse(k,EllipticCurve.DEFAULT_P) * (numFor.parseHashToLong(coba) + R * privateKey) ) % EllipticCurve.DEFAULT_P;
            }
            
            
            System.out.println("R = " + R);
            System.out.println("S = " + S);
            System.out.println("k = " + k);
            
            
            long W = pri.getInverse(S,EllipticCurve.DEFAULT_P) % EllipticCurve.DEFAULT_P;
            long u1 = (W*numFor.parseHashToLong(coba)) % EllipticCurve.DEFAULT_P;
            long u2 = (W*R)% EllipticCurve.DEFAULT_P;
            long tes =  (W * (numFor.parseHashToLong(coba) + R * privateKey) ) % EllipticCurve.DEFAULT_P;
            System.out.println("tes = " + tes);
            System.out.println("W = " + W);
            System.out.println("u1 = " + u1);
            System.out.println("u2 = " + u2);
            System.out.println((u1 * curve.getBasePoint()[0] + u2 * publicKey[0]) % EllipticCurve.DEFAULT_P);
            System.out.println(curve.multiplyPoint(u1, curve.getBasePoint())[0]);
            System.out.println(curve.multiplyPoint(u2, publicKey)[0]);
            long Verif = (curve.addPoint(curve.multiplyPoint(u1, curve.getBasePoint()),curve.multiplyPoint(u2, publicKey))[0]);
            System.out.println(Verif);
	}

}
