package view;

import com.github.lgooddatepicker.components.DatePicker;
import model.entity.enums.*;
import util.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardView extends JFrame{

    public JPanel JPanelPricipal;
    public JPanel JPanelCard;

    public JButton btnHome;
    public JButton btnBags;
    public JButton btnHistory;
    public JButton btnProfile;
    public JButton btnSelling;
    public JButton btnAddProduct;
    public JButton btnConfig;
    public JTextField txtSearching;
    public JLabel lblImgProfile;
    public JButton btnUserPopup;

    public JPanel JPanelHome;
    public JLabel lblHomeCountry;
    public JButton btnHomeProfile;
    public JLabel lblHomeCity;
    public JLabel lblHomeStreet;
    public JLabel lblHomeNumberApartament;
    public JLabel lblHomeTypoProduct;
    public JLabel lblHomeCodeBag;
    public JLabel lblHomeBrand;
    public JLabel lblHomeMaterial;
    public JTable tableHome;

    public JPanel JPanelHistoy;
    public JTable tableHistory;

    public JPanel JPanelBags;
    public JTable tableBags;

    public JPanel JPanelViewProfile;
    public JTextField txtProfileName;
    public JTextField txtProfileLastName;
    public JTextField txtProfileUsername;
    public JTextField txtProfileEmail;
    public JTextField txtProfileCountry;
    public JTextField txtProfileCity;
    public JTextField txtProfileApartament;
    public JTextField txtProfileStreet;
    public DatePicker dpProfileUsername;
    public JButton btnProfileHome;
    public JButton btnProfileEdit;

    public JPanel JPanelEditProfile;
    public JTextField txtEditProfileName;
    public JTextField txtEditProfileLastName;
    public JTextField txtEditProfileUsername;
    public DatePicker dpEditProfileBithday;
    public JPasswordField txtEditProfilePassword;
    public JPasswordField txtEditProfileConfirmPassword;
    public JTextField txtEditProfileCountry;
    public JTextField txtEditProfileCity;
    public JTextField txtEditProfileApartament;
    public JTextField txtEditProfileStreet;

    public JPanel JPanelAddProducto;
    public JComboBox cbAddType;
    public JSpinner spAddPrice;
    public JSlider slAddWeight;
    public JRadioButton rbAddYesWater;
    public JRadioButton rbAddNoWater;
    public JLabel lblNewTypeProduct;
    public JComboBox cbAddMaterial;
    public JComboBox cbAddBrand;
    public DatePicker dpAddDate;
    public JComboBox cbAddGadget;
    public JComboBox cbAddSecurity;
    public JRadioButton rbAddYesWheels;
    public JRadioButton rbAddNoWheels;
    public JButton btnAddHome;
    public JButton btnAddSave;
    public JButton btnAddClean;
    public JButton btnHomeAdminNewProduct;
    public JButton btnHomeAdminUpdateForm;
    public JButton btnHomeAdminUpdateTable;
    public JComboBox cbAddSize;
    public JTextField txtAddCode;
    public JTextField txtAddLastCode;
    public JLabel lblAddgadget;
    public JLabel lblAddSecurity;
    public JLabel lblAddWheels;
    public JPanel JPanelComponent;
    public DefaultTableModel dtmTableProducts;
    public ButtonGroup waterproof;
    public ButtonGroup wheels;

    public JPanel JPanelHomeAdmin;
    public JTabbedPane tabbedPane1;
    public JTable tableAdminProducts;
    public JTable tableHomeAdminSales;

    public JPanel JPanelSales;
    public JTable tableAdminSales;

    public JPanel JPanelConfig;
    public JPasswordField txtConfigEditDataPass;
    public JTextField txtConfigIP;
    public JTextField txtConfigUser;
    public JTextField txtConfigEditIP;
    public JTextField txtConfigEditUser;
    public JPasswordField txtConfigEditDataConfirmPass;
    public JPasswordField txtConfigEditAdminPass;
    public JPasswordField txtConfigEditAdminConfirmPass;
    public JButton btnConfigHome;
    public JButton btnConfigSave;
    public JButton btnConfigClean;



    public DashboardView(){
        setTitle("Dashboard");
        setContentPane(JPanelPricipal);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
        initComponent();
        setVisible(true);
    }

    private void initComponent(){

        initImageIcon();

        initBtn();

        initCombobox();

        initRadioButtons();

        initTables();

    }



    private void initImageIcon(){
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("maletin.png"));
        Image imgIcono = icono.getImage();
        setIconImage(imgIcono);
    }

    private void initBtn(){
        Utilities.setBorderBtn(btnHome, Color.white);
        btnHome.setActionCommand("btnHome");

        Utilities.setBorderBtn(btnBags, Color.white);
        btnBags.setActionCommand("btnBags");

        Utilities.setBorderBtn(btnHistory, Color.white);
        btnHistory.setActionCommand("btnHistory");

        Utilities.setBorderBtn(btnProfile, Color.white);
        btnProfile.setActionCommand("btnProfile");

        Utilities.setBorderBtn(btnSelling, Color.white);
        btnSelling.setActionCommand("btnSelling");

        Utilities.setBorderBtn(btnAddProduct, Color.white);
        btnAddProduct.setActionCommand("btnAddProduct");

        Utilities.setBorderBtn(btnConfig, Color.white);
        btnConfig.setActionCommand("btnConfig");

        Utilities.setBorderBtn(btnProfileHome, Color.BLACK);
        btnProfileHome.setActionCommand("btnProfileHome");

        Utilities.setBorderBtn(btnProfileEdit, Color.BLACK);
        btnProfileEdit.setActionCommand("btnProfileEdit");

        Utilities.setBorderBtn(btnAddHome, Color.BLACK);
        btnAddHome.setActionCommand("btnAddHome");

        Utilities.setBorderBtn(btnAddSave, Color.BLACK);
        btnAddSave.setActionCommand("btnAddSave");

        Utilities.setBorderBtn(btnAddClean, Color.BLACK);
        btnAddClean.setActionCommand("btnAddClean");

        Utilities.setBorderBtn(btnConfigHome, Color.BLACK);
        btnConfigHome.setActionCommand("btnConfigHome");

        Utilities.setBorderBtn(btnConfigSave, Color.BLACK);
        btnConfigSave.setActionCommand("btnConfigSave");

        Utilities.setBorderBtn(btnConfigClean, Color.BLACK);
        btnConfigClean.setActionCommand("btnConfigClean");

        Utilities.setBorderBtn(btnUserPopup, Color.WHITE);
        btnUserPopup.setActionCommand("btnUserPopup");

        Utilities.setBorderBtn(btnHomeAdminNewProduct, Color.WHITE);
        btnHomeAdminNewProduct.setActionCommand("btnHomeAdminNewProduct");

        Utilities.setBorderBtn(btnHomeAdminUpdateForm, Color.WHITE);
        btnHomeAdminUpdateForm.setActionCommand("btnHomeAdminUpdateForm");

        Utilities.setBorderBtn(btnHomeAdminUpdateTable, Color.WHITE);
        btnHomeAdminUpdateTable.setActionCommand("btnHomeAdminUpdateTable");

    }

    private void initCombobox() {
        spAddPrice.setModel(new SpinnerNumberModel(1.0,0.0,10000.00,1.0));
        cbAddType.setModel(new DefaultComboBoxModel<TypeProduct>(TypeProduct.values()));
        cbAddType.setActionCommand("TypeOfProductCombo");
        cbAddMaterial.setModel(new DefaultComboBoxModel<Material>(Material.values()));
        cbAddBrand.setModel(new DefaultComboBoxModel<Brand>(Brand.values()));
        cbAddGadget.setModel(new DefaultComboBoxModel<Gadget>(Gadget.values()));
        cbAddSecurity.setModel(new DefaultComboBoxModel<Security>(Security.values()));
        cbAddSize.setModel(new DefaultComboBoxModel<Size>(Size.values()));
    }

    private void initRadioButtons(){
        waterproof = new ButtonGroup();
        waterproof.add(rbAddNoWater);
        waterproof.add(rbAddYesWater);

        wheels = new ButtonGroup();
        wheels.add(rbAddYesWheels);
        wheels.add(rbAddNoWheels);
    }

    private void initTables() {
        dtmTableProducts = new DefaultTableModel();
        tableAdminProducts.setModel(dtmTableProducts);
    }
}
