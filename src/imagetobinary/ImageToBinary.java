/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagetobinary;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author EKOON Corp.
 */
public class ImageToBinary {
    static String[] compName={"AutoComplete","InputTextarea","SelectBooleanButton","SelectBooleanCheckbox","SelectOneButton","SelectOneRadio","SelectCheckboxMenu","SelectOneMenu","TriStateCheckbox","InputGroup","InputNumber","InputMask","SelectOneListbox","SelectManyButton","SelectManyMenu","SelectManyCheckbox","MultiSelectListbox","ToggleSwitch","Calendar","Editor","Signature","Spinner","Slider","InputSwitch","Password","Keyboard","Rating","ColorPicker","Inplace","KeyFilter","Knob","TextEditor","Button","Link","CommandButton","SplitButton","CommandLink","Carousel","DataGrid","DataList","DataView","DataScroller","DataTable","HorizontalTree","OrderList","Organigram","DataExporter","GMap","Mindmap","AjaxFramework","ClientSideValidation","PickList","Repeat","Ring","Schedule","TagCloud","DialogFramework","DragDrop","FileDownload","ColorPicker","MultiSelectListbox","TextEditor","Menu","Growl","Messages","BlockUI","Cache","Captcha","Clock","Collector","DefaultCommand","Effect","ExceptionHandler","FontAwesome","Focus","Hotkey","IdleMonitor","ImportConstants","ImportEnum","Lifecycle","Log","OutputLabel","Printer","ProgressBar","ResetInput","Resizable","ResponsiveDesign","Separator","Spacer","Sticky","ThemeSwitcher","Watermark","Barcode","ImageCompare","ContentFlow","ImageCropper","Galleria","GraphicImage","Media","PhotoCam","ImageSwitch","ConfirmDialog","LightBox","OverlayPanel","Sidebar","AccordionPanel","Dashboard","Fieldset","GridCSS","NotificationBar","Panel","PanelGrid","Ribbon","ScrollPanel","TabView","Toolbar","Wizard","Diagram","HorizontalTree","Timeline","Tree","TreeTable","FileUpload","ContextMenu","Terminal","Dialog","Tooltip","Layout"};
    static void getImageBinary(String url) { 
        Connection connection = null;
        String connectionURL = "jdbc:mysql://localhost:3306/zero?useSSL=false";
        ResultSet rs = null;
        PreparedStatement psmnt = null;
        FileInputStream fis;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root","hoge");
            for(int i=0;i<compName.length;i++){
                File image = new File(url+compName[i]+".JPG");
                fis=new FileInputStream(image);
                psmnt = connection.prepareStatement("update mt_component set component_picture=? where component_ttl=?;");
                psmnt.setString(2, compName[i]);
                fis = new FileInputStream(image);
                psmnt.setBinaryStream(1, (InputStream) fis, (int) (image.length()));
                int s = psmnt.executeUpdate();
                if (s > 0) {
                        System.out.println("Uploaded successfully !");
                } else {
                        System.out.println("unsucessfull to upload image.");
                }
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String fileName = "C://Users//hanxu//Desktop//componet//";
        getImageBinary(fileName);
    }
    
}
