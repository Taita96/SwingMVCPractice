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
    public DefaultTableModel dtmTableHome;
    public JButton btnHomePreviewBack;

    public JPanel JPanelHistoy;
    public DefaultTableModel dtmTableHistory;
    public JTable tableHistory;

    public JPanel JPanelBags;
    public JTable tableBags;
    public JButton btnBagsToHome;
    public JButton btnBagsBuy;

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
    public JButton btnGeneralViewInfo;
    public JButton btnAddressViewInfo;
    public JButton btnPreviewADBack;
    public JButton btnPreviewADDelete;
    public JButton btnPreviewADHome;
    public JButton btnPreviewNormalHome;
    public JButton btnPreviewNormalBack;
    public JButton btnPreviewNormalDelete;
    public DefaultTableModel dtmTablePreviewADInfo;
    public DefaultTableModel dtmTablePreviewNormalInfo;
    public JTable tablePreviewNormalInfo;
    public JTable tablePreviewADInfo;
    public JButton btnPreviewADForm;

    public JPanel JPanelEditProfile;
    public JTextField txtEditProfileName;
    public JTextField txtEditProfileLastName;
    public JTextField txtEditProfileUsername;
    public DatePicker dpEditProfileBithday;
    public JPasswordField txtEditProfilePassword;
    public JPasswordField txtEditProfileConfirmPassword;
    public JTextField txtEditProfileApartament;
    public JTextField txtEditProfileStreet;
    public JButton btnEditProfileAddressBack;
    public JButton btnEditProfileAddressSave;
    public JButton btnEditProfileAddressClean;
    public JButton btnEditProfileBack;
    public JButton btnEditProfileSave;
    public JButton btnEditProfileClean;
    public JComboBox cbEditProfileCountry;
    public JComboBox cbEditProfileCity;

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
    public JComboBox cbAddSize;
    public JTextField txtAddCode;
    public JTextField txtAddLastCode;
    public JLabel lblAddgadget;
    public JLabel lblAddSecurity;
    public JLabel lblAddWheels;
    public JPanel JPanelComponent;
    public DefaultTableModel dtmTableProducts;
    public JButton btnHomeAdminDelete;
    public ButtonGroup waterproof;
    public ButtonGroup wheels;

    public JPanel JPanelHomeAdmin;
    public JTabbedPane tabbedPane1;
    public JTable tableAdminProducts;
    public JTable tableHomeAdminSales;

    public JPanel JPanelSales;
    public DefaultTableModel dtmtableAdminSales;
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

        Utilities.setBorderBtn(btnHomeAdminDelete, Color.WHITE);
        btnHomeAdminDelete.setActionCommand("btnHomeAdminDelete");


        Utilities.setBorderBtn(btnEditProfileAddressBack, Color.WHITE);
        btnEditProfileAddressBack.setActionCommand("btnEditProfileAddressBack");

        Utilities.setBorderBtn(btnEditProfileAddressSave, Color.WHITE);
        btnEditProfileAddressSave.setActionCommand("btnEditProfileAddressSave");

        Utilities.setBorderBtn(btnEditProfileAddressClean, Color.WHITE);
        btnEditProfileAddressClean.setActionCommand("btnEditProfileAddressClean");

        Utilities.setBorderBtn(btnEditProfileBack, Color.WHITE);
        btnEditProfileBack.setActionCommand("btnEditProfileBack");

        Utilities.setBorderBtn(btnEditProfileSave, Color.WHITE);
        btnEditProfileSave.setActionCommand("btnEditProfileSave");

        Utilities.setBorderBtn(btnEditProfileClean, Color.WHITE);
        btnEditProfileClean.setActionCommand("btnEditProfileClean");

        Utilities.setBorderBtn(btnHomePreviewBack, Color.WHITE);
        btnHomePreviewBack.setActionCommand("btnHomePreviewBack");

        Utilities.setBorderBtn(btnBagsToHome, Color.WHITE);
        btnBagsToHome.setActionCommand("btnBagsToHome");

        Utilities.setBorderBtn(btnBagsBuy, Color.WHITE);
        btnBagsBuy.setActionCommand("btnBagsBuy");

        Utilities.setBorderBtn(btnGeneralViewInfo, Color.WHITE);
        btnGeneralViewInfo.setActionCommand("btnGeneralViewInfo");

        Utilities.setBorderBtn(btnAddressViewInfo, Color.WHITE);
        btnAddressViewInfo.setActionCommand("btnAddressViewInfo");

        Utilities.setBorderBtn(btnPreviewADBack, Color.WHITE);
        btnPreviewADBack.setActionCommand("btnPreviewADBack");

        Utilities.setBorderBtn(btnPreviewNormalBack, Color.WHITE);
        btnPreviewNormalBack.setActionCommand("btnPreviewNormalBack");

        Utilities.setBorderBtn(btnPreviewADHome, Color.WHITE);
        btnPreviewADHome.setActionCommand("btnPreviewADHome");

        Utilities.setBorderBtn(btnPreviewNormalHome, Color.WHITE);
        btnPreviewNormalHome.setActionCommand("btnPreviewNormalHome");

        Utilities.setBorderBtn(btnPreviewNormalDelete, Color.WHITE);
        btnPreviewNormalDelete.setActionCommand("btnPreviewNormalDelete");

        Utilities.setBorderBtn(btnPreviewADDelete, Color.WHITE);
        btnPreviewADDelete.setActionCommand("btnPreviewADDelete");

        Utilities.setBorderBtn(btnPreviewADForm, Color.WHITE);
        btnPreviewADForm.setActionCommand("btnPreviewADForm");

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
        cbEditProfileCountry.setModel(new DefaultComboBoxModel<Country>(Country.values()));
        cbEditProfileCountry.setActionCommand("cbEditProfileCountry");
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

        dtmTableProducts = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        tableAdminProducts.setModel(dtmTableProducts);

        dtmTableHome = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHome.setModel(dtmTableHome);
        dtmTableHistory = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHistory.setModel(dtmTableHistory);
        tableBags.setModel(dtmTableHome);


        dtmTablePreviewADInfo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        tablePreviewADInfo.setModel(dtmTablePreviewADInfo);

        dtmTablePreviewNormalInfo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 4 && column != 6;
            }
        };

        tablePreviewNormalInfo.setModel(dtmTablePreviewNormalInfo);


        dtmtableAdminSales = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        tableAdminSales.setModel(dtmtableAdminSales);
    }



}
