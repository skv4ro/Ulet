package sk.juvius.ulet;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;

public class ErrorDialog  {
    private final static String imageBase64 = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQBAMAAADt3eJSAAAAA3NCSVQICAjb4U/gAAAACXBIWXMAAACFAAAAhQHi7P/BAAAAGXRFWHRTb2Z0d2FyZQB3d3cuaW5rc2NhcGUub3Jnm+48GgAAAC1QTFRF/////wAA/1Ur8lEo71Ip8FMo8VIo71Io8VIo71Mo8FIn71Eo71Io8FIo8FIo6AgzBwAAAA50Uk5TAAEGJnB4f4Cxs7XV5vbN2yIZAAAAYUlEQVQIHWNgYDCfc7KYAQhE7r1799aRgYFx3TsgeCXAwPLu9bt3+945MNi+a4p7qvHuMkPdawXWAKZ9zxjmvQtgYGB995Lh3LunDAxx794gGHApuGK4driBcCvglsKcAQAWmEP/6jtPqQAAAABJRU5ErkJggg==";
    private static JDialog jDialog = new JDialog();
    private static JTextArea errorMsgArea = new JTextArea();

    static {
        try {
            jDialog.setIconImage(ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(imageBase64))));
        } catch (IOException ignored) {
        }
        jDialog.setTitle(GlobalContainer.APP_NAME + " ERROR");
        jDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        jDialog.setAlwaysOnTop(true);
        jDialog.setModal(true);
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> jDialog.setVisible(false));
        jDialog.setLayout(new BoxLayout(jDialog.getContentPane(), BoxLayout.Y_AXIS));
        ((JPanel) jDialog.getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        jDialog.getContentPane().add(errorMsgArea);
        jDialog.getContentPane().add(closeBtn, Component.CENTER_ALIGNMENT);
        errorMsgArea.setEditable(false);
        Font oldFont = errorMsgArea.getFont();
        //Font newFont = new Font(oldFont.getFontName(), oldFont.getStyle(), (int)(oldFont.getSize() * 1.5));
        //errorMsgArea.setFont(newFont);
        jDialog.getContentPane().setBackground(errorMsgArea.getBackground());
        jDialog.setMinimumSize(new Dimension(200, 200));
    }

    public static void showErrorMsg(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        showErrorMsg(sw.toString());
    }

    public static void showErrorMsg(String errorMsg) {
        errorMsgArea.setText(errorMsg);
        jDialog.pack();
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }
}
